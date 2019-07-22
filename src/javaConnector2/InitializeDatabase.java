package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import dbDefaultData.Amenities;
import dbDefaultData.HomeType;
import dbDefaultData.Occupation;

public class InitializeDatabase {

  /**
   * Initialize the database with it's first user and all tables setup.
   */
  public static Connection initialize() {

    Connection connection = DatabaseDriver.connectOrCreateDataBase();
    System.out.println("connection successful");
    try {
      initializeDatabase(connection);
      initializeHomeTypes(connection);
      initializeAmenTable(connection);
      initializeOccupations(connection);
      initializeFirstUser(connection);
      return connection;
    } catch (Exception e) {
      e.printStackTrace();
      return connection;
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println("Unable to close connection");
      }
    }
  }

  private static void initializeDatabase(Connection connection) {
    try {
      DatabaseDriver.initialize(connection);
      System.out.println("db setup successful");
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private static void initializeAmenTable(Connection connection) {
    String amenStr = "";
    try {
      for (Amenities amenities : Amenities.values()) {
        amenStr = amenities.toString();
        DatabaseInserter.insertAmen(amenStr, connection);
      }
      System.out.println("amenities setup successful");
    } catch (Exception e) {
      // TODO Improve this block
      e.printStackTrace();
    }
  }

  private static void initializeHomeTypes(Connection connection) {
    String homeTypeStr = "";
    try {
      for (HomeType homeTypes : HomeType.values()) {
        homeTypeStr = homeTypes.toString();
        DatabaseInserter.insertHomeType(homeTypeStr, connection);
      }
      System.out.println("hometype setup successful");
    } catch (Exception e) {
      // TODO improve this block
      e.printStackTrace();
    }
  }

  private static void initializeOccupations(Connection connection) {
    String occupationStr = "";
    try {
      for (Occupation occ : Occupation.values()) {
        occupationStr = occ.toString();
        DatabaseInserter.insertOccupations(occupationStr, connection);
      }
      System.out.println("occupation setup successful");
    } catch (Exception e) {
      // TODO improve this block
      e.printStackTrace();
    }
  }

  /**
   * Add the first user to the database.
   * 
   * @param connection the connection to the database.
   */
  private static int initializeFirstUser(Connection connection) {
    String name = "John Smith";
    String dob = "Jan 1";
    String sin = "100";
    try {
      return DatabaseInserter.insertNewUser(sin, dob, name, connection);
    } catch (Exception e) {
      // TODO improve this.
      e.printStackTrace();
      return -1;
    }

  }

}
