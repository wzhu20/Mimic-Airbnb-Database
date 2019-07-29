package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;

public class CommentRateHose extends UserOption {
  private int sin;

  protected CommentRateHose(Scanner sc, int sin) {
    super(sc);
    this.sin = sin;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Host to comment and rate : ");
    ResultSet data = DatabaseSelectHelper.getHostasRenter(this.sin);
    int userSin = 0;
    int hostId = 0;
    if (!data.next()) {
      System.out.println("operation failed, no hosts");
    } else {
      while (data.next()) {
        userSin = data.getInt("Users_SIN");
        hostId = DatabaseSelectHelper.getHostProfile(userSin);
        System.out.println("Host profile : " + hostId);
      }
      data.close();
      String host = sc.nextLine();
      System.out.println("Comment : ");
      String comment = sc.nextLine();
      System.out.println("Rating : ");
      String rating = sc.nextLine();

      int hostSin = DatabaseSelectHelper.getSinFromHostId(Integer.parseInt(host));
      DatabaseInsertHelper.insertHostComment(Integer.parseInt(host), hostSin, comment);
      DatabaseInsertHelper.insertHostRating(Integer.parseInt(host), hostSin,
          Integer.parseInt(rating));

    }
  }

}
