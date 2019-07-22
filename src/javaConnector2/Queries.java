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
      List<String> columnName = getColumnNames(resultMeta);
      
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
  
  public static List<List<String>> queryAllListingsNearLocation(Connection connection) {
    String sql = "SELECT U.Full_Name, L.ListingID, L.Latitutude, L.Longitude, H1.Type AS 'HOUSE TYPE'"
               + " FROM Listing L, Users_Host_Listing UH, Users U, HomeType H1"
               + " WHERE UH.Users_SIN=U.SIN AND L.ListingID=UH.Listing_ListingID AND L.HomeType_idHomeType=H1.idHomeType";
    
    List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = getColumnNames(resultMeta);
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
      System.out.println("Couldnt query listings");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> queryPostalCodes(Connection connection) {
    String sql = "SELECT U.Full_Name, L.ListingID, L.Latitutude, L.Longitude, A1.Address_Postal_Code, H1.Type AS 'HOUSE TYPE'"
               + " FROM Address_has_Listing A1, Listing L, Users_Host_Listing UH, Users U, HomeType H1"
               + " WHERE A1.Listing_ListingID=L.ListingID AND L.ListingID=UH.Listing_ListingID"
               + " AND UH.Users_SIN=U.SIN AND L.HomeType_idHomeType=H1.idHomeType";
    
List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = getColumnNames(resultMeta);
      result.add(columnName);
      
      while (resultSet.next()) {
        List<String> rowValues = new ArrayList<>();
        rowValues.add(resultSet.getString(1));
        rowValues.add(resultSet.getString(2));
        rowValues.add(resultSet.getString(3));
        rowValues.add(resultSet.getString(4));
        rowValues.add(resultSet.getString(5));
        rowValues.add(resultSet.getString(6));
        result.add(rowValues);
      }
      
      
    } catch (SQLException e) {
      System.out.println("Couldnt query postal codes");
      e.printStackTrace();
    }
    return result;
  }
  
  // this doesn't work as intended right now...
  public static List<List<String>> queryListingAmenities(Connection connection, List<String> amenities) {
    String sql = "SELECT U.Full_Name, L.ListingID, L.Latitutude, L.Longitude, H1.Type AS 'HOUSE TYPE'"
               + " FROM Listing L, Listing_has_Amenities LA, Amenities A, HomeType H1, Users_Host_Listing UH, Users U"
               + " WHERE L.ListingID=LA.Listing_ListingID AND UH.Users_SIN=U.SIN AND L.HomeType_idHomeType=H1.idHomeType"
               + " AND LA.Amenities_idAmenities=A.idAmenities AND A.Item IN (";
    
    for (int i = 0; i < amenities.size(); i++) {
      sql += "?, ";
    }
    sql = sql.substring(0, sql.length() - 2);
    sql += ") GROUP BY L.ListingID, U.Full_Name, L.Latitutude, L.Longitude, 'HOUSE TYPE'";
    
    List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      for (int i = 0; i < amenities.size(); i++) {
        preparedStatement.setString(i+1, amenities.get(i));
      }
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = getColumnNames(resultMeta);
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
      System.out.println("Couldnt query by amenities");
      e.printStackTrace();
    }
    return result;
  }
  
  private static List<String> getColumnNames(ResultSetMetaData resultMeta) {
    List<String> columnName = new ArrayList<>();
    
      try {
        int count = resultMeta.getColumnCount();
        for(int i = 1; i <= count; i++) {
          
        columnName.add(resultMeta.getColumnLabel(i));
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    return columnName;
  }
}
