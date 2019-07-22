package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;

public class CreateNewUser extends UserOption {

  public CreateNewUser(Scanner sc) {
    super(sc);
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("SIN: ");
    String sin = sc.nextLine();
    System.out.println("Date of Birth in format of MM DD YY :");
    String dob = sc.nextLine();
    System.out.println("Full name: ");
    String name = sc.nextLine();
    System.out.println("Occupation number (0 to see list of occupations)");
    String occ = sc.nextLine();
    try {
      while (Integer.parseInt(occ) == 0) {
        ResultSet data = DatabaseSelectHelper.getAllOccupations();
        while (data.next()) {
          System.out.println(
              data.getInt("Occupation_idOccupation") + " : " + data.getString("Occupation_name"));
        }
        data.close();
        System.out.println("Occupation number (0 to see list of occupations)");
        occ = sc.nextLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseInsertHelper.insertUser(sin, dob, name, Integer.parseInt(occ));
    System.out.println("user created");
  }

}
