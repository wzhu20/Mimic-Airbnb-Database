package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import exceptions.DatabaseInsertException;

public class DatabaseInsertHelper extends DatabaseInserter {

  public static int insertUser(String sin, String dob, String name, int host, int rent,
      int occupation) throws SQLException, DatabaseInsertException {
    /*
     * boolean cdt1 = Checker.checkValidUsersName(name); boolean cdt2 =
     * Checker.checkValidUsersAge(age); boolean cdt3 = Checker.checkValidUsersAddress(address);
     * boolean cdt4 = Checker.checkValidUsersRoleId(roleId);
     */
    // if it is a valid user, establish a connection
    // if (cdt1 && cdt2 && cdt3 && cdt4) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertUser(sin, dob, name, host, rent, occupation, connection);
    connection.close();
    return id;
    // }
    // return -1;

  }

  public static int insertHometype(String name) throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertHomeType(name, connection);
    connection.close();
    return id;
  }

  public static int insertAmen(String name) throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertAmen(name, connection);
    connection.close();
    return id;
  }

  public static int insertAddress(String postal, String city, String country) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertAddress(postal, city, country, connection);
    connection.close();
    return id;
  }

  public static int insertUserAddress(String postal, String sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertUserAddress(postal, sin, connection);
    connection.close();
    return id;
  }

  public static int insertHost() throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertNewHost(connection);
    connection.close();
    return id;
  }

  public static int insertRenter() throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertNewRenter(connection);
    connection.close();
    return id;
  }

  public static int insertOccupation(String occupations)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertOccupations(occupations, connection);
    connection.close();
    return id;
  }

  public static int insertCalendarDate(String begin, String end)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertCalendar(begin, end, connection);
    connection.close();
    return id;
  }

  public static int insertListing(float lat, float longit, int hometype)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertListing(lat, longit, hometype, connection);
    connection.close();
    return id;
  }

  public static int insertListingCalendar(int listingId, String begin, String end, Double double1)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertListingCalendar(listingId, begin, end, double1, connection);
    connection.close();
    return id;
  }

  public static int insertListingAmen(int listingId, int amen)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertListingAmen(listingId, amen, connection);
    connection.close();
    return id;
  }

  public static int insertHostListing(int sin, int listingId)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertHostListing(sin, listingId, connection);
    connection.close();
    return id;
  }

  public static int insertRentListing(int sin, int listingId, int credit)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertRentListing(sin, listingId, credit, connection);
    connection.close();
    return id;
  }

  public static int insertListingAddress(int listingId, String postal)
      throws DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertListingAddress(postal, listingId, connection);
    connection.close();
    return id;
  }

}
