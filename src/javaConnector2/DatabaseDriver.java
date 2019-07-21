package javaConnector2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class DatabaseDriver {

  /**
   * This will connect to existing database, or create it if it's not there.
   * 
   * @return the database connection.
   */
  protected static Connection connectOrCreateDataBase() {
    String user = "root";
    String pass = "1234";
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airbnb", user, pass);

    } catch (Exception e) {
      System.out.println("Something went wrong with your connection! see below details: ");
      e.printStackTrace();
    }

    return connection;
  }

  /**
   * This will initialize the database, or throw a ConnectionFailedException.
   * 
   * @param connection the database you'd like to write the tables to.
   * @return the connection you passed in, to allow you to continue.
   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
   */
  protected static Connection initialize(Connection connection) throws ConnectionFailedException {
    if (!initializeDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }


  /*
   * THIS IS WHERE THE SQL TABLE QUERIES SHOULD GO BECAUSE THIS IS THE FUNCTION THAT CREATES THE
   * DATABASE https://coderanch.com/t/306966/databases/Execute-sql-file-java for parser
   */


  private static boolean initializeDatabase(Connection connection) {
    Statement statement = null;
    String string = new String();
    StringBuffer stringBuff = new StringBuffer();

    try {
      FileReader fileReader = new FileReader(new File("airbnb.sql"));
      BufferedReader buffReader = new BufferedReader(fileReader);

      while ((string = buffReader.readLine()) != null) {
        stringBuff.append(string);
      }
      buffReader.close();

      // here is our splitter ! We use ";" as a delimiter for each request
      // then we are sure to have well formed statements
      String[] query = stringBuff.toString().split(";");

      statement = connection.createStatement();

      for (int i = 0; i < query.length; i++) {
        // we ensure that there is no spaces before or after the request string
        // in order to not execute empty statements
        if (!query[i].trim().equals("")) {
          statement.executeUpdate(query[i]);
        }
      }
      System.out.println("finished sql query");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }


}
