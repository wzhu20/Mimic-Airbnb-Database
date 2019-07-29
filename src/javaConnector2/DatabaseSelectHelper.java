package javaConnector2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import exceptions.DatabaseInsertException;
import userCommands.User;


public class DatabaseSelectHelper extends DatabaseSelector {

  public static ResultSet getAllOccupations() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM users_has_Occupation;");
    return results;
  }

  public static ResultSet getAllHostList(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getHostListing(sin, connection);
    return results;
  }

  public static ResultSet getAllRentList(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRentListing(sin, connection);
    return results;
  }

  public static ResultSet getAllAvaliableHostList(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAvaliableHostListing(sin, connection);
    return results;
  }

  public static ResultSet getAllAvaliableListing() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAvaliableListing(connection);
    return results;
  }

  public static ResultSet getAllListingDate(int listingId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getListingDate(listingId, connection);
    return results;
  }


  public static ResultSet getAllHometypes() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM HomeType;");
    return results;
  }

  public static ResultSet getAllAmen() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM Amenities;");
    return results;
  }

  public static ResultSet getHostasRenter(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getHostasRenter(sin, connection);
    return results;
  }

  public static ResultSet getRenterasHost(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRenterasHost(sin, connection);
    return results;
  }

  public static int getRenterProfile(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRenterasHost(sin, connection);
    int id = results.getInt("Renter_Profile_idRenter_Profile");
    results.close();
    connection.close();
    return id;
  }

  public static int getHostProfile(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getHostProfile(sin, connection);
    int id = results.getInt("Host_Profile_idHost_Profile");
    results.close();
    connection.close();
    return id;
  }

  public static int getSinFromHostId(int hostId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSinFromHostId(hostId, connection);
    int id = -1;
    while (results.next()) {
      id = results.getInt("SIN");
    }
    results.close();
    connection.close();
    return id;
  }

  public static int getSinFromRentId(int rentId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSinFromRentId(rentId, connection);
    int id = -1;
    while (results.next()) {
      id = results.getInt("SIN");
    }
    results.close();
    connection.close();
    return id;
  }


  /**
   * Gets the details of the user based on their id.
   *
   * @param userId the user's id.
   * @return the user object if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static User getUserDetails(int userId) throws SQLException, DatabaseInsertException {
    boolean cdt1 = true; // Checker.checkValidUserAccountUserId(userId);
    // if it is a valid user account user id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserDetails(userId, connection);

      while (results.next()) {
        int sin = results.getInt("SIN");
        String name = results.getString("FULL_NAME");
        String dob = results.getString("Date_of_Birth");
        int hostId = results.getInt("Host_Profile_idHost_Profile");
        int rentId = results.getInt("Renter_Profile_idRenter_Profile");
        int occId = results.getInt("Occupation");
        User user = new User(sin, name, dob, hostId, rentId, occId);
        connection.close();
        return user;
      }

    }
    return null;
  }



}
