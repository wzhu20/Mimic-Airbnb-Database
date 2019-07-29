package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseUpdater {

  protected static boolean deleteUser(int sin, Connection connection) {
    String sql = "DELETE FROM USERS WHERE SIN = ?;";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.executeUpdate();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  protected static boolean deleteListing(int listingId, Connection connection) {
    String sql = "DELETE FROM listing WHERE ListingId = ?;";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, listingId);
      preparedStatement.executeUpdate();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  protected static boolean updateListingPrice(int listingId, String begin, String end,
      double rental, Connection connection) {
    String sql = "UPDATE Listing_has_Calendar SET RentalPrice = ?"
        + " WHERE Listing_ListingID = ? AND Calendar_BeginDate = ? AND Calendar_EndDate = ?;";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1, rental);
      preparedStatement.setInt(2, listingId);
      preparedStatement.setDate(3, java.sql.Date.valueOf(begin));
      preparedStatement.setDate(4, java.sql.Date.valueOf(end));
      preparedStatement.executeUpdate();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  protected static boolean updateCancelRent(int sin, int listingId, String begin, String end,
      Connection connection) {
    String sql =
        "DELETE FROM users_rent_listing WHERE Users_SIN = ? AND Listing_ListingID = ? AND Calendar_BeginDate = ? AND Calendar_EndDate = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.setInt(2, listingId);
      preparedStatement.setDate(3, java.sql.Date.valueOf(begin));
      preparedStatement.setDate(4, java.sql.Date.valueOf(end));
      preparedStatement.executeUpdate();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
