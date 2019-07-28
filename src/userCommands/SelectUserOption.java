package userCommands;

import java.sql.SQLException;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseSelectHelper;

public class SelectUserOption extends UserOption {
  private User user;

  protected SelectUserOption(Scanner sc, User user) {
    super(sc);
    this.user = user;
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {

    if (sc != null) {
      String input = "";
      int choice = -1;
      UserOption option = null;
      do {
        userMenu(); // Print Menu
        input = sc.nextLine();
        try {
          choice = Integer.parseInt(input);
          switch (choice) { // Activate the desired functionality
            case 0:
              option = new Blank(sc, "Back option");
              break;
            case 1:
              option = new CreateNewUser(sc);
              break;
            case 2:
              System.out.println("SIN: ");
              String sin = sc.nextLine();
              this.user = DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin));
              option = new SelectUserOption(sc, user);
              break;
            case 3:
              break;
            case 4:
              break;
            case 5:
              option = new CreateListing(sc, user.sin);
              break;
            case 6:
              break;
            case 7:
              option = new DeleteListing(sc, this.user.sin);
              break;
            case 8:
              option = new DeleteUser(sc, this.user.sin);
              break;
            default:
              break;
          }
          option.execute();
        } catch (NumberFormatException e) {
          input = "-1";
        } catch (Exception e) {
          e.printStackTrace();
        }
      } while (input.compareTo("0") != 0);
    } else {
      System.out.println("");
      System.out.println("Error Happened!");
    }

  }

  private static void userMenu() {
    System.out.println("=========MENU========");
    System.out.println("0. Back to main menu");
    System.out.println("1. create a new user");
    System.out.println("2. switch as user");
    System.out.println("3. Rent a Listing");
    System.out.println("4. Cancel renting of a listing");
    System.out.println("5. Create a Listing");
    System.out.println("6. update a Listing");
    System.out.println("7. delete a Listing");
    System.out.println("8. delete this user");
  }

}
