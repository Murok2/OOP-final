package gui;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            userInfoList=dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}