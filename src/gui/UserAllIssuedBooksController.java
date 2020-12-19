package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataAccess.DatabaseHandler;
import classes.IssuedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static gui.MainPanelController.bookInfoList;

public class UserAllIssuedBooksController implements Initializable {

    @FXML
    private TableColumn<IssuedBook, String> titleColumn;
    @FXML
    private TableColumn<IssuedBook, String> ISBNColumn;
    @FXML
    private TableColumn<IssuedBook, String> authorColumn;
    @FXML
    private TableColumn<IssuedBook, String> publisherColumn;
    @FXML
    private TableView<IssuedBook> issuedBookstable;

    ObservableList<IssuedBook> issuedBookList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        issuedBookstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            issuedBookList= dbAction.getAllissuedbooks();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("title"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("Isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("publisher"));


        issuedBookstable.setItems(issuedBookList);
    }

    @FXML
    private void backAction(MouseEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void issueBookAction(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ToIssueBook.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void returnbookAction(MouseEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReturnBook.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}