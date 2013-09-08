package models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.Logger;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends User
{

  public Date date;
  public String gender;
  public String nationality;

  @ManyToMany
  List<Course> appliedForCourses = new ArrayList<Course>();

  @ManyToMany
  List<Internship> appliedForInternships = new ArrayList<Internship>();

  @ManyToMany
  List<Accommodation> bookedViewings = new ArrayList<Accommodation>();

  public Student(String firstName, String lastName, String email, String password, Date date, String gender,
      String nationality, String mobileCountryCode, int mobile, String securityQuestion, String answer)
  {

    super(firstName, lastName, email, password, mobileCountryCode, mobile, securityQuestion, answer);
    this.date = date;
    this.gender = gender;
    this.nationality = nationality;

  }
}
