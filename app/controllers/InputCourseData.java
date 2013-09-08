package controllers;

import play.*;
import play.mvc.*;
import java.util.*;

import models.*;

public class InputCourseData extends Controller
{

  public static void index()
  {
    User user = Accounts.getCurrentUser();
    if (user != null)
    {
      render(user);
    }
    else
    {
      Accounts.login();
    }
  }

  /*
   * Method allows College to add new course to the list of courses.
   * 
   * @param schoolName 
   *          School Name
   * @param courseTitle 
   *          Course Title 
   * @param level 
   *          Course level
   * @param points 
   *          Entry points
   */
  public static void addCourse(String schoolName, String courseTitle, int level, int points)
  {

    Logger.info("Adding Courses" + " " + schoolName + " " + courseTitle + " " + level + " " + points + " ");

    College college = (College) Accounts.getCurrentUser(); 
    Logger.info("addCourse" + college);

    validation.required("School Name", schoolName);
    validation.required("Course Title", courseTitle);
    validation.required("Level", level);
    validation.required("Points", points);

    if (validation.hasErrors())
    {
      params.flash();
      validation.keep();
      index();
    }

    Course newCourse = new Course(college, schoolName, courseTitle, level, points);
    newCourse.save();
    Logger.info("Course newCourse" + " " + newCourse.schoolName + " " + newCourse.courseTitle + " " + newCourse.level
        + " " + newCourse.points + " ");

    college.courses.add(newCourse);
    college.save();
    index();
  }

  /*
   * Method allows College to remove course from the list of courses.
   * 
   * @param id 
   *          Course id
   */
  public static void removeCourse(Long id)
  {

    College college = (College) Accounts.getCurrentUser();
    Logger.info("Course id:" + id);
    Course course = Course.findById(id);
    college.courses.remove(course);
    college.save();
    course.delete();
    Logger.info("Removing course" + course);
    index();
  }

}
