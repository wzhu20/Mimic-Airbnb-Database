package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseUpdater {

  protected static boolean deleteUser(int sin, Connection connection) {
    String sql = "DELETE FROM USERS WHERE WHERE SIN = ?;";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.executeUpdate();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
