package javaConnector2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInsertHelper extends DatabaseInserter {

  /**
   * Inserts an account into database.
   *
   * @param name the name of the account.
   * @param balance the balance of the account.
   * @param typeId the type ID of the account.
   * @return 0 if successful, -1 otherwise.
   * @SQLException if the connection is unsuccessful.
   */
  public static int insertAccount(String name, BigDecimal balance, int typeId)
      throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidAccountsName(name);
    boolean cdt2 = Checker.checkValidAccountsBalance(balance);
    boolean cdt3 = Checker.checkValidAccountsType(typeId);
    // if it is a valid account, establish a connection
    balance.setScale(2, RoundingMode.HALF_UP);
    if (cdt1 && cdt2 && cdt3) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int id = DatabaseInserter.insertAccount(name, balance, typeId, connection);
      connection.close();
      return id;
    }
    return -1;
  }

  /**
   * Inserts an account type into database.
   *
   * @param name the name of the account type.
   * @param interestRate the interest rate of the account type.
   * @return true if successful, false otherwise.
   * @SQLException if the connection is unsuccessful.
   */
  public static int insertAccountType(String name, BigDecimal interestRate)
      throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidAccountTypesName(name);
    boolean cdt2 = Checker.checkValidAccountTypesInterestRate(interestRate);
    interestRate.setScale(2, RoundingMode.HALF_UP);
    // if it is a valid account type, establish a connection
    if (cdt1 && cdt2) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int result = DatabaseInserter.insertAccountType(name, interestRate, connection);
      connection.close();
      return result;
    }
    return -1;
  }

  /**
   * Inserts a new user into the database.
   *
   * @param name the name of the user.
   * @param age the age of the user.
   * @param address the address of the user.
   * @param roleId the role ID of the user.
   * @param password the password of the user.
   * @return id of the user if successful, -1 otherwise.
   * @SQLException if the connection is unsuccessful.
   */
  public static int insertNewUser(String name, int age, String address, int roleId, String password)
      throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidUsersName(name);
    boolean cdt2 = Checker.checkValidUsersAge(age);
    boolean cdt3 = Checker.checkValidUsersAddress(address);
    boolean cdt4 = Checker.checkValidUsersRoleId(roleId);
    // if it is a valid user, establish a connection
    if (cdt1 && cdt2 && cdt3 && cdt4) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int id = DatabaseInserter.insertNewUser(name, age, address, roleId, password, connection);
      connection.close();
      return id;
    }

    return -1;

  }

  /**
   * Inserts a role into the database.
   *
   * @param role the role to be inserted.
   * @return true if successful, false otherwise.
   * @SQLException if the connection is unsuccessful.
   */
  public static int insertRole(String role) throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidRolesName(role);
    // if it is a valid role, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int result = DatabaseInserter.insertRole(role, connection);
      connection.close();
      return result;
    }
    return -1;
  }

  /**
   * Inserts a new user account into the database.
   *
   * @param userId the user ID.
   * @param accountId the account ID.
   * @return true if successful, false otherwise.
   * @SQLException if the connection is unsuccessful.
   */
  public static int insertUserAccount(int userId, int accountId)
      throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidUserAccountUserId(userId);
    boolean cdt2 = Checker.checkValidUserAccountAccountId(accountId, userId);
    // if it is a valid user id and account id, establish a connection
    if (cdt1 && cdt2) {
      if (DatabaseSelectHelper.getAccountIds(userId).contains(accountId)) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
        int result = DatabaseInserter.insertUserAccount(userId, accountId, connection);
        connection.close();
        return result;
      }
    }
    return -1;
  }


}
