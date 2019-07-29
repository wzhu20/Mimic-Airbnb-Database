package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;

public class RentListing extends UserOption {
  private int sin;

  protected RentListing(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Listing to rent : ");
    ResultSet data = DatabaseSelectHelper.getAllAvaliableListing();
    while (data.next()) {
      System.out.println("Listing :" + data.getInt("Listing_ListingID"));
    }
    data.close();
    String listing = sc.nextLine();

    data = DatabaseSelectHelper.getAllAvaliableListing();
    while (data.next()) {
      System.out.println("Listing :" + data.getInt("Listing_ListingID") + " Begin Date:"
          + data.getDate("Calendar_BeginDate") + " End Date :" + data.getDate("Calendar_EndDate"));
    }
    data.close();

    System.out.println("Credit card : ");
    String credit = sc.nextLine();

    System.out.println("Beginning date for listing (YYYY-MM-DD) :");
    String begin = sc.nextLine();
    System.out.println("End date for listing (YYYY-MM-DD) :");
    String end = sc.nextLine();

    DatabaseInsertHelper.insertRentListing(sin, Integer.parseInt(listing), begin, end,
        Integer.parseInt(credit));
  }

}
