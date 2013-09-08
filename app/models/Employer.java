package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Employer extends User
{

  public String employerName;
  public String employerAddress;

  @OneToMany(mappedBy = "employer")
  public List<Internship> internships = new ArrayList<Internship>();

  public Employer(String firstName, String lastName, String email, String password, String mobileCountryCode,
      int mobile, String securityQuestion, String answer, String employerName, String employerAddress)
  {

    super(firstName, lastName, email, password, mobileCountryCode, mobile, securityQuestion, answer);

    this.employerName = employerName;
    this.employerAddress = employerAddress;
  }

  public List<Internship> getInternships()
  {
    return internships;
  }
}
