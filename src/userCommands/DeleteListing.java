package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.Checker;
import javaConnector2.DatabaseSelectHelper;
import javaConnector2.DatabaseUpdateHelper;

public class DeleteListing extends UserOption {
  private int sin;

  protected DeleteListing(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }


  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Listing to delete : ");
    ResultSet data = DatabaseSelectHelper.getAllHostList(this.sin);
    while (data.next()) {
      System.out.println("Listing :" + data.getInt("Listing_ListingID"));
    }
    data.close();
    String listing = sc.nextLine();

    if (Checker.checkHostHasListing(sin, Integer.parseInt(listing))) {
      DatabaseUpdateHelper.deleteListing(Integer.parseInt(listing), this.sin);
    } else {
      System.out.println("operation failed");
    }

  }

}
