package javaConnector2;

import java.sql.Connection;

import com.bank.database.DatabaseDriver;

public class DatabaseDriverHelper extends DatabaseDriver {
  protected static Connection connectOrCreateDataBase() {
    return DatabaseDriver.connectOrCreateDataBase();
  }
}
