package gui;

import DataAccess.DatabaseHandler;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private RadioButton radioBtnMale;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private Button btnSignIn;

    @FXML
    void handleSignUpButtonAction(ActionEvent event) throws Exception {
    }

    @FXML
    void initialize() {
        btnSignUp.setOnAction(event -> signUpNewUser());

        btnSignIn.setOnAction(event -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/SignIn.fxml"));
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

    private void openNewScene(String newWindow) {
        btnSignIn.getScene().getWindow().hide();

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

    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String gender = "";

        if(radioBtnMale.isSelected())
            gender = "Male";
        if(radioBtnFemale.isSelected())
            gender = "Female";

        User user = new User(firstName, lastName, email, password, gender, phoneNumber, address);

        databaseHandler.signUpUser(user);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully registered!");
        alert.showAndWait();

    }
}