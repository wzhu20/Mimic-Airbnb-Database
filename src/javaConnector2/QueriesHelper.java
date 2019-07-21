package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QueriesHelper {
  
  public static List<List<String>> queryByAddress(String postalCode) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Queries.queryByAddress(postalCode, connection);
    try {
      connection.close();
    } catch (SQLException e) {
      System.err.println("Failed to close connection when querying postal code " + postalCode);
      e.printStackTrace();
    }
    return result;
    
  }

}
