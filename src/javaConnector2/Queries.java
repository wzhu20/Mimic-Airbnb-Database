package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Queries {
  
  public static List<List<String>> queryByAddress(String postalCode, Connection connection) {
    String sql = "SELECT U2.Full_Name, L1.ListingID AS ID, L1.Latitutude, L1.Longitude, H1.Type AS 'HOUSE TYPE'"
               + " FROM  Listing as L1"
               + " INNER JOIN"
               + " (SELECT Listing_ListingID FROM Address_has_Listing WHERE Address_Postal_Code=?) as L2"
               + " ON L1.ListingID=L2.Listing_ListingID"
               + " INNER JOIN"
               + " (SELECT Users_SIN, Listing_ListingID FROM Users_Host_Listing) as U1"
               + " on L2.Listing_ListingID=U1.Listing_ListingID"
               + " INNER JOIN"
               + " (SELECT Full_Name, SIN FROM Users) as U2"
               + " on U1.Users_SIN=U2.SIN"
               + " INNER JOIN"
               + " (Select idHomeType, Type FROM HomeType) as H1"
               + " on L1.HomeType_idHomeType=H1.idHomeType";
    PreparedStatement preparedStatement;
    List<List<String>> result = new ArrayList<>();
    
    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postalCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      
      // columns
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      int count = resultMeta.getColumnCount();
      List<String> columnName = new ArrayList<>();
      
      for(int i = 1; i <= count; i++) {
        columnName.add(resultMeta.getColumnLabel(i));
      }
      
      result.add(columnName);
      
      while (resultSet.next()) {
        List<String> rowValues = new ArrayList<>();
        rowValues.add(resultSet.getString(1));
        rowValues.add(resultSet.getString(2));
        rowValues.add(resultSet.getString(3));
        rowValues.add(resultSet.getString(4));
        rowValues.add(resultSet.getString(5));
        result.add(rowValues);
      }
    } catch (SQLException e) {
      System.out.println("Couldnt query by " + postalCode);
      e.printStackTrace();
    }
    return result;
  }
}
