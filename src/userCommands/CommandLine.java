package userCommands;

import java.util.Scanner;

public class CommandLine {

  // 'sc' is needed in order to scan the inputs provided by the user
  private Scanner sc = null;

  // Public functions - CommandLine State Functions

  /*
   * Function used for initializing an istance of current class
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
        menu(); // Print Menu
        input = sc.nextLine();
        try {
          choice = Integer.parseInt(input);
          switch (choice) { // Activate the desired functionality
            case 1:
              option = new CreateNewUser(sc);
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
      System.out.println("");
      return false;
    }
  }

  // Private functions

  // Print menu options
  private static void menu() {
    System.out.println("=========MENU=========");
    System.out.println("0. Exit.");
    System.out.println("1. create a new user");
    /*
     * System.out.println("2. Select a record."); System.out.println("3. Print schema.");
     * System.out.println("4. Print table schema."); System.out.println("5. Create a new user");
     * System.out.print("Choose one of the previous options [0-4]: ");
     */
  }

  /*
   * // Function that handles the feature: "3. Print schema." private void printSchema() {
   * ArrayList<String> schema = sqlMngr.getSchema();
   * 
   * System.out.println(""); System.out.println("------------");
   * System.out.println("Total number of tables: " + schema.size()); for (int i = 0; i <
   * schema.size(); i++) { System.out.println("Table: " + schema.get(i)); }
   * System.out.println("------------"); System.out.println(""); }
   * 
   * // Function that handles the feature: "4. Print table schema." private void printColSchema() {
   * System.out.print("Table Name: "); String tableName = sc.nextLine(); ArrayList<String> result =
   * sqlMngr.colSchema(tableName); System.out.println(""); System.out.println("------------");
   * System.out.println("Total number of fields: " + result.size() / 2); for (int i = 0; i <
   * result.size(); i += 2) { System.out.println("-"); System.out.println("Field Name: " +
   * result.get(i)); System.out.println("Field Type: " + result.get(i + 1)); }
   * System.out.println("------------"); System.out.println(""); }
   * 
   * // Function that handles the feature: "2. Select a record." private void selectOperator() {
   * String query = ""; System.out.print("Issue the Select Query: "); query = sc.nextLine();
   * query.trim(); if (query.substring(0, 6).compareToIgnoreCase("select") == 0)
   * sqlMngr.selectOp(query); else System.err.println("No select statement provided!"); }
   * 
   * // Function that handles the feature: "1. Insert a record." private void insertOperator() { int
   * rowsAff = 0; int counter = 0; String query = ""; System.out.print("Table: "); String table =
   * sc.nextLine(); System.out.print("Comma Separated Columns: "); String cols = sc.nextLine();
   * System.out.print("Comma Separated Values: "); String[] vals = sc.nextLine().split(","); //
   * transform the user input into a valid SQL insert statement query = "INSERT INTO " + table +
   * " (" + cols + ") VALUES("; for (counter = 0; counter < vals.length - 1; counter++) { query =
   * query.concat("'" + vals[counter] + "',"); } query = query.concat("'" + vals[counter] + "');");
   * System.out.println(query); rowsAff = sqlMngr.insertOp(query); System.out.println("");
   * System.out.println("Rows affected: " + rowsAff); System.out.println(""); }
   */
}
