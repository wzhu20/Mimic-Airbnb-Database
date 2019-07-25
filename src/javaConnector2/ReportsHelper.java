package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;

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
}
