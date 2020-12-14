package gui;

import DataAccess.DatabaseHandler;
import classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField txtUsername;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private RadioButton radioBtnMale;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    void initialize() {
        btnSignUp.setOnAction(event -> {
            signUpNewUser();
        });

    }

    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String gender = "";

        if (radioBtnMale.isSelected()) {
            gender = "Male";
        }
        if (radioBtnFemale.isSelected())
            gender = "Female";

        User user = new User(firstName, lastName, phoneNumber, address, username, password, gender);

        databaseHandler.signUpUser(user);

    }
}
