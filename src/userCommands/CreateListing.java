package userCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import exceptions.DatabaseInsertException;
import javaConnector2.DatabaseInsertHelper;
import javaConnector2.DatabaseSelectHelper;

public class CreateListing extends UserOption {

  protected CreateListing(Scanner sc) {
    super(sc);
  }

  @Override
  void execute() throws NumberFormatException, SQLException, DatabaseInsertException {
    System.out.println("Address (postal code)");
    String postal = sc.nextLine();
    System.out.println("Latitutude:");
    String lat = sc.nextLine();
    System.out.println("Longitude :");
    String longit = sc.nextLine();
    System.out.println("Begin date (YYYY-MM-DD):");
    String begin = sc.nextLine();
    System.out.println("End date (YYYY-MM-DD):");
    String end = sc.nextLine();
    System.out.println("Home Type (press 0 to see all, -1 to add a new one):");
    String hometype = sc.nextLine();
    try {
      while (Integer.parseInt(hometype) == 0) {
        ResultSet data = DatabaseSelectHelper.getAllHometypes();
        while (data.next()) {
          System.out.println(data.getInt("idHomeType") + " : " + data.getString("type"));
        }
        data.close();
        System.out.println("Home Type (press 0 to see all home types)");
        hometype = sc.nextLine();
      }
      if (Integer.parseInt(hometype) == -1) {
        System.out.println("Type Name: ");
        String typeName = sc.nextLine();
        hometype = DatabaseInsertHelper.insertHometype(typeName) + "";
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

    System.out.println("Amentities of home (-1 to add a brand new one, -2 to finish adding):");
    String amen = sc.nextLine();
    ArrayList<Integer> amenList = new ArrayList<Integer>();
    try {
      while (Integer.parseInt(amen) != -2) {
        ResultSet data = DatabaseSelectHelper.getAllAmen();
        while (data.next()) {
          System.out.println(data.getInt("idHomeType") + " : " + data.getString("type"));
        }
        data.close();
        if (Integer.parseInt(amen) == -1) {
          System.out.println("Amentity Name: ");
          String amenName = sc.nextLine();
          amen = DatabaseInsertHelper.insertAmen(amenName) + "";
        }
        amenList.add(Integer.parseInt(amen));
        amen = sc.nextLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

}
