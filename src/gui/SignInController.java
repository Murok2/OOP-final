package gui;

import DataAccess.DatabaseHandler;
import animations.Shake;
import classes.Admin;
import classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneInformation;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblErrors;

    @FXML
    void initialize() {
        btnSignIn.setOnAction(event -> {
            String emailText = txtEmail.getText().trim();
            String passwordText = txtPassword.getText().trim();

            if (!emailText.equals("") && !passwordText.equals("")) {
                loginUser(emailText, passwordText);
            } else {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Login and/or password are empty");
            }
        });

        btnSignUp.setOnAction(event -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/SignUp.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }

    private void loginUser(String usernameText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setUserName(usernameText);
        user.setPassword(passwordText);

        Admin admin = new Admin();
        admin.setEmail(usernameText);
        admin.setPassword(passwordText);

        ResultSet userResultSet = dbHandler.getUser(user); //выделить память
        ResultSet adminResultSet = dbHandler.getAdmin(admin);

        int userCounter = 0;

        while(true){
            try {
                if (!userResultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            userCounter++;
        }

        if(userCounter >= 1){
            openNewScene("/gui/MainPanel.fxml");
        } else {
            Shake paneAnimation = new Shake(paneInformation);
            paneAnimation.playAnimation();
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Login and/or password are incorrect");
        }

        int adminCounter = 0;

        while(true){
            try {
                if (!adminResultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adminCounter++;
        }

        if(adminCounter >= 1){
            openNewScene("/gui/MainAdmin.fxml");
        } else {
            Shake paneAnimation = new Shake(paneInformation);
            paneAnimation.playAnimation();
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Login and/or password are incorrect");
        }
    }


    private void openNewScene(String newWindow) {
        btnSignUp.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(newWindow));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}