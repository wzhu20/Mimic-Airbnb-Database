package userCommands;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.QueriesHelper;

public class RunQueries extends UserOption {

  protected RunQueries(Scanner sc) {
    super(sc);
  }


  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    if (sc != null) {
      UserOption option = null;
      String input = "";
      List<List<String>> output = null;
      int choice = -1;
      do {
        menu(); // Print Menu
        input = sc.nextLine();
        try {
          choice = Integer.parseInt(input);
          switch (choice) { // Activate the desired functionality
            case 0:
              option = new Blank(sc, "Back option");
              option.execute();
              break;
            case 1:
              System.out.println("postal Code: ");
              String postal = sc.nextLine();
              output = QueriesHelper.queryByAddress(postal);
              for (List<String> i : output) {
                for (String j : i) {
                  System.out.print(j + " ");
                }
                System.out.println("");
              }
              break;
            /*
             * case -11: System.out.println("SIN: "); String sin = sc.nextLine(); User user =
             * DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin)); break; case 2:
             * System.out.println("SIN: "); String sin = sc.nextLine(); User user =
             * DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin)); break; case 3:
             * System.out.println("SIN: "); String sin = sc.nextLine(); User user =
             * DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin)); break; case 4:
             * System.out.println("SIN: "); String sin = sc.nextLine(); User user =
             * DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin)); break;
             */
            default:
              option = new NullOption(sc);
              break;
          }

        } catch (NumberFormatException e) {
          input = "-1";
        } catch (Exception e) {
          e.printStackTrace();
        }
      } while (input.compareTo("0") != 0);
    } else {
      System.out.println("");
      System.out.println("Connection could not been established! Bye!");
    }
  }



  // Print menu options
  private static void menu() {
    System.out.println("=========MENU========");
    System.out.println("0. Back to main menu");
    System.out.println("1. search Listings by postal code");
    System.out.println("2. search around postal code");
    System.out.println("3. search by locations (latitude and longitude)");
    System.out.println("4. search arounrd a location (distance from latitude and longitude)");
    System.out.println("5. search by amentities");
    System.out.println("6. search by listingDates");
    System.out.println("7. search by rental prices");
  }

}
