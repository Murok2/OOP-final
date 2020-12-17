package gui;

import DataAccess.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private TableView tblData;

    @FXML
    private ImageView btnClose;


    @FXML
    void initialize() {
        fetchColumnList();
        fetchRowList();

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
            System.exit(0);
        }
    }

    PreparedStatement preparedStatement;
    Connection connection;

    public MainPanelController() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        connection = (Connection) databaseHandler.getDbConnection();
    }

    private ObservableList<ObservableList> data;
    String SQL = "SELECT * from useraccounts";

    private void fetchColumnList() {

        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    private void fetchRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}