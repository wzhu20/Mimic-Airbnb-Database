package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Checker {

  public static boolean checkHostHasListing(int sin, int listingId) {
    boolean output = true;
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    try {
      boolean listingExist = DatabaseSelector.getListingExists(listingId, connection);
      boolean hostHasListing = DatabaseSelector.getHostListExists(sin, listingId, connection);
      if (!(listingExist && hostHasListing)) {
        output = false;
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
      output = false;
    }
    return output;
  }

  public static boolean checkValidRentPrice(Double rent) {
    boolean output = true;
    if (rent <= 0) {
      output = false;
    }
    return output;
  }

  public static boolean checkValidSin(int sin) {
    boolean output = true;
    if (sin <= 100000 || sin > 999999) {
      output = false;
    }
    return output;
  }

  public static boolean checkValidPostal(String postal) {
    boolean output = true;
    if (postal.length() != 6) {
      output = false;
    }
    return output;
  }

  // GIVEN BY https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
  public static boolean checkValidDate(String date) {
    try {
      DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
      df.setLenient(false);
      df.parse(date);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

}
