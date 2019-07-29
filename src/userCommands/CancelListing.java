package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.Checker;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;
import javaConnector2.DatabaseUpdateHelper;

public class CancelListing extends UserOption {
  private int sin;

  protected CancelListing(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    String begin = "";
    String end = "";
    System.out.println("Listing to delete : ");
    ResultSet data = DatabaseSelectHelper.getAllRentList(this.sin);
    while (data.next()) {
      System.out.println("Listing :" + data.getInt("Listing_ListingID") + " Begin Date:"
          + data.getDate("Calendar_BeginDate") + " End Date :" + data.getDate("Calendar_EndDate"));
    }
    data.close();
    String listingId = sc.nextLine();

    System.out.println("Begin date (YYYY-MM-DD):");
    begin = sc.nextLine();
    while (!Checker.checkValidDate(begin)) {
      System.out.println("invalid date, try again (YYYY-MM-DD)");
      begin = sc.nextLine();
    }

    System.out.println("End date (YYYY-MM-DD):");
    end = sc.nextLine();

    while (!Checker.checkValidDate(end)) {
      System.out.println("invalid date, try again (YYYY-MM-DD)");
      begin = sc.nextLine();
    }

    DatabaseUpdateHelper.updateCancelRentalPrice(sin, Integer.parseInt(listingId), begin, end);
    DatabaseInsertHelper.insertCancelRent(sin, Integer.parseInt(listingId));

  }

}
