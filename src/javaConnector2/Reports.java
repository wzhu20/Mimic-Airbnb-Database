package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reports {
  public static int countBookingsAddress(String postalCode, Connection connection) {
    String sql = "SELECT COUNT(*) AS 'Number of Listings in Area'"
               + " FROM Users_rent_Listing UR, Listing L, Address_has_Listing AL"
               + " WHERE UR.Listing_ListingID=L.ListingID AND L.ListingID=AL.Listing_ListingID"
               + " AND LEFT(AL.Address_Postal_Code, 3)=?";
    int result = 0;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postalCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
        result = resultSet.getInt(1);
      
      }
      
    } catch (SQLException e) {
      System.out.println("Couldnt count number of rented listings in area");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsCountry(Connection connection) {
    String sql = "SELECT A.Country, COUNT(A.Country) AS 'Number of Listings'"
               + " FROM Listing L, Address_has_Listing AL, Address A"
               + " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code"
               + " GROUP BY A.Country";
    
    List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = Queries.getColumnNames(resultMeta);
      result.add(columnName);
      
      while (resultSet.next()) {
        List<String> rowValues = new ArrayList<>();
        rowValues.add(resultSet.getString(1));
        rowValues.add(resultSet.getString(2));
        result.add(rowValues);
      }
      
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by listings per country");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsCountryCity(Connection connection) {
    String sql = "SELECT A.Country, A.City, COUNT(A.Country) AS 'Number of Listings'"
               + " FROM Listing L, Address_has_Listing AL, Address A"
               + " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code"
               + " GROUP BY A.Country, A.City";
    
    List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = Queries.getColumnNames(resultMeta);
      result.add(columnName);
      
      while (resultSet.next()) {
        List<String> rowValues = new ArrayList<>();
        rowValues.add(resultSet.getString(1));
        rowValues.add(resultSet.getString(2));
        rowValues.add(resultSet.getString(3));
        result.add(rowValues);
      }
      
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by listings per country city");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> countListingsCountryCityPostal(Connection connection) {
    String sql = "SELECT A.Country, A.City, A.Postal_Code, COUNT(A.Country) AS 'Number of Listings'"
               + " FROM Listing L, Address_has_Listing AL, Address A"
               + " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code"
               + " GROUP BY A.Country, A.City, A.Postal_Code";
    
    List<List<String>> result = new ArrayList<>();
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultMeta = resultSet.getMetaData();
      List<String> columnName = Queries.getColumnNames(resultMeta);
      result.add(columnName);
      
      while (resultSet.next()) {
        List<String> rowValues = new ArrayList<>();
        rowValues.add(resultSet.getString(1));
        rowValues.add(resultSet.getString(2));
        rowValues.add(resultSet.getString(3));
        rowValues.add(resultSet.getString(4));
        result.add(rowValues);
      }
      
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by listings per country city postalcode");
      e.printStackTrace();
    }
    return result;
  }
}
