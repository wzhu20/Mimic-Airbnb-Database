package userCommands;

import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;

public abstract class UserOption {
  Scanner sc;

  protected UserOption(Scanner sc) {
    this.sc = sc;
  }

  abstract void execute() throws NumberFormatException, SQLException, DatabaseInsertException;
}
