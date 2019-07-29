package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.Checker;
import javaConnector2.DatabaseSelectHelper;
import javaConnector2.DatabaseUpdateHelper;

public class UpdateListing extends UserOption {
  private int sin;

  protected UpdateListing(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Listing to update : ");
    ResultSet data = DatabaseSelectHelper.getAllAvaliableHostList(this.sin);
    if (!data.next()) {
      System.out.println("operation failed, no avaliable listing");
    } else {
      while (data.next()) {
        System.out.println("Listing :" + data.getInt("Listing_ListingID"));
      }
      data.close();
      String listing = sc.nextLine();

      System.out.println("Beginning date for listing (YYYY-MM-DD) :");
      String begin = sc.nextLine();
      System.out.println("End date for listing (YYYY-MM-DD) :");
      String end = sc.nextLine();

      System.out.println("Price to update to :");
      String rental = sc.nextLine();
      if (Checker.checkHostHasListing(sin, Integer.parseInt(listing))) {
        DatabaseUpdateHelper.updateListingPrice(Integer.parseInt(listing), begin, end,
            Double.valueOf(rental));
      } else {
        System.out.println("operation failed");
      }
    }
  }
}
