package javaConnector2;

import java.sql.Connection;

public class DatabaseDriverHelper extends DatabaseDriver {
  public static Connection connectOrCreateDataBase() {
    return DatabaseDriver.connectOrCreateDataBase();
  }
}
