package userCommands;

import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;

public class NullOption extends UserOption {

  protected NullOption(Scanner sc) {
    super(sc);
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("null option");

  }

}
