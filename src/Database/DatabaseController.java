package Database;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Owen Li on 14-11-08.
 */
public class DatabaseController {

    public void initializeDatabase() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        try {
            Connection connection = null;
            Statement stmt = null;

            try {
                // create a database connection
                connection = DriverManager.getConnection("jdbc:sqlite:user_data.db");
                stmt = connection.createStatement();
                stmt.executeUpdate("create table if not exists  Users (username String ,password String, realName String, Highscore int)");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {

                if (stmt != null) {
                    stmt.close();
                }

                if (connection != null) {
                    connection.close();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean createNewUser(String Uname, String pass, String rName) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet rsUserCheck = null;
            String sql = "INSERT INTO Users"
                    + "(USERNAME, PASSWORD, REALNAME, HIGHSCORE) VALUES"
                    + "(?,?,?,?)";
            String verify = "select * from Users where username = ?";

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:user_data.db");
                stmt = connection.prepareStatement(verify);
                stmt.setString(1, Uname);
                rsUserCheck = stmt.executeQuery();
                if (rsUserCheck.isBeforeFirst()) {
                    System.out.println("User already exists");
                    return true;
                }
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, Uname);
                stmt.setString(2, pass);
                stmt.setString(3, rName);
                stmt.executeUpdate();
                System.out.println("User is inserted into database");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean authenticateUser(String Uname, String pass) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try {
            Connection connection = null;
            ResultSet rsUser = null;
            PreparedStatement stmt = null;
            String usernameOnDB = null;
            String passwordOnDB = null;
            String sql = "select * from Users where username = ? and password = ? ";

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:user_data.db");
                //connection.setAutoCommit(false);
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, Uname);
                stmt.setString(2, pass);
                rsUser = stmt.executeQuery();

                if (!rsUser.isBeforeFirst()) {
                    System.out.println("Username/Password does not exist");
                }

                while (rsUser.next()) {
                    usernameOnDB = rsUser.getString("username");
                    passwordOnDB = rsUser.getString("password");
                    System.out.println("Verified Username : " + usernameOnDB);
                    System.out.println("Verified Password : " + passwordOnDB);
                }


                if (usernameOnDB != null && passwordOnDB != null) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rsUser != null) {
                    rsUser.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean updateInformation(String newPass, String newRealName, String Uname) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            Connection connection = null;
            PreparedStatement updatePasswordStmt = null;
            PreparedStatement updateRealNameStmt = null;
            PreparedStatement updateStmt;
            String sql = "update Users set password = ? where username = ?;";
            String sqlUpdateRealName = "update Users set realName = ? where username = ?;";
            String updateRecords = "SELECT * from Users where username = ?;";
            ResultSet rsUpdate = null;

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:user_data.db");
                //connection.setAutoCommit(false);
                updatePasswordStmt = connection.prepareStatement(sql);
                updatePasswordStmt.setString(1, newPass);
                updatePasswordStmt.setString(2, Uname);
                updatePasswordStmt.executeUpdate();

                updateRealNameStmt = connection.prepareStatement(sqlUpdateRealName);
                updateRealNameStmt.setString(1, newRealName);
                updateRealNameStmt.setString(2, Uname);
                updateRealNameStmt.executeUpdate();


                updateStmt = connection.prepareStatement(updateRecords);
                updateStmt.setString(1,Uname);
                rsUpdate = updateStmt.executeQuery();
                while (rsUpdate.next()) {
                    String updatedPassword = rsUpdate.getString("password");
                    String updatedRealName = rsUpdate.getString("realName");
                    System.out.println("Updated Password to : " + updatedPassword);
                    System.out.println("Updated Real Name to : " + updatedRealName);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rsUpdate != null) {
                    rsUpdate.close();
                }

                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}








