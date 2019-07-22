package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;
import exceptions.DatabaseInsertException;

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

  public static int insertAddress(String postal, String city, String country) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertAddress(postal, city, country, connection);
    return id;
  }

}
