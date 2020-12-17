package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataAccess.DatabaseHandler;
import classes.BookInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static gui.FXMLController.userInfoList;

public class AdminAddBookController implements Initializable {

    @FXML
    private TextField bookTitle;
    @FXML
    private TextField bookID;
    @FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookPublisher;
    @FXML
    private Button addbookButton;
    @FXML
    private TextField numofCopies;

    static ObservableList<BookInfo> bookInfoList=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            bookInfoList=dbAction.getAllbookInfo();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addbookbuttonAction(MouseEvent event) throws SQLException, ClassNotFoundException {

        if(bookID.getText().equals("")) return;

        else {

            String title=bookTitle.getText();
            String isbn=bookID.getText();
            String author=bookAuthor.getText();
            String publisher=bookPublisher.getText();
            int numofcopies=Integer.parseInt(numofCopies.getText());


            BookInfo bookobj=new BookInfo(title,isbn,author,publisher,numofcopies);



            //database action
            DatabaseHandler dbAction=new DatabaseHandler();
            dbAction.addbookInfo(bookobj);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully");
            alert.showAndWait();

            bookTitle.clear();
            bookID.clear();
            bookAuthor.clear();
            bookPublisher.clear();
            numofCopies.clear();


        }

    }

}