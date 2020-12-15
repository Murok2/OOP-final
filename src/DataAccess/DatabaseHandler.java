package DataAccess;

import classes.User;

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
}