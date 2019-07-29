package javaConnector2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import exceptions.DatabaseInsertException;


public class DatabaseInserter {
  /*
   * INSERT STATEMENTS
   */
  protected static int insertHomeType(String homeType, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO HomeType(TYPE) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, homeType);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          int outputkey = uniqueKey.getInt(1);
          return outputkey;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  protected static int insertAmen(String amenities, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO Amenities(Item) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, amenities);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          int outputkey = uniqueKey.getInt(1);
          return outputkey;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  protected static int insertOccupations(String occupations, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO Users_has_Occupation(Occupation_name) VALUES(?)";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, occupations);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          int outputkey = uniqueKey.getInt(1);
          return outputkey;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  protected static int insertNewHost(Connection connection) throws DatabaseInsertException {
    String sql = "INSERT INTO Host_Profile values()";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.executeUpdate();
      ResultSet id = preparedStatement.getGeneratedKeys();
      if (id.next()) {
        int outputId = id.getInt(1);
        return outputId;
      } else {
        return -1;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }

  protected static int insertNewRenter(Connection connection) throws DatabaseInsertException {
    String sql = "INSERT INTO Renter_Profile values()";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.executeUpdate();
      ResultSet id = preparedStatement.getGeneratedKeys();
      if (id.first()) {
        int outputId = id.getInt(1);
        return outputId;

      } else {
        return -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
  }


  protected static int insertUserAddress(String postal, String sin, Connection connection) {
    String sql = "INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, sin);
      preparedStatement.setString(2, postal);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;

  }

  protected static int insertAddress(String postal, String city, String country,
      Connection connection) {
    String sql = "INSERT INTO address (Postal_code, city, country) VALUES(?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postal);
      preparedStatement.setString(2, city);
      preparedStatement.setString(3, country);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertListingAddress(String postal, int listingId, Connection connection) {
    String sql =
        "INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postal);
      preparedStatement.setInt(2, listingId);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertUser(String sin, String dob, String name, int hostId, int rentId,
      Integer occupation, Connection connection) {
    String sql = "INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile, "
        + "Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(?,?,?,?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, sin);
      preparedStatement.setString(2, dob);
      preparedStatement.setString(3, name);
      if (hostId == -1)
        preparedStatement.setNull(4, java.sql.Types.INTEGER);
      else
        preparedStatement.setInt(4, hostId);
      if (rentId == -1)
        preparedStatement.setNull(5, java.sql.Types.INTEGER);
      else
        preparedStatement.setInt(5, rentId);
      preparedStatement.setInt(6, occupation);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertListing(float lat, float longit, int hometype, Connection connection) {
    String sql = "INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(?,?,?);";
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setFloat(1, lat);
      preparedStatement.setFloat(2, longit);
      preparedStatement.setInt(3, hometype);
      preparedStatement.executeUpdate();
      ResultSet id = preparedStatement.getGeneratedKeys();
      if (id.first()) {
        int outputId = id.getInt(1);
        return outputId;

      } else {
        return -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }


  protected static int insertCalendar(String begin, String end, Connection connection) {
    String sql = "INSERT INTO Calendar (BeginDate, EndDate) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDate(1, java.sql.Date.valueOf(begin));
      preparedStatement.setDate(2, java.sql.Date.valueOf(end));
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertListingCalendar(int listingId, String begin, String end, double rental,
      Connection connection) {
    String sql = "INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate, "
        + "Calendar_EndDate, RentalPrice) VALUES(?,?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, listingId);
      preparedStatement.setDate(2, java.sql.Date.valueOf(begin));
      preparedStatement.setDate(3, java.sql.Date.valueOf(end));
      preparedStatement.setDouble(4, rental);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertListingAmen(int listingId, int amen, Connection connection) {
    String sql =
        "INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, listingId);
      preparedStatement.setInt(2, amen);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertHostListing(int sin, int listingId, Connection connection) {
    String sql = "INSERT INTO Users_Host_Listing (Users_SIN, Listing_ListingID) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.setInt(2, listingId);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertRentListing(int sin, int listingId, String begin, String end,
      int credit, Connection connection) {
    String sql =
        "INSERT INTO Users_rent_Listing (Users_SIN, Listing_ListingID, Credit_Card, Calendar_BeginDate, Calendar_EndDate) VALUES(?,?,?,?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.setInt(2, listingId);
      preparedStatement.setInt(3, credit);
      preparedStatement.setDate(4, java.sql.Date.valueOf(begin));
      preparedStatement.setDate(5, java.sql.Date.valueOf(end));
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertCancelRent(int sin, int listingId, Connection connection) {
    String sql =
        "INSERT INTO users_Cancel_Rent (users_rent_Listing_users_SIN, users_rent_Listing_Listing_ListingID) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.setInt(2, listingId);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  protected static int insertCancelHost(int sin, int listingId, Connection connection) {
    String sql =
        "INSERT INTO users_Cancel_Host (users_Host_Listing_users_SIN, users_Host_Listing_Listing_ListingID) VALUES(?,?);";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, sin);
      preparedStatement.setInt(2, listingId);
      int id = preparedStatement.executeUpdate();
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

}
