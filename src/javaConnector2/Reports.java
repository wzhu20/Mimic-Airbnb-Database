package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {
  public static int countBookingsAddress(String postalCode, Connection connection) {
    String sql = "SELECT COUNT(*)"
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
}
