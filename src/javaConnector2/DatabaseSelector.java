package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelector {

  /*
   * SELECT FUNCTIONS
   */

  protected static ResultSet getOccupations(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM Users_has_address;");
    return results;
  }


  /**
   * find all the details about a given user.
   * 
   * @param userId the id of the user.
   * @param connection a connection to the database.
   * @return a result set with the details of the user.
   * @throws SQLException thrown when something goes wrong with query.
   */
  protected static ResultSet getUserDetails(int userId, Connection connection) throws SQLException {
    String sql = "SELECT * FROM USERS WHERE ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, userId);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  protected static Boolean getaddressExists(String postal, Connection connection)
      throws SQLException {
    String sql = "SELECT * FROM ADDRESS WHERE Postal_Code = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, postal);
    ResultSet id = preparedStatement.executeQuery();
    boolean exists = id.first();
    id.close();
    return exists;
  }

  protected static ResultSet getaddress(String postal, Connection connection) throws SQLException {
    String sql = "SELECT * FROM ADDRESS WHERE Postal_Code = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, postal);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

}
