package javaConnector2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelectHelper extends DatabaseSelector {

  public static ResultSet getAllOccupations() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = connection.createStatement();
    ResultSet results = statement.executeQuery("SELECT * FROM users_has_Occupation;");
    return results;
  }

}
