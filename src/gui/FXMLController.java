package gui;

import DataAccess.DatabaseHandler;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLController implements Initializable {


    @FXML
    private TextField ConfirmpassField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField userIDField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label notification;

    static ObservableList<User> userInfoList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            userInfoList=dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void signUpAction(ActionEvent event) throws IOException, SQLException {

        if(userIDField.getText().equals("")) return;

        else {
            int id=Integer.parseInt(userIDField.getText());
            String name=NameField.getText();
            String email=EmailField.getText();
            String password=encryptThisString(PasswordField.getText());
            String confirmpass=encryptThisString(ConfirmpassField.getText());

            if(!email.contains("@gmail.com")){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Email");

                alert.showAndWait();

            }


            if((!password.equals(confirmpass)) || (PasswordField.getText().length()<7)){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Password doesnt match or length is short or invalid email");

                alert.showAndWait();


            }

        }

    }

    @FXML
    private void emailfieldAction(KeyEvent event) {
        notification.setText("");
        String email=EmailField.getText();

        for(User userobj:userInfoList){
            if(userobj.getUserName().equals(email)){
                notification.setText("Not available");
                break;
            }
        }
    }

    public static String encryptThisString(String pass)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");

            byte[] messageDigest = md.digest(pass.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}