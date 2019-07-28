package userCommands;

import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;

public class Blank extends UserOption {
  private String msg;

  protected Blank(Scanner sc, String msg) {
    super(sc);
    this.msg = msg;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println(msg);
  }

}
