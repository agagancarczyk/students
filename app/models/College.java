package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

@Entity
public class College extends User
{

  public String collegeName;
  public String collegeAddress;

  @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
  public List<Course> courses = new ArrayList<Course>();

  public College(String firstName, String lastName, String email, String password, String mobileCountryCode,
      int mobile, String securityQuestion, String answer, String collegeName, String collegeAddress)
  {

    super(firstName, lastName, email, password, mobileCountryCode, mobile, securityQuestion, answer);

    this.collegeName = collegeName;
    this.collegeAddress = collegeAddress;
  }

  public List<Course> getCourses()
  {
    return courses;
  }

}
