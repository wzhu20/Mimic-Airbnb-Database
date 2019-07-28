package userCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseDriverHelper;

public class Populate extends UserOption {

  protected Populate(Scanner sc) {
    super(sc);
    // TODO Auto-generated constructor stub
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Statement statement = null;
    String string = new String();
    StringBuffer stringBuff = new StringBuffer();

    try {
      FileReader fileReader = new FileReader(new File("populate.sql"));
      BufferedReader buffReader = new BufferedReader(fileReader);

      while ((string = buffReader.readLine()) != null) {
        stringBuff.append(string);
      }
      buffReader.close();
      fileReader.close();
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
      System.out.println("finished populate sql query");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
