package userCommands;

import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseUpdateHelper;

public class DeleteUser extends UserOption {
  private int sin;

  protected DeleteUser(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    DatabaseUpdateHelper.deleteUser(this.sin);
    System.out.println("deletion finished");
  }

}
