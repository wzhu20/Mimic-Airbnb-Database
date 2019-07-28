package userCommands;

public class User {
  public int sin;
  public String dob;
  public String name;
  public int hostId;
  public int rentId;
  public int occ;

  public User(int sin, String dob, String name, int hostId, int rentId, int occ) {
    this.sin = sin;
    this.dob = dob;
    this.name = name;
    this.hostId = hostId;
    this.rentId = rentId;
    this.occ = occ;
  }

  public int getUserType() {

    // host only = 1
    // rent only = 2
    // host and rent type = 3
    // other = 0

    if (this.hostId != -1 && this.rentId != -1) {
      return 3;
    } else if (this.hostId != -1) {
      return 1;
    } else if (this.rentId != -1) {
      return 2;
    } else {
      return 0;
    }

  }
}
