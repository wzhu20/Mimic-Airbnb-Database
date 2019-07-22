package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
  
  public static List<List<String>> queryByLocations(float lat1, float lon1, float distance) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Queries.queryAllListingsNearLocation(connection);
    
    List<List<String>> listingsNear = new ArrayList<>();
    listingsNear.add(result.get(0)); // put column titles
    for (int i = 1; i < result.size(); i++) {
      Float lat2 = Float.parseFloat(result.get(i).get(2));
      Float lon2 = Float.parseFloat(result.get(i).get(3));
      if (haversine(lat1, lon1, lat2, lon2) < distance) {
        listingsNear.add(result.get(i));
      }
    }
    return listingsNear;
  }
  
  public static List<List<String>> queryByLocations(float lat1, float lon1) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Queries.queryAllListingsNearLocation(connection);
    
    List<List<String>> listingsNear = new ArrayList<>();
    listingsNear.add(result.get(0)); // put column titles
    for (int i = 1; i < result.size(); i++) {
      Float lat2 = Float.parseFloat(result.get(i).get(2));
      Float lon2 = Float.parseFloat(result.get(i).get(3));
      if (haversine(lat1, lon1, lat2, lon2) < 2000) {
        listingsNear.add(result.get(i));
      }
    }
    return listingsNear;
  }
  
  public static List<List<String>> queryByAdjacentPostalCodes(String postalCode) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<List<String>> result = Queries.getPostalCodes(connection);
    
    List<List<String>> adjacentListing = new ArrayList<>();
    adjacentListing.add(result.get(0)); // put column titles
    for (int i = 1; i < result.size(); i++) {
      String pc = result.get(i).get(4);
      if (postalCode.substring(0, 2).equalsIgnoreCase(pc.substring(0, 2))) {
        adjacentListing.add(result.get(i));
      }
    }
    return adjacentListing;
  }
  // THIS CODE WAS TAKEN FROM http://rosettacode.org/wiki/Haversine_formula#Java
  // TO CALCULATE latitude and longitudes in a certain distance
  private static final double R = 6372.8; // In kilometers
  private static double haversine(double lat1, double lon1, double lat2, double lon2) {
      double dLat = Math.toRadians(lat2 - lat1);
      double dLon = Math.toRadians(lon2 - lon1);
      lat1 = Math.toRadians(lat1);
      lat2 = Math.toRadians(lat2);

      double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
      double c = 2 * Math.asin(Math.sqrt(a));
      return R * c;
  }

}
