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

  public static int insertHometype(String name) throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertHomeType(name, connection);
    return id;
  }

  public static int insertAmen(String name) throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertAmen(name, connection);
    return id;
  }

  public static int insertAddress(String postal, String city, String country) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertAddress(postal, city, country, connection);
    return id;
  }

  public static int insertUserAddress(String postal, String sin) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertUserAddress(postal, sin, connection);
    return id;
  }

  public static int insertHost() throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertNewHost(connection);
    return id;
  }

  public static int insertRenter() throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertNewRenter(connection);
    return id;
  }

  public static int insertOccupation(String occupations) throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int id = DatabaseInserter.insertOccupations(occupations, connection);
    return id;
  }

}
