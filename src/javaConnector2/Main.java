package javaConnector2;

import userCommands.CommandLine;

public class Main {

  /*
   * Here starts the execution of our program. we will use the initializeDatabase object's static
   * initialize method to set up the database.
   */
  public static void main(String[] args) {
    CommandLine commandLine = new CommandLine();
    DatabaseDriver.connectOrCreateDataBase();

    if (commandLine.startSession() && commandLine.execute()) {
      commandLine.endSession();
    }
    System.out.println("session ended");
  }

}
