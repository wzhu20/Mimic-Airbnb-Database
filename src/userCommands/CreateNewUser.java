package userCommands;

import java.util.Scanner;

public class CreateNewUser extends UserOption {

  public CreateNewUser(Scanner sc) {
    super(sc);
  }

  @Override
  void execute() {
    String[] userInfo = new String[4];
    System.out.println("SIN: ");
    userInfo[0] = sc.nextLine();
    System.out.println("Date of Birth in format of MM DD YY :");
    userInfo[1] = sc.nextLine();
    System.out.println("Full name: ");
    userInfo[2] = sc.nextLine();
    System.out.println("Occupation number (0 to see list of occupations)");
    String occ = sc.nextLine();
    try {

    } catch (Exception e) {

    }
  }

}
