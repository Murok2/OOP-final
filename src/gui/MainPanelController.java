package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataAccess.DatabaseHandler;
import classes.BookInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainPanelController {

    @FXML
    private TableView<BookInfo> tblBooksData;
    @FXML
    private TableColumn<BookInfo, String> titleColumn;
    @FXML
    private TableColumn<BookInfo, String> IDColumn;
    @FXML
    private TableColumn<BookInfo, String> AuthorColumn;
    @FXML
    private TableColumn<BookInfo, String> PublisherColumn;
    @FXML
    private TableColumn<BookInfo, Integer> numCopies;
    @FXML
    private TableColumn<BookInfo, String> CategoriesColumn;
    @FXML
    private TableColumn<BookInfo, String> SubCategoriesColumn;
    @FXML
    private TableColumn<BookInfo, Integer> YearColumn;
    @FXML
    private TableColumn<BookInfo, Integer> RatingColumn;

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
    private TableView tblMembersData;

    @FXML
    private ImageView btnClose;

    @FXML
    private RadioButton rbTitle;

    @FXML
    private RadioButton rbAuthor;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button btnBlueColor;

    @FXML
    private Button btnGreenColor;

    @FXML
    private Button btnPurpleColor;

    @FXML
    private Button btnRedColor;

    @FXML
    private Button btnYellowColor;

    @FXML
    private Button btnOrangeColor;

    @FXML
    private Button btnResetColor;

    @FXML
    private VBox personalInfoContent;

    @FXML
    private VBox VboxSections;

    @FXML
    private AnchorPane AnchorPane;

    static ObservableList<BookInfo> bookInfoList=FXCollections.observableArrayList();
    static ObservableList<BookInfo> bookInfoListtwo=FXCollections.observableArrayList();

    @FXML
    void initialize() {

        tblBooksData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            bookInfoList= dbAction.getAllbookInfo();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
        CategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("categories"));
        SubCategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("subcategories"));
        YearColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("year"));
        RatingColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("rating"));
        numCopies.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("numcopies"));

        tblBooksData.setItems(bookInfoList);

        fetchColumnListForMembers();
        fetchRowListForMembers();

        btnBlueColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(18,191,206,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnGreenColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(76,206,82,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnOrangeColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(206,164,0,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnPurpleColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(206,123,158,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnRedColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(206,65,55,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnYellowColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(194,206,0,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnResetColor.setOnAction(event -> {
            AnchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255,1), CornerRadii.EMPTY, Insets.EMPTY)));
        });


    }

    @FXML
    public void radioButtonSelect(javafx.event.ActionEvent actionEvent) {
        if(rbAuthor.isSelected()){

        }
    }

    @FXML
    public void handleClicks(javafx.event.ActionEvent event) throws IOException {
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
            Parent pane=FXMLLoader.load(getClass().getResource("UserAllIssuedBooks.fxml"));
            personalInfoContent.getChildren().setAll(pane);

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
    void refreshAction(MouseEvent event) {
        tblBooksData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction=new DatabaseHandler();
        try {
            bookInfoList= dbAction.getAllbookInfo();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
        CategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("categories"));
        SubCategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("subcategories"));
        YearColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("year"));
        RatingColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("rating"));
        numCopies.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("numcopies"));

        tblBooksData.setItems(bookInfoList);
    }

    @FXML
    private void searchAction(MouseEvent event) throws SQLException {

        bookInfoListtwo.clear();

        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","pass");
        Statement statement=conn.createStatement();
        if(rbAuthor.isSelected()){
            String query= " SELECT * FROM bookinfo WHERE Author LIKE '%"+ searchField.getText()+"%'";
            ResultSet rs=statement.executeQuery(query);

            if(rs.next()){
                String title=rs.getString("Title");
                String isbn=rs.getString("ISBN");
                String author=rs.getString("Author");
                String publisher=rs.getString("Publisher");
                String categories=rs.getString("Categories");
                String subcategories=rs.getString("Subcategories");
                int year=rs.getInt("Year");
                int rating=rs.getInt("Rating");
                int numcopies =rs.getInt("numberOfCopies");
                BookInfo bookinfo=new BookInfo(title,isbn,author,publisher,categories, subcategories, year, rating, numcopies);
                bookInfoListtwo.add(bookinfo);

                titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
                IDColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("isbn"));
                AuthorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
                PublisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
                CategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("categories"));
                SubCategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("subcategories"));
                YearColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("year"));
                RatingColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("rating"));
                numCopies.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("numcopies"));

                tblBooksData.setItems(bookInfoListtwo);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Book is not Available");
                alert.showAndWait();
            }
        }
        if(rbTitle.isSelected()){
            String query= " SELECT * FROM bookinfo WHERE Title LIKE '%"+searchField.getText()+"%'";
            ResultSet rs=statement.executeQuery(query);

            if(rs.next()){
                String title=rs.getString("Title");
                String isbn=rs.getString("ISBN");
                String author=rs.getString("Author");
                String publisher=rs.getString("Publisher");
                String categories=rs.getString("Categories");
                String subcategories=rs.getString("Subcategories");
                int year=rs.getInt("Year");
                int rating=rs.getInt("Rating");
                int numcopies =rs.getInt("numberOfCopies");
                BookInfo bookinfo=new BookInfo(title,isbn,author,publisher,categories, subcategories, year, rating, numcopies);
                bookInfoListtwo.add(bookinfo);

                titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
                IDColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("Isbn"));
                AuthorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
                PublisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
                CategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("categories"));
                SubCategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("subcategories"));
                YearColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("year"));
                RatingColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("rating"));
                numCopies.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("numcopies"));

                tblBooksData.setItems(bookInfoListtwo);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Book is not Available");
                alert.showAndWait();
            }
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
    String SQL_MEMBERS_INFO = "SELECT * from useraccounts";


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












