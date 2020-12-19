package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DataAccess.DatabaseHandler;
import classes.BookInfo;
import classes.IssuedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReturnBookController implements Initializable {

    @FXML
    private TextField isbn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void returnAction(MouseEvent event) throws SQLException, ClassNotFoundException {
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","pass");
        Statement statement=conn.createStatement();

        String query= " SELECT * FROM bookinfo WHERE ISBN LIKE '%"+isbn.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);

        if(rs.next()){
            String title= rs.getString("Title");
            String isbn=rs.getString("ISBN");
            String author=rs.getString("Author");
            String publisher =rs.getString("Publisher");
            String categories=rs.getString("Categories");
            String subcategories=rs.getString("Subcategories");
            int year=rs.getInt("Year");
            int rating=rs.getInt("Rating");
            int numcopies =rs.getInt("numberOfCopies");

            IssuedBook bookobj=new IssuedBook(title,isbn,author,publisher);
            BookInfo bookinfo=new BookInfo(title,isbn,author,publisher,categories, subcategories, year, rating, numcopies);

            DatabaseHandler dbAction=new DatabaseHandler();
            dbAction.removeissuedbooks(bookobj);
            DatabaseHandler dbAction3=new DatabaseHandler();
            dbAction3.incrementCopies(bookinfo);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book returned successfully");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error!");
            alert.setHeaderText("Can not return book");
            alert.setContentText("This ISBN number does not exist");

            alert.showAndWait();
        }
    }

    @FXML
    private void backAction(MouseEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainPanel.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}