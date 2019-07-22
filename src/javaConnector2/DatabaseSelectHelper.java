package javaConnector2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bank.bank.accounts.Account;
import com.bank.bank.accounts.ChequingAccount;
import com.bank.bank.accounts.ChequingAccountImpl;
import com.bank.bank.accounts.SavingsAccount;
import com.bank.bank.accounts.SavingsAccountImpl;
import com.bank.bank.accounts.TFSA;
import com.bank.bank.accounts.TFSAImpl;
import com.bank.bank.user.Admin;
import com.bank.bank.user.AdminImpl;
import com.bank.bank.user.Customer;
import com.bank.bank.user.CustomerImpl;
import com.bank.bank.user.Teller;
import com.bank.bank.user.TellerImpl;
import com.bank.bank.user.User;
import exceptions.DatabaseInsertException;

public class DatabaseSelectHelper extends DatabaseSelector {

  public static ResultSet getAllOccupations() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM users_has_Occupation;");
    return results;
  }

  /**
   * Gets the role of the user based on their id.
   *
   * @param id the user's id.
   * @return the users role if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static String getRole(int id) throws SQLException {
    boolean cdt1 = Checker.checkValidRolesId(id);
    // if it is a valid role id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      String role = DatabaseSelector.getRole(id, connection);
      connection.close();
      return role;
    }
    return null;
  }

  /**
   * Gets the hashed password of the user based on their id.
   *
   * @param userId the user's id.
   * @return the users hashed password if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static String getPassword(int userId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountUserId(userId);
    // if it is a valid user id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      String hashPassword = DatabaseSelector.getPassword(userId, connection);
      connection.close();
      return hashPassword;
    }
    return null;
  }

  /**
   * Gets the details of the user based on their id.
   *
   * @param userId the user's id.
   * @return the user object if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static User getUserDetails(int userId) throws SQLException, DatabaseInsertException {
    boolean cdt1 = Checker.checkValidUserAccountUserId(userId);
    // if it is a valid user account user id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserDetails(userId, connection);

      while (results.next()) {
        // acquire the name, address, and role id of the user
        String name = results.getString("NAME");
        int age = results.getInt("AGE");
        String address = results.getString("ADDRESS");
        int roleId = results.getInt("ROLEID");
        // get the role of the user
        String roleCorrespond = getRole(roleId);
        // assign the corresponding role to make the appropriate user class
        if (roleCorrespond.equalsIgnoreCase("ADMIN")) {
          Admin admin = new AdminImpl(userId, name, age, address, roleId);
          connection.close();
          return admin;
        } else if (roleCorrespond.equalsIgnoreCase("TELLER")) {
          Teller teller = new TellerImpl(userId, name, age, address, roleId);
          connection.close();
          return teller;
        } else if (roleCorrespond.equalsIgnoreCase("CUSTOMER")) {
          Customer customer = new CustomerImpl(userId, name, age, address, roleId);
          connection.close();
          return customer;
        }

      }

    }
    return null;
  }

  /**
   * Gets the account IDs of the user based on their id.
   *
   * @param userId the user's id.
   * @return the list of the user's account ID if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static List<Integer> getAccountIds(int userId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountUserId(userId);
    // if it is a valid user id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
      List<Integer> idList = new ArrayList<Integer>();
      connection.close();
      // add all of the account ids into the arraylist
      while (results.next()) {
        idList.add(results.getInt("ACCOUNTID"));
      }
      return idList;
    }
    return null;

  }

  /**
   * Gets the account details of the account on the id.
   *
   * @param accountId the account id.
   * @return the user's account if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static Account getAccountDetails(int accountId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountAccountId(accountId);
    // if it is a account id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getAccountDetails(accountId, connection);
      while (results.next()) {
        // get the account details
        String name = results.getString("NAME");
        BigDecimal balance = new BigDecimal(results.getString("BALANCE"));
        // make an account class (either chequing, savings, or tfsa) based on the type
        if (results.getString("TYPE").equals("CHEQUING")) {
          ChequingAccount chequing = new ChequingAccountImpl(accountId, name, balance);
          connection.close();
          return chequing;
        } else if (results.getString("TYPE").equals("SAVING")) {
          SavingsAccount savings = new SavingsAccountImpl(accountId, name, balance);
          connection.close();
          return savings;
        } else if (results.getString("TYPE").equals("TFSA")) {
          TFSA tfsa = new TFSAImpl(accountId, name, balance);
          connection.close();
          return tfsa;
        }

      }
    }
    return null;
  }

  /**
   * Gets the balance of the account based on the id.
   *
   * @param accountId the account id.
   * @return the balance of the account if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static BigDecimal getBalance(int accountId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountAccountId(accountId);
    // if it is a valid account id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      BigDecimal balance = DatabaseSelector.getBalance(accountId, connection);
      connection.close();
      return balance;
    }
    return null;
  }

  /**
   * Gets the interest rate of the account based on the id.
   *
   * @param accountType the type of the account.
   * @return the interest rate of that account type if successful, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static BigDecimal getInterestRate(int accountType) throws SQLException {
    boolean cdt1 = Checker.checkValidAccountsType(accountType);
    // if it is a valid account type, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      BigDecimal interestRate = DatabaseSelector.getInterestRate(accountType, connection);
      connection.close();
      return interestRate;
    }
    return null;
  }

  /**
   * Gets a list of account type IDs.
   *
   * @return the list of the account type IDs.
   * @throws SQLException if connection is unsuccessful.
   */
  public static List<Integer> getAccountTypesIds() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAccountTypesId(connection);
    List<Integer> ids = new ArrayList<>();
    // add the account types ids to the arraylist
    while (results.next()) {
      ids.add(results.getInt("ID"));
    }
    connection.close();
    return ids;
  }

  /**
   * Gets account type name based on the account type ID.
   *
   * @return the account type name, null otherwise.
   * @throws SQLException if connection is unsuccessful.
   */
  public static String getAccountTypeName(int accountTypeId) throws SQLException {
    boolean cdt1 = Checker.checkValidAccountsType(accountTypeId);
    // establish a connection
    String accountTypeName = null;
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    // if it is a valid account type id
    if (cdt1) {

      accountTypeName = DatabaseSelector.getAccountTypeName(accountTypeId, connection);

    }
    connection.close();
    return accountTypeName;
  }

  /**
   * Gets a list of roles.
   *
   * @return the list of the roles.
   * @throws SQLException if connection is unsuccessful.
   */
  public static List<Integer> getRoles() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRoles(connection);
    List<Integer> ids = new ArrayList<>();
    // adds the roles to the arraylist
    while (results.next()) {
      ids.add(results.getInt("ID"));
    }
    connection.close();
    return ids;

  }

  /**
   * Gets the account type of the account based on the account ID.
   *
   * @param accountId the account's ID
   * @return the type of the account if successful, -1 if otherwise.
   * @throws SQLException if the connection is unsuccessful.
   */
  public static int getAccountType(int accountId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountAccountId(accountId);
    // if it is a valid account id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int accountType = DatabaseSelector.getAccountType(accountId, connection);
      connection.close();
      return accountType;
    }
    return -1;

  }

  /**
   * Gets the user role of the user based on their ID.
   *
   * @param userId the user's ID
   * @return the integer representation of the role if successful, -1 if otherwise.
   * @throws SQLException if the connection is unsuccessful.
   */
  public static int getUserRole(int userId) throws SQLException {
    boolean cdt1 = Checker.checkValidUserAccountUserId(userId);
    // if it is a valid user account user id, establish a connection
    if (cdt1) {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int userRole = DatabaseSelector.getUserRole(userId, connection);
      connection.close();
      return userRole;
    }
    return -1;
  }

}
