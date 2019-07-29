package javaConnector2;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUpdateHelper extends DatabaseUpdater {

  public static boolean deleteUser(int sin) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.deleteUser(sin, connection);
    connection.close();
    return complete;
  }

  public static boolean deleteListing(int listingId, int sin) throws SQLException {
    // boolean cdt1 = Checker.checkValidRolesName(name);
    // boolean cdt2 = Checker.checkValidUsersId(id);
    // if (cdt1 && cdt2) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.deleteListing(listingId, connection);
    connection.close();
    return complete;
    // }
    // return false;
  }

  public static boolean updateListingPrice(int listingId, String begin, String end, double rental)
      throws SQLException {
    // boolean cdt1 = Checker.checkValidRolesName(name);
    // boolean cdt2 = Checker.checkValidUsersId(id);
    // if (cdt1 && cdt2) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete =
        DatabaseUpdater.updateListingPrice(listingId, begin, end, rental, connection);
    connection.close();
    return complete;
    // }
    // return false;
  }

  public static boolean updateCancelRentalPrice(int sin, int listingId, String begin, String end)
      throws SQLException {
    // boolean cdt1 = Checker.checkValidRolesName(name);
    // boolean cdt2 = Checker.checkValidUsersId(id);
    // if (cdt1 && cdt2) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateCancelRent(sin, listingId, begin, end, connection);
    connection.close();
    return complete;
    // }
    // return false;
  }
}
