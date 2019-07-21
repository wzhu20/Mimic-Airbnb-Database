package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInsertHelper extends DatabaseInserter {

  public static int insertUser(String sin, String dob, String name, int occupation)
      throws SQLException, DatabaseInsertException {
    /*
     * boolean cdt1 = Checker.checkValidUsersName(name); boolean cdt2 =
     * Checker.checkValidUsersAge(age); boolean cdt3 = Checker.checkValidUsersAddress(address);
     * boolean cdt4 = Checker.checkValidUsersRoleId(roleId);
     */
    // if it is a valid user, establish a connection
    // if (cdt1 && cdt2 && cdt3 && cdt4) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertUser(sin, dob, name, -1, -1, occupation, connection);
    connection.close();
    return id;
    // }

    // return -1;

  }
  /*
   * 
   * public static int insertAccount(String name, BigDecimal balance, int typeId) throws
   * SQLException, DatabaseInsertException { boolean cdt1 = Checker.checkValidAccountsName(name);
   * boolean cdt2 = Checker.checkValidAccountsBalance(balance); boolean cdt3 =
   * Checker.checkValidAccountsType(typeId); // if it is a valid account, establish a connection
   * balance.setScale(2, RoundingMode.HALF_UP); if (cdt1 && cdt2 && cdt3) { Connection connection =
   * DatabaseDriverHelper.connectOrCreateDataBase(); int id = DatabaseInserter.insertAccount(name,
   * balance, typeId, connection); connection.close(); return id; } return -1; }
   * 
   * 
   * public static int insertAccountType(String name, BigDecimal interestRate) throws SQLException,
   * DatabaseInsertException { boolean cdt1 = Checker.checkValidAccountTypesName(name); boolean cdt2
   * = Checker.checkValidAccountTypesInterestRate(interestRate); interestRate.setScale(2,
   * RoundingMode.HALF_UP); // if it is a valid account type, establish a connection if (cdt1 &&
   * cdt2) { Connection connection = DatabaseDriverHelper.connectOrCreateDataBase(); int result =
   * DatabaseInserter.insertAccountType(name, interestRate, connection); connection.close(); return
   * result; } return -1; }
   * 
   * 
   * public static int insertRole(String role) throws SQLException, DatabaseInsertException {
   * boolean cdt1 = Checker.checkValidRolesName(role); // if it is a valid role, establish a
   * connection if (cdt1) { Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
   * int result = DatabaseInserter.insertRole(role, connection); connection.close(); return result;
   * } return -1; }
   * 
   * 
   * public static int insertUserAccount(int userId, int accountId) throws SQLException,
   * DatabaseInsertException { boolean cdt1 = Checker.checkValidUserAccountUserId(userId); boolean
   * cdt2 = Checker.checkValidUserAccountAccountId(accountId, userId); // if it is a valid user id
   * and account id, establish a connection if (cdt1 && cdt2) { if
   * (DatabaseSelectHelper.getAccountIds(userId).contains(accountId)) { Connection connection =
   * DatabaseDriverHelper.connectOrCreateDataBase(); int result =
   * DatabaseInserter.insertUserAccount(userId, accountId, connection); connection.close(); return
   * result; } } return -1; }
   */

}
