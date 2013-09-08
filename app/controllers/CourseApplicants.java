package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class CourseApplicants extends Controller
{

  public static void index()
  {
    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Logger.info("Unable to locate current user. Attempting to log in...");
      Accounts.login();
    }
    else
    {
      render(user);
    }
  }

  /*
   * Method allows College to reject Student who applied for a course.
   * 
   * @param courseId 
   *          Course id
   * @param applicantId 
   *          Student's id
   */
  public static void rejectFromCourse(Long courseId, Long applicantId)
  {
    Course course = Course.findById(courseId);
    Student student = Student.findById(applicantId);
    course.rejectApplicant(student);
    CourseApplicants.index();
  }
}