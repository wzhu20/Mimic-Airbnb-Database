package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import exceptions.DatabaseInsertException;


public class DatabaseInserter {
  /*
   * INSERT STATEMENTS
   */
  /**
   * Use this to insert new home types into the database.
   * 
   * @param home type to be added.
   * @param connection the database.
   * @return the id of the home type that was inserted.
   * @throws DatabaseInsertException on failure.
   */
  protected static int insertHomeType(String homeType, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO HomeType(TYPE) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, homeType);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          return uniqueKey.getInt(1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  /**
   * Use this to insert new Amenities into the database.
   * 
   * @param amenity to be added.
   * @param connection the database.
   * @return the id of the amenities that was inserted.
   * @throws DatabaseInsertException on failure.
   */
  protected static int insertAmen(String amenities, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO Amenities(Item) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, amenities);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          return uniqueKey.getInt(1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  /**
   * Use this to insert new occupations into the database.
   * 
   * @param occupation to be added.
   * @param connection the database.
   * @return the id of the occupations that was inserted.
   * @throws DatabaseInsertException on failure.
   */
  protected static int insertOccupations(String occupations, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO Users_has_Occupation(Occupation_name) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, occupations);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          return uniqueKey.getInt(1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  /**
   * Use this to insert a new user.
   * 
   * @param name the user's name.
   * @param age the user's age.
   * @param address the user's address.
   * @param roleId the user's role.
   * @param password the user's password (not hashsed).
   * @param connection the database connection.
   * @return the account id
   * @throws DatabaseInsertException if there is a failure on the insert
   */
  protected static int insertNewUser(String sin, String dob, String name, Connection connection)
      throws DatabaseInsertException {
    int id = insertUser(sin, dob, name, -1, -1, 1, connection);
    // TODO ADD INSERT ADDRESS FUNCTIONALITY
    return id;
  }

  protected static int insertUserAddress(String postal, String sin, Connection connection) {
    String sql = "INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, sin);
      preparedStatement.setString(2, postal);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;

  }

  protected static int insertAddress(String postal, String city, String country,
      Connection connection) {
    String sql = "INSERT INTO address (Postal_code, city, country) VALUES(?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postal);
      preparedStatement.setString(2, city);
      preparedStatement.setString(3, country);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * insert an accountType into the accountType table.
   * 
   * @param name the name of the type of account.
   * @param interestRate the interest rate for this type of account.
   * @param connection the database connection.
   * @return the id of the accountType.
   * @throws DatabaseInsertException on failure
   * 
   *         protected static int insertAccountType(String name, BigDecimal interestRate, Connection
   *         connection) throws DatabaseInsertException { String sql = "INSERT INTO
   *         ACCOUNTTYPES(NAME,INTERESTRATE) VALUES(?,?)";; try { PreparedStatement
   *         preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
   *         preparedStatement.setString(1, name); preparedStatement.setString(2,
   *         interestRate.toPlainString()); int id = preparedStatement.executeUpdate(); if (id > 0)
   *         { ResultSet uniqueKey = preparedStatement.getGeneratedKeys(); if (uniqueKey.next()) {
   *         return uniqueKey.getInt(1); } } } catch (Exception e) { e.printStackTrace(); }
   * 
   *         throw new DatabaseInsertException(); }
   */
  /**
   * Insert a new account into account table.
   * 
   * @param name the name of the account.
   * @param balance the balance currently in account.
   * @param typeId the id of the type of the account.
   * @param connection the database connection.
   * @return accountId of inserted account.
   * @throws DatabaseInsertException on failure of insert.
   * 
   *         protected static int insertAccount(String name, BigDecimal balance, int typeId,
   *         Connection connection) throws DatabaseInsertException { String sql = "INSERT INTO
   *         ACCOUNTS(NAME,BALANCE,TYPE) VALUES(?,?,?)"; try { PreparedStatement preparedStatement =
   *         connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
   *         preparedStatement.setString(1, name); preparedStatement.setString(2,
   *         balance.toPlainString()); preparedStatement.setInt(3, typeId); int id =
   *         preparedStatement.executeUpdate(); if (id > 0) { ResultSet uniqueKey =
   *         preparedStatement.getGeneratedKeys(); if (uniqueKey.next()) { return
   *         uniqueKey.getInt(1); } } } catch (Exception e) { e.printStackTrace(); } throw new
   *         DatabaseInsertException(); }
   */
  /**
   * insert a user and account relationship.
   * 
   * @param userId the id of the user.
   * @param accountId the id of the account.
   * @param connection the database connection.
   * @return id of the USERACCOUNT.
   * @throws DatabaseInsertException on failure of insert.
   */
  /*
   * protected static int insertUserAccount(int userId, int accountId, Connection connection) throws
   * DatabaseInsertException { String sql =
   * "INSERT INTO USERACCOUNT(USERID,ACCOUNTID) VALUES(?,?);"; try { PreparedStatement
   * preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
   * preparedStatement.setInt(1, userId); preparedStatement.setInt(2, accountId); int id =
   * preparedStatement.executeUpdate(); if (id > 0) { ResultSet uniqueKey =
   * preparedStatement.getGeneratedKeys(); if (uniqueKey.next()) { return uniqueKey.getInt(1); } } }
   * catch (Exception e) { e.printStackTrace(); } throw new DatabaseInsertException(); }
   * 
   * private static boolean insertPassword(String password, int userId, Connection connection) {
   * String sql = "INSERT INTO USERPW(USERID, PASSWORD) VALUES(?,?);"; try { password =
   * PasswordHelpers.passwordHash(password); PreparedStatement preparedStatement =
   * connection.prepareStatement(sql); preparedStatement.setInt(1, userId);
   * preparedStatement.setString(2, password); preparedStatement.executeUpdate(); } catch (Exception
   * e) { e.printStackTrace(); } return false; }
   */
  protected static int insertUser(String sin, String dob, String name, int hostId, int rentId,
      Integer occupation, Connection connection) {
    String sql = "INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile, "
        + "Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(?,?,?,?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, sin);
      preparedStatement.setString(2, dob);
      preparedStatement.setString(3, name);
      if (hostId == -1)
        preparedStatement.setNull(4, java.sql.Types.INTEGER);
      else
        preparedStatement.setInt(4, hostId);
      if (hostId == -1)
        preparedStatement.setNull(5, java.sql.Types.INTEGER);
      else
        preparedStatement.setInt(5, rentId);
      preparedStatement.setInt(6, occupation);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }
}
