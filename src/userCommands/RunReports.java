package userCommands;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.Checker;
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
            case 9:
              String date1 = "";
              String date2 = "";
              do {
                System.out.println("Date start(YYYY-MM-DD): ");
                date1 = sc.nextLine();
                System.out.println("Date end(YYYY-MM-DD): ");
                date2 = sc.nextLine();
              } while (!(Checker.checkValidDate(date1) && Checker.checkValidDate(date2)));
              output = ReportsHelper.rankRentersByNumberOfRentsInDateRange(date1, date2);
              printOutput(output);
              break;
            case 10:
              output = ReportsHelper.rankRentersByNumberOfRentsInDateRangePerCity();
              printOutput(output);
              break;
            case 11:
              output = ReportsHelper.RentersWithMostCancellaionsInAYear();
              printOutput(output);
              break;
            case 12:
              output = ReportsHelper.HostersWithMostCancellaionsInAYear();
              printOutput(output);
              break;
            case 13:
              output = ReportsHelper.rankRentersDateRangePerYearWithMoreThanTwoBookings();
              printOutput(output);
              break;
            case 14:
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
    System.out.println("9. rank Renters By Number Of Rents In Date Range");
    System.out.println("10. rank Renters By Number Of Rents In Date Range Per City");
    System.out.println("11. Renters With Most Cancellaions In A Year");
    System.out.println("12. Hosters With Most Cancellaions In A Year");
    System.out.println("13. rank Renters Date Range Per Year With More Than Two Bookings");
  }

}
