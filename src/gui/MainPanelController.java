package gui;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataAccess.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnMembers;

    @FXML
    private Button btnPersonalInfo;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnAbout;

    @FXML
    private GridPane pnAbout;

    @FXML
    private GridPane pnSettings;

    @FXML
    private GridPane pnBooks;

    @FXML
    private GridPane pnPersonalInfo;

    @FXML
    private GridPane pnMembers;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Label lblMiniStatus;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView tblBooksData;

    @FXML
    private TableView tblMembersData;

    @FXML
    private ImageView btnClose;


    @FXML
    void initialize() {
        fetchColumnListForBooks();
        fetchRowListForBooks();

        fetchColumnListForMembers();
        fetchRowListForMembers();
    }

    @FXML
    public void handleClicks(javafx.event.ActionEvent event) {
        if (event.getSource() == btnBooks) {
            lblMiniStatus.setText("/home/Books");
            lblStatus.setText("Books");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(113,86,61), CornerRadii.EMPTY, Insets.EMPTY)));
            pnBooks.setVisible(true);
            pnAbout.setVisible(false);
            pnSettings.setVisible(false);
            pnPersonalInfo.setVisible(false);
            pnMembers.setVisible(false);
        } else if (event.getSource() == btnMembers) {
            lblMiniStatus.setText("/home/Members");
            lblStatus.setText("Members");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnMembers.setVisible(true);
            pnAbout.setVisible(false);
            pnSettings.setVisible(false);
            pnPersonalInfo.setVisible(false);
            pnBooks.setVisible(false);
        } else if (event.getSource() == btnPersonalInfo) {
            lblMiniStatus.setText("/home/Personal information");
            lblStatus.setText("Personal Information");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 99, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnPersonalInfo.setVisible(true);
            pnAbout.setVisible(false);
            pnSettings.setVisible(false);
            pnBooks.setVisible(false);
            pnMembers.setVisible(false);
        } else if (event.getSource() == btnSettings) {
            lblMiniStatus.setText("/home/Settings");
            lblStatus.setText("Settings");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnSettings.setVisible(true);
            pnAbout.setVisible(false);
            pnBooks.setVisible(false);
            pnPersonalInfo.setVisible(false);
            pnMembers.setVisible(false);
        } else if (event.getSource() == btnAbout) {
            lblMiniStatus.setText("/home/About");
            lblStatus.setText("About");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(42,28,66), CornerRadii.EMPTY, Insets.EMPTY)));
            pnAbout.setVisible(true);
            pnBooks.setVisible(false);
            pnSettings.setVisible(false);
            pnPersonalInfo.setVisible(false);
            pnMembers.setVisible(false);
        }
    }

    @FXML
    void handleClose(MouseEvent event) {
        if (event.getSource() == btnClose) {
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
        }
    }

    PreparedStatement preparedStatement;
    Connection connection;

    public MainPanelController() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        connection = (Connection) databaseHandler.getDbConnection();
    }

    private ObservableList<ObservableList> data;
    String SQL_BOOK_INFO = "SELECT * from bookinfo";
    String SQL_MEMBERS_INFO = "SELECT * from useraccounts";

    private void fetchColumnListForBooks() {

        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL_BOOK_INFO);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblBooksData.getColumns().removeAll(col);
                tblBooksData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    private void fetchRowListForBooks() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL_BOOK_INFO);

            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblBooksData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void fetchColumnListForMembers() {
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL_MEMBERS_INFO);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                if (i == 1 || i == 2 || i == 3 || i == 5) {
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tblMembersData.getColumns().removeAll(col);
                    tblMembersData.getColumns().addAll(col);

                    System.out.println("Column [" + i + "] ");
                }

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    private void fetchRowListForMembers() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL_MEMBERS_INFO);

            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblMembersData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}