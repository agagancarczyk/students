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
public class Course extends Model
{

  public String schoolName;
  public String courseTitle;
  public int level;
  public int points;
  public Date date;

  @ManyToOne
  public College college;

  @ManyToMany
  List<Student> courseApplicants = new ArrayList<Student>();

  public Course(College college, String schoolName, String courseTitle, int level, int points)
  {
    this.college = college;
    this.schoolName = schoolName;
    this.courseTitle = courseTitle;
    this.level = level;
    this.points = points;
    this.date = new Date();
  }

  /*
   * Method allows Student to be added to the list of course applicants.
   * 
   * @param student 
   *          Student
   */
  public void addApplicant(Student student)
  {
    courseApplicants.add(student);
    Logger.info("addApplicant: " + courseApplicants);
    save();
    student.appliedForCourses.add(this);
    Logger.info("appliedForCourses: student " + student);
    student.save();
  }

  /*
   * Method allows Student to be removed from the list of course applicants.
   * 
   * @param student 
   *          Student
   */
  public void rejectApplicant(Student student)
  {
    courseApplicants.remove(student);
    save();
    student.appliedForCourses.remove(this);
    student.save();
  }
}
