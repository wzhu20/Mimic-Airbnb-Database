package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.Checker;
import javaConnector2.DatabaseSelectHelper;
import javaConnector2.QueriesHelper;

public class RunQueries extends UserOption {

  protected RunQueries(Scanner sc) {
    super(sc);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    if (sc != null) {
      UserOption option = null;
      String input = "";
      String postal = "";
      String lat = "";
      String longit = "";
      String distance = "";
      String date = "";
      String rental = "";
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
              output = QueriesHelper.queryByAddress(postal);
              printOutput(output);
              break;

            case 2:
              System.out.println("postal Code: ");
              postal = sc.nextLine();
              output = QueriesHelper.queryByAdjacentPostalCodes(postal);
              printOutput(output);
              break;

            case 3:
              do {
                System.out.println("latitude: ");
                lat = sc.nextLine();
                System.out.println("longitude: ");
                longit = sc.nextLine();
              } while (!(Checker.checkValidLatLong(Float.valueOf(lat))
                  && Checker.checkValidLatLong(Float.valueOf(longit))));
              output = QueriesHelper.queryByLocations(Float.valueOf(lat), Float.valueOf(longit));
              printOutput(output);
              break;

            case 4:
              do {
                System.out.println("latitude: ");
                lat = sc.nextLine();
                System.out.println("longitude: ");
                longit = sc.nextLine();
                System.out.println("distance: ");
                distance = sc.nextLine();
              } while (!(Checker.checkValidLatLong(Float.valueOf(lat))
                  && Checker.checkValidLatLong(Float.valueOf(longit))
                  && Checker.checkValidLatLong(Float.valueOf(distance))));
              output = QueriesHelper.queryByLocations(Float.valueOf(lat), Float.valueOf(longit),
                  Float.valueOf(distance));
              printOutput(output);
              break;

            case 5:
              System.out.println("Amentities of home (type - to finish adding):");
              ResultSet data = DatabaseSelectHelper.getAllAmen();
              while (data.next()) {
                System.out.println(data.getInt("idAmenities") + " : " + data.getString("Item"));
              }
              data.close();
              String amen = sc.nextLine();
              List<String> amenList = new ArrayList<String>();
              List<String> totalAmenList = new ArrayList<String>();

              while (!amen.equals("-")) {
                data = DatabaseSelectHelper.getAllAmen();
                while (data.next()) {
                  System.out.println(data.getInt("idAmenities") + " : " + data.getString("Item"));
                  totalAmenList.add(data.getString("Item"));
                }
                data.close();
                if (totalAmenList.contains(amen)) {
                  amenList.add(amen);
                } else if (amen.equals("-")) {
                  System.out.println("finished");
                } else {
                  System.out.println("invalid amenity, try again");
                }

                amen = sc.nextLine();
              }
              output = QueriesHelper.queryByAmenities(amenList);
              printOutput(output);
              break;
            case 6:
              do {
                System.out.println("date (YYYY-MM-DD): ");
                date = sc.nextLine();
              } while (!Checker.checkValidDate(date));

              output = QueriesHelper.queryByListingDates(date);
              printOutput(output);
              break;
            case 7:
              do {
                System.out.println("Rental Price: ");
                rental = sc.nextLine();
              } while (!Checker.checkValidRentPrice(Double.valueOf(rental)));
              output = QueriesHelper.queryByRentalPrices(Double.valueOf(rental));
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
    System.out.println("1. search Listings by postal code");
    System.out.println("2. search Listings around postal code");
    System.out.println("3. search by locations (latitude and longitude)");
    System.out.println("4. search arounrd a location (distance from latitude and longitude)");
    System.out.println("5. search by amentities");
    System.out.println("6. search by listingDates");
    System.out.println("7. search by rental prices upper limit");
  }


}
