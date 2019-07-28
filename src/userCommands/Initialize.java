package userCommands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.InitializeDatabase;

public class Initialize extends UserOption {

  protected Initialize(Scanner sc) {
    super(sc);
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    Connection c = InitializeDatabase.initialize();
    c.close();
  }

}
