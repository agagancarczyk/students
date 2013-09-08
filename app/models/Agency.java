package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Agency extends User
{

  public String agencyName;
  public String agencyAddress;

  @OneToMany(mappedBy = "agency")
  public List<Accommodation> accommodations = new ArrayList<Accommodation>();

  public Agency(String firstName, String lastName, String email, String password, String mobileCountryCode, int mobile,
      String securityQuestion, String answer, String agencyName, String agencyAddress)
  {

    super(firstName, lastName, email, password, mobileCountryCode, mobile, securityQuestion, answer);

    this.agencyName = agencyName;
    this.agencyAddress = agencyAddress;
  }

  public List<Accommodation> getAccommodations()
  {
    return accommodations;
  }
}
