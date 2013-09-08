package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Internship extends Model
{

  public String jobTitle;
  public String careerSector;
  public Date date;

  @ManyToOne
  public Employer employer;

  @ManyToMany
  public List<Student> internshipApplicants = new ArrayList<Student>();

  public Internship(Employer employer, String jobTitle, String careerSector)
  {
    this.employer = employer;
    this.jobTitle = jobTitle;
    this.careerSector = careerSector;
    this.date = new Date();
  }

  /*
   * Method allows Student to be added to the list of internship applicants.
   * 
   * @param student 
   *          Student
   */
  public void addIntern(Student student)
  {
    internshipApplicants.add(student);
    save();
    student.appliedForInternships.add(this);
    student.save();
  }

  /*
   * Method allows Student to be removed from the list of internship applicants.
   * 
   * @param student 
   *          Student
   */
  public void rejectIntern(Student student)
  {
    internshipApplicants.remove(student);
    save();
    student.appliedForInternships.remove(this);
    student.save();
  }

  @Override
  public String toString()
  {
    return "Internship [jobTitle=" + jobTitle + ", careerSector=" + careerSector + ", date=" + date + ", employer="
        + employer + ", internshipApplicants=" + internshipApplicants + "]";
  }
}
