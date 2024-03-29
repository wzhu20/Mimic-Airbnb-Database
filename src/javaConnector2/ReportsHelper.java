package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportsHelper {
  public static int countBookingsByAddress(String postalCode) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int result = Reports.countBookingsAddress(postalCode, connection);
    try {
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsByCountries() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.countListingsCountry(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsByCountriesCity() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.countListingsCountryCity(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsByCountriesCityPostal() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.countListingsCountryCityPostal(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankHostsByNumberofListingsPerCountry() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.rankHostsNumberofListingsPerCountry(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankHostsByNumberofListingsPerCity() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.rankHostsNumberofListingsPerCountry(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> hostWithMoreThanTenPercentByCountry() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.hostWithMoreThanTenPercentPerCountry(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> hostWithMoreThanTenPercentByCity() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.hostWithMoreThanTenPercentPerCity(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankRentersByNumberOfRentsInDateRange(String date1, String date2) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.rankRentersDateRange(connection, date1, date2);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankRentersByNumberOfRentsInDateRangePerCity() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.rankRentersDateRangePerCity(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> RentersWithMostCancellaionsInAYear() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.renterWithMostCancellations(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> HostersWithMostCancellaionsInAYear() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.hosterWithMostCancellations(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankRentersDateRangePerYearWithMoreThanTwoBookings() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Reports.rankRentersDateRangePerYearWithMoreThanTwoBookings(connection);
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
