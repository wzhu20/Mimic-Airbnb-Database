package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;

public class CommentRateRenter extends UserOption {
  private int sin;

  protected CommentRateRenter(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Renter to comment and rate : ");
    ResultSet data = DatabaseSelectHelper.getRenterasHost(this.sin);
    int userSin = 0;
    int RentId = 0;
    if (!data.next()) {
      System.out.println("operation failed, no renters");
    } else {
      while (data.next()) {
        userSin = data.getInt("Users_SIN");
        RentId = DatabaseSelectHelper.getHostProfile(userSin);
        System.out.println("Renter profile : " + RentId);
      }
      data.close();
      String rent = sc.nextLine();
      System.out.println("Comment : ");
      String comment = sc.nextLine();
      System.out.println("Rating : ");
      String rating = sc.nextLine();

      int rentSin = DatabaseSelectHelper.getSinFromHostId(Integer.parseInt(rent));
      DatabaseInsertHelper.insertRentComment(Integer.parseInt(rent), rentSin, comment);
      DatabaseInsertHelper.insertRenterRating(Integer.parseInt(rent), rentSin,
          Integer.parseInt(rating));

    }

  }

}
