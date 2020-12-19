package DataAccess;

import classes.Admin;
import classes.BookInfo;
import classes.IssuedBook;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configurations{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + UserConstants.USER_TABLE + "(" +
                UserConstants.USER_FIRSTNAME + "," + UserConstants.USER_LASTTNAME + "," +
                UserConstants.USER_USERNAME + "," + UserConstants.USER_PASSWORD + ","
                + UserConstants.USER_GENDER + "," + UserConstants.USER_PHONE_NUMBER + "," + UserConstants.USER_ADDRESS + ")" +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getGender());
            prSt.setString(6, user.getPhoneNumber());
            prSt.setString(7,user.getAddress());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "select * from useraccounts where username =? AND password =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAdmin(Admin admin) {
        ResultSet resultSet = null;

        String select = "select * from adminaccounts where Email =? AND Password =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, admin.getEmail());
            prSt.setString(2, admin.getPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ObservableList<BookInfo> getAllbookInfo() throws SQLException, ClassNotFoundException {

        ObservableList<BookInfo> bookInfoList=FXCollections.observableArrayList();

        Connection conn = getDbConnection();
        Statement statement=conn.createStatement();
        String query="select * from bookinfo";

        ResultSet rs=statement.executeQuery(query);

        while(rs.next()){
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
            bookInfoList.add(bookinfo);
        }
        return bookInfoList;

    }

    public String addbookInfo(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query="insert into bookinfo "
                + "values('"+bookinfo.getTitle()+"','"+bookinfo.getIsbn()+"','"+bookinfo.getAuthor()+"','"+bookinfo.getPublisher()+"','"+bookinfo.getCategories()+"','"
                +bookinfo.getSubcategories()+"','"+bookinfo.getYear()+"','"+bookinfo.getRating()+"',"+bookinfo.getNumcopies()+")";

        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }

    }

    public ObservableList<User> getAlluserInfo() throws SQLException, ClassNotFoundException {

        ObservableList<User> userInfoList=FXCollections.observableArrayList();

        Connection conn = getDbConnection();
        Statement statement=conn.createStatement();
        String query="select * from useraccounts";

        ResultSet rs=statement.executeQuery(query);

        while(rs.next()){
            String firstName=rs.getString("firstName");
            String lastName=rs.getString("lastName");
            String email=rs.getString("username");
            String password=rs.getString("password");
            String gender=rs.getString("gender");
            String phoneNumber=rs.getString("phoneNumber");
            String address=rs.getString("address");

            User userinfo=new User(firstName, lastName, email,password, gender, phoneNumber, address);
            userInfoList.add(userinfo);
        }
        return userInfoList;

    }

    public String updatebookInfo(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query = "UPDATE bookinfo "
                + "SET Title ='"+bookinfo.getTitle()+"',Author='"+bookinfo.getAuthor()+"',Publisher='"+bookinfo.getPublisher()+"',Categories='"+bookinfo.getCategories()+"',Subcategories='"+bookinfo.getSubcategories()
                +"',Year='"+bookinfo.getYear()+"',Rating='"+bookinfo.getRating()+"',numberOfCopies='"+bookinfo.getNumcopies()
                + "' WHERE ISBN = '"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        }
    }

    public ObservableList<IssuedBook> getAllissuedbooks() throws SQLException, ClassNotFoundException {

        ObservableList<IssuedBook> issuedbookList=FXCollections.observableArrayList();

        Connection conn = getDbConnection();
        Statement statement=conn.createStatement();
        String query="select * from issuedbooks";

        ResultSet rs=statement.executeQuery(query);

        while(rs.next()){
            String title=rs.getString("Title");
            String isbn=rs.getString("ISBN");
            String author=rs.getString("Author");
            String publisher=rs.getString("Publisher");

            IssuedBook bookinfo=new IssuedBook(title,isbn,author,publisher);
            issuedbookList.add(bookinfo);
        }
        return issuedbookList;

    }

    public String addissuedbooks(IssuedBook bookobj) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query="insert into issuedbooks "
                + "values('"+bookobj.getTitle()+"','"+bookobj.getIsbn()+"','"+bookobj.getAuthor()+"','"+bookobj.getPublisher()+"')";

        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }
    }
    public String removeissuedbooks(IssuedBook bookobj) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query="delete from issuedbooks WHERE ISBN = '"+bookobj.getIsbn()+"'";

        if(statement.executeUpdate(query)>0){
            return "Bookinfo added successfully";
        }else{
            return "failed";
        }
    }

    public String decrementCopies(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query= "UPDATE bookinfo "
                + "SET numberOfCopies=("+bookinfo.getNumcopies()+"-1)"
                + "WHERE ISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        }
    }

    public String incrementCopies(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        String query= "UPDATE bookinfo "
                + "SET numberOfCopies=("+bookinfo.getNumcopies()+"+1)"
                + "WHERE ISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "bookinfo updated successfully";
        }else{
            return "failed";
        }
    }

    public String deletebooks(ObservableList<BookInfo> selectedBooks) throws SQLException, ClassNotFoundException {
        Connection conn=getDbConnection();
        Statement statement=conn.createStatement();

        for(BookInfo bookinfo:selectedBooks){
            String query="delete from bookinfo WHERE ISBN = '"+bookinfo.getIsbn()+"'";
            statement.executeUpdate(query);
        }
        return null;
    }
}