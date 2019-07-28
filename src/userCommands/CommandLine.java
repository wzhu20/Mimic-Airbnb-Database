package userCommands;

import java.util.Scanner;
import javaConnector2.DatabaseSelectHelper;

public class CommandLine {

  // 'sc' is needed in order to scan the inputs provided by the user
  private Scanner sc = null;

  // Public functions - CommandLine State Functions

  /*
   * Function used for initializing an instance of current class
   */
  public boolean startSession() {
    boolean success = true;
    if (sc == null) {
      sc = new Scanner(System.in);
    }

    return success;
  }

  /*
   * Function that acts as destructor of an instance of this class. Performs some housekeeping
   * setting instance's private field to null
   */
  public void endSession() {
    if (sc != null) {
      sc.close();
    }
    sc = null;
  }

  /*
   * Function that executes an infinite loop and activates the respective functionality according to
   * user's choice. At each time it also outputs the menu of core functionalities supported from our
   * application.
   */
  public boolean execute() {
    if (sc != null) {
      System.out.println("****AIRBNB SYSTEM****");
      UserOption option = null;
      String input = "";
      int choice = -1;
      do {
        mainMenu(); // Print Menu
        input = sc.nextLine();
        try {
          choice = Integer.parseInt(input);
          switch (choice) { // Activate the desired functionality
            case 1:
              option = new CreateNewUser(sc);
              break;
            case -11:
              option = new Initialize(sc);
              break;
            case -12:
              option = new Populate(sc);
              break;
            case 2:
              System.out.println("SIN: ");
              String sin = sc.nextLine();
              User user = DatabaseSelectHelper.getUserDetails(Integer.parseInt(sin));
              option = new SelectUserOption(sc, user);
              break;
            case 3:
              option = new RunQueries(sc);
              break;
            case 4:
              option = new RunReports(sc);
              break;
            default:
              option = new NullOption(sc);
              break;
          }
          option.execute();
        } catch (NumberFormatException e) {
          input = "-1";
        } catch (Exception e) {
          e.printStackTrace();
        }
      } while (input.compareTo("0") != 0);
      return true;
    } else {
      System.out.println("");
      System.out.println("Connection could not been established! Bye!");
      return false;
    }
  }

  // Print menu options
  private static void mainMenu() {
    System.out.println("=========MENU========");
    System.out.println("-11. (initialize database)");
    System.out.println("-12. (populate database)");
    System.out.println("0. Exit.");
    System.out.println("1. create a new user");
    System.out.println("2. select as user");
    System.out.println("3. run queries");
    System.out.println("4. run reports");
  }
}
