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
    String sql = "SELECT * FROM USERS WHERE SIN = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, userId);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  protected static ResultSet getaddress(String postal, Connection connection) throws SQLException {
    String sql = "SELECT * FROM ADDRESS WHERE Postal_Code = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, postal);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  public static Boolean getaddressExists(String postal, Connection connection) throws SQLException {
    String sql = "SELECT * FROM ADDRESS WHERE Postal_Code = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, postal);
    ResultSet id = preparedStatement.executeQuery();
    boolean exists = id.first();
    id.close();
    return exists;
  }

  public static Boolean getListingExists(int listingId, Connection connection) throws SQLException {
    String sql = "SELECT * FROM Listing WHERE ListingId = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, listingId);
    ResultSet id = preparedStatement.executeQuery();
    boolean exists = id.first();
    id.close();
    return exists;
  }

  public static Boolean getHostListExists(int sin, int listingId, Connection connection)
      throws SQLException {
    String sql = "SELECT * FROM Users_Host_Listing WHERE Listing_ListingID = ? AND Users_SIN = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, listingId);
    preparedStatement.setInt(2, sin);
    ResultSet id = preparedStatement.executeQuery();
    boolean exists = id.first();
    id.close();
    return exists;
  }

  public static ResultSet getHostListing(int sin, Connection connection) throws SQLException {
    String sql = "SELECT * FROM Users_Host_Listing WHERE Users_SIN = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, sin);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  public static ResultSet getRentListing(int sin, Connection connection) throws SQLException {
    String sql = "SELECT * FROM Users_rent_Listing WHERE Users_SIN = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, sin);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  public static ResultSet getAvaliableHostListing(int sin, Connection connection)
      throws SQLException {
    String sql = "SELECT Users_Host_Listing.Listing_ListingID as Listing_ListingID "
        + "FROM Users_Host_Listing,Users_rent_Listing " + "WHERE Users_Host_Listing.Users_SIN = ? "
        + "AND Users_Host_Listing.Listing_ListingID <> Users_Host_Listing.Listing_ListingID";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, sin);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  public static ResultSet getAvaliableListing(Connection connection) throws SQLException {
    String sql = "SELECT Users_Host_Listing.Listing_ListingID "
        + "FROM Users_Host_Listing,Users_rent_Listing "
        + "WHERE Users_Host_Listing.Listing_ListingID <> Users_Host_Listing.Listing_ListingID";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

  public static ResultSet getListingDate(int listingId, Connection connection) throws SQLException {
    String sql = "SELECT * " + "FROM Listing_has_Calendar" + "WHERE Listing_ListingID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, listingId);
    ResultSet id = preparedStatement.executeQuery();
    return id;
  }

}
