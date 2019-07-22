package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import exceptions.InvalidAgeException;
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
    System.out.println("Year of Birth:");
    String year = sc.nextLine();
    System.out.println("Month of Birth :");
    String month = sc.nextLine();
    System.out.println("Day of Birth :");
    String day = sc.nextLine();
    try {
      if (2019 - Integer.parseInt(year) < 18) {
        throw new InvalidAgeException();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    String dob = year.trim() + " " + month.trim() + " " + day.trim();
    System.out.println("Address; postal code");
    String postal = sc.nextLine();
    System.out.println("Address; city");
    String city = sc.nextLine();
    System.out.println("Address; country:");
    String country = sc.nextLine();

    System.out.println("Full name: ");
    String name = sc.nextLine();

    System.out
        .println("Occupation number (0 to see list of occupations, -1 to add a new occupation)");
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
    DatabaseInsertHelper.insertAddress(postal, city, country);
    System.out.println("user created");
  }

}
