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
      resultSet.close();
      
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
      resultSet.close();
      
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
      resultSet.close();
      
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by listings per country city postalcode");
      e.printStackTrace();
    }
    return result;
  }
  
  // ?
  public static List<List<String>> rankHostsNumberofListingsPerCountry(Connection connection) {
    String sql = "SELECT UH.Users_SIN, A.Country, COUNT(UH.Listing_ListingID) AS 'Number of Listings', RANK () OVER ( ORDER BY COUNT(UH.Listing_ListingID)) Ranking\n" + 
        " FROM Listing L, Address_has_Listing AL, Address A, Users_host_Listing UH\n" + 
        " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code AND UH.Listing_ListingID=L.ListingID\n" + 
        " GROUP BY UH.Users_SIN, A.Country";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by host rankings per country");
      e.printStackTrace();
    }
    return result;
  }
  
  // ?
  public static List<List<String>> rankHostsNumberofListingsPerCity(Connection connection) {
    String sql = "SELECT UH.Users_SIN, A.City, COUNT(UH.Listing_ListingID) AS 'Number of Listings', RANK () OVER ( ORDER BY COUNT(UH.Listing_ListingID)) Ranking\n" + 
        " FROM Listing L, Address_has_Listing AL, Address A, Users_host_Listing UH\n" + 
        " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code AND UH.Listing_ListingID=L.ListingID\n" + 
        " GROUP BY UH.Users_SIN, A.City";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by host rankings per city");
      e.printStackTrace();
    }
    return result;
  }
  
  // ?
  public static List<List<String>> hostWithMoreThanTenPercentPerCountry(Connection connection) {
    String sql = "SELECT UH.Users_SIN, A.Country, COUNT(UH.Listing_ListingID) AS 'Number of Hosted Listings', COUNT(L.ListingID) AS 'Total Number of Listing in Country'\n" + 
        " FROM Listing L, Address_has_Listing AL, Address A, Users_host_Listing UH\n" + 
        " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code AND UH.Listing_ListingID=L.ListingID\n" + 
        " GROUP BY UH.Users_SIN, A.Country\n" + 
        " HAVING COUNT(UH.Listing_ListingID)  >= 0.10*COUNT(L.ListingID)";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by hosts with more than 10% per country");
      e.printStackTrace();
    }
    return result;
  }
  
  //?
  public static List<List<String>> hostWithMoreThanTenPercentPerCity(Connection connection) {
   String sql = "SELECT UH.Users_SIN, A.City, COUNT(UH.Listing_ListingID) AS 'Number of Hosted Listings', COUNT(L.ListingID) AS 'Total Number of Listing in Country'\n" + 
       " FROM Listing L, Address_has_Listing AL, Address A, Users_host_Listing UH\n" + 
       " WHERE L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code AND UH.Listing_ListingID=L.ListingID\n" + 
       " GROUP BY UH.Users_SIN, A.City\n" + 
       " HAVING COUNT(UH.Listing_ListingID)  >= 0.10*COUNT(L.ListingID)";
   
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
     resultSet.close();
     
   } catch (SQLException e) {
     System.out.println("Couldnt query by hosts with more than 10% per city");
     e.printStackTrace();
   }
   return result;
 }
 
 //?
 public static List<List<String>> rankRentersDateRange(Connection connection, String date1, String date2) {
  String sql = " SELECT UR.Users_SIN,  COUNT(UR.Users_SIN) AS 'Number of Bookings', RANK () OVER ( ORDER BY COUNT(UR.Users_SIN)) Ranking\n" + 
      " FROM Listing L, Users_rent_Listing UR, Listing_has_Calendar LC\n" + 
      " WHERE L.ListingID=UR.Listing_ListingID AND LC.Listing_ListingID=L.ListingID AND (LC.Calendar_BeginDate BETWEEN ? AND ?) AND (LC.Calendar_EndDate BETWEEN ? AND ?)\n" + 
      " GROUP BY UR.Users_SIN";
  
  List<List<String>> result = new ArrayList<>();
  
  try {
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, date1);
    preparedStatement.setString(2, date2);
    preparedStatement.setString(3, date1);
    preparedStatement.setString(4, date2);
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
    resultSet.close();
    
  } catch (SQLException e) {
    System.out.println("Couldnt query by host rankings per city");
    e.printStackTrace();
  }
  return result;
  }

  public static List<List<String>> rankRentersDateRangePerCity(Connection connection) {
  String sql = " SELECT UR.Users_SIN,  A.City, COUNT(UR.Users_SIN) AS 'Number of Bookings', RANK () OVER ( ORDER BY COUNT(UR.Users_SIN)) Ranking\n" + 
      " FROM Listing L, Users_rent_Listing UR, Listing_has_Calendar LC, Address_has_Listing AL, Address A\n" + 
      " WHERE L.ListingID=UR.Listing_ListingID AND LC.Listing_ListingID=L.ListingID AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR) <= LC.Calendar_BeginDate\n" + 
      " AND L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code"
      + " GROUP BY UR.Users_SIN, A.City";
  
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
    resultSet.close();
    
  } catch (SQLException e) {
    System.out.println("Couldnt query by host rankings per city");
    e.printStackTrace();
  }
  return result;
  }
  
  public static List<List<String>> renterWithMostCancellations(Connection connection) {
    String sql = "SELECT U.SIN, U.Full_Name, U.Date_Of_Birth, RANK () OVER ( ORDER BY COUNT(UR.Users_SIN)) Ranking, COUNT(U.SIN) AS 'Number of Cancellations'\n" + 
        " FROM User_Cancel_Rent UCR, Users_rent_Listing UR, Users U, Listing L, Listing_has_Calendar LC\n" + 
        " WHERE UCR.User_rent_Listing_User_SIN=UR.Users_SIN AND UR.USERS_SIN=U.SIN AND LC.Listing_ListingID=L.ListingID AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR) <= LC.Calendar_BeginDate\n" + 
        " GROUP BY U.SIN\n" + 
        " LIMIT 0, 1";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by most rent cancels in a year");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> hosterWithMostCancellations(Connection connection) {
    String sql = "SELECT U.SIN, U.Full_Name, U.Date_Of_Birth, RANK () OVER ( ORDER BY COUNT(UR.Users_SIN)) Ranking, COUNT(U.SIN) AS 'Number of Cancellations'\n" + 
        " FROM User_Cancel_Host UCR, Users_Host_Listing UR, Users U, Listing L, Listing_has_Calendar LC\n" + 
        " WHERE UCR.User_Host_Listing_User_SIN=UR.Users_SIN AND UR.USERS_SIN=U.SIN AND LC.Listing_ListingID=L.ListingID AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR) <= LC.Calendar_BeginDate\n" + 
        " GROUP BY U.SIN\n" + 
        " LIMIT 0, 1";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by most host cancels in a year");
      e.printStackTrace();
    }
    return result;
  }
  
  public static List<List<String>> rankRentersDateRangePerYearWithMoreThanTwoBookings(Connection connection) {
    String sql = " SELECT UR.Users_SIN,  A.City, COUNT(UR.Users_SIN) AS 'Number of Bookings'\n" + 
        " FROM Listing L, Users_rent_Listing UR, Listing_has_Calendar LC, Address_has_Listing AL, Address A\n" + 
        " WHERE L.ListingID=UR.Listing_ListingID AND LC.Listing_ListingID=L.ListingID AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR) <= LC.Calendar_BeginDate\n" + 
        " AND L.ListingID=AL.Listing_ListingID AND AL.Address_Postal_Code=A.Postal_Code"
        + " GROUP BY UR.Users_SIN, A.City"
        + " HAVING COUNT(UR.Users_SIN) >= 2";
    
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
      resultSet.close();
      
    } catch (SQLException e) {
      System.out.println("Couldnt query by host rankings per city");
      e.printStackTrace();
    }
    return result;
    }
}
