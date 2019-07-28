package userCommands;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.ReportsHelper;

public class RunReports extends UserOption {

  protected RunReports(Scanner sc) {
    super(sc);
  }


  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    if (sc != null) {
      UserOption option = null;
      String input = "";
      String postal = "";
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
              postal = sc.nextLine();
              System.out.println("Count: " + ReportsHelper.countBookingsByAddress(postal));
              break;
            case 2:
              output = ReportsHelper.countListingsByCountries();
              printOutput(output);
              break;

            case 3:
              output = ReportsHelper.countListingsByCountriesCity();
              printOutput(output);
              break;

            case 4:
              output = ReportsHelper.countListingsByCountriesCityPostal();
              printOutput(output);
              break;
            case 5:
              output = ReportsHelper.rankHostsByNumberofListingsPerCountry();
              printOutput(output);
              break;
            case 6:
              output = ReportsHelper.rankHostsByNumberofListingsPerCity();
              printOutput(output);
              break;
            case 7:
              output = ReportsHelper.hostWithMoreThanTenPercentByCountry();
              printOutput(output);
              break;
            case 8:
              output = ReportsHelper.hostWithMoreThanTenPercentByCity();
              printOutput(output);
              break;
            default:
              option = new NullOption(sc);
              option.execute();
              break;
          }

        } catch (NumberFormatException e) {
          System.out.println("bad input format");
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


  private void printOutput(List<List<String>> output) {
    for (List<String> i : output) {
      for (String j : i) {
        System.out.print(j + "\t");
      }
      System.out.println("");
    }
  }


  // Print menu options
  private static void menu() {
    System.out.println("=========MENU========");
    System.out.println("0. Back to main menu");
    System.out.println("1. count Bookings By Address");
    System.out.println("2. count Listings By Countries");
    System.out.println("3. count Listings By Countries City");
    System.out.println("4. count Listings By Countries City Postal");
    System.out.println("5. rank Hosts By Number of Listings Per Country");
    System.out.println("6. rank Hosts By Number of Listings Per City");
    System.out.println("7. host With More Than Ten Percent By Country");
    System.out.println("8. host With More Than Ten Percent By City");
  }

}
