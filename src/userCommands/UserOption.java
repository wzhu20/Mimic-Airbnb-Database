package userCommands;

import java.util.Scanner;

public abstract class UserOption {
  Scanner sc;

  protected UserOption(Scanner sc) {
    this.sc = sc;
  }

  abstract void execute();
}
