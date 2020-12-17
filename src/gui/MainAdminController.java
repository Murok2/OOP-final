package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainAdminController implements Initializable {

    @FXML
    private VBox maincontent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent pane=FXMLLoader.load(getClass().getResource("AdminAllBooks.fxml"));
            maincontent.getChildren().setAll(pane);

        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addbooks(MouseEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("AdminAddBook.fxml"));
        maincontent.getChildren().setAll(pane);
    }

    @FXML
    private void showAllbooks(MouseEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("AdminAllBooks.fxml"));
        maincontent.getChildren().setAll(pane);
    }

}