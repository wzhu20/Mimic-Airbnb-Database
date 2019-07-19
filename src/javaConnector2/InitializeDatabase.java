package javaConnector2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class InitializeDatabase {

  /**
   * Initialize the database with it's first user and all tables setup.
   */
  public static void initialize() {

    Connection connection = DatabaseDriver.connectOrCreateDataBase();
    try {
      initializeDatabase(connection);
      initializeAmenTable(connection);
      initializeHomeTypes(connection);
      initializeOccupations(connection);
      initializeFirstUser(connection);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println("Unable to close connection");
      }
    }

  }

  /**
   * insert a second user into the database as a test.
   * 
   * @throws SQLException thrown if something goes wrong in inserting the user.
   * 
   *         public static void insert() throws SQLException { Connection connection =
   *         DatabaseDriver.connectOrCreateDataBase(); int userId = initializeFirstUser(connection);
   *         int accountId = initializeFirstUser(connection);
   * 
   *         associateAccount(userId, accountId, connection); connection.close(); }
   */
  /**
   * Update stuff in the database to validate update commands worked. DO NOT CALL THIS ON YOUR FINAL
   * CODE!
   * 
   * @throws SQLException thrown if something goes wrong.
   */
  public static void update() throws SQLException {
    Connection connection = DatabaseDriver.connectOrCreateDataBase();

    DatabaseUpdater.updateAccountBalance(new BigDecimal("99.92"), 1, connection);
    DatabaseUpdater.updateAccountName("New John", 1, connection);
    DatabaseUpdater.updateAccountType(2, 1, connection);
    DatabaseUpdater.updateAccountTypeInterestRate(new BigDecimal("0.4"), 1, connection);
    DatabaseUpdater.updateAccountTypeName("THIS IS BAD", 1, connection);
    DatabaseUpdater.updateRoleName("THIS TOO IS BAD", 1, connection);
    DatabaseUpdater.updateUserAddress("123 Four Five Street", 1, connection);
    DatabaseUpdater.updateUserAge(102, 1, connection);
    DatabaseUpdater.updateUserName("Sir Bob Marley", 1, connection);
    DatabaseUpdater.updateUserRole(2, 1, connection);

    connection.close();
  }

  private static void initializeDatabase(Connection connection) {
    try {
      DatabaseDriver.initialize(connection);
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
        DatabaseInserter.insertOccupation(occupationStr, connection);
      }
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
    String postalCode = "a1b2c3";
    String street = "fake";
    String city = "city";
    String dob = "Jan 1";
    int sin = 100;
    try {
      return DatabaseInserter.insertNewUser(sin, dob, name, null, null, connection);
    } catch (Exception e) {
      // TODO improve this.
      e.printStackTrace();
      return -1;
    }

  }
  /*
   * private static int initializeFirstAccount(Connection connection) { int id = 1; try { return
   * DatabaseInserter.insertAccount(name, balance, typeId, connection); } catch (Exception e) { //
   * TODO Improve this e.printStackTrace(); return -1; } }
   * 
   * 
   * private static void associateAccount(int userId, int accountId, Connection connection) { try {
   * System.out.println(userId + " " + accountId); DatabaseInserter.insertUserAccount(userId,
   * accountId, connection); } catch (Exception e) { e.printStackTrace(); }
   * 
   * }
   */


}
