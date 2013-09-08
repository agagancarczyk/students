package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;
import play.Logger;
import play.mvc.Controller;

public class CourseSearch extends Controller
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
   * Method allows Student to search for a course by course title.
   * 
   * @param courseTitle 
   *          Course Title
   */
  public static void findByCourseTitle(String courseTitle)
  {
    String str = courseTitle.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Course> courses = Course.findAll();
    Logger.info(courses + " size " + courses.size());

    List<Course> searchResults = new ArrayList<Course>();
    for (Course c : courses)
    {
      Logger.info("course title: " + c.courseTitle);
      if (c.courseTitle.toLowerCase().contains(str))
      {
        searchResults.add(c);
        Logger.info("searchResults" + searchResults);
      }
      courses = searchResults;
      Logger.info("courses" + courses);
    }
    render(user, courses);
  }

  /*
   * Method allows Student to search for a course by college name.
   * 
   * @param college 
   *          College Name
   */
  public static void findByCollege(String college)
  {

    String str = college.toLowerCase();
    Logger.info("CourseSearch.findByCollege(college): collegeToLowerCase = " + str);

    User user = Accounts.getCurrentUser();

    List<College> allColleges = College.findAll();
    Logger.info(college + " size " + allColleges.size());

    List<College> searchResults = new ArrayList<College>();
    for (College c : allColleges)
    {
      Logger.info("c.collegeName: " + c.collegeName);
      if (c.collegeName.toLowerCase().contains(str))
      {
        searchResults.add(c);
        Logger.info("searchResults: " + searchResults);
        Logger.info("c: " + c);
      }
    }
    List<Course> courseResults = new ArrayList<Course>();
    for (College c : searchResults)
    {
      courseResults.addAll(c.courses);
    }
    render(user, courseResults);
  }

  /*
   * Method allows Student to search for a course by school/ department.
   * 
   * @param schoolName 
   *          Name of school/ department
   */
  public static void findBySchool(String schoolName)
  {

    String str = schoolName.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Course> allCourses = Course.findAll();
    Logger.info("allCourses: " + allCourses + " size: " + allCourses.size());

    List<Course> searchResults = new ArrayList<Course>();
    for (Course i : allCourses)
    {
      if (i.schoolName.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }
    render(user, searchResults);
  }

  /*
   * Method allows Student to search for a course by course level.
   * 
   * @param level 
   *          Course Level
   */
  public static void findByLevel(int level)
  {

    User user = Accounts.getCurrentUser();

    List<Course> allCourses = Course.findAll();
    Logger.info(allCourses + " size " + allCourses.size());

    List<Course> searchResults = new ArrayList<Course>();
    for (Course c : allCourses)
    {
      if (c.level == level)
        searchResults.add(c);
      Logger.info("searchResults: " + searchResults);
    }
    render(user, searchResults);
  }

  /*
   * Method allows Student to search for a course by entry points required by college 
   * in respect of a particular course.
   * 
   * @param points 
   *          Entry Points
   */
  public static void findByPoints(int points)
  {

    User user = Accounts.getCurrentUser();

    List<Course> allCourses = Course.findAll();
    Logger.info(allCourses + " size " + allCourses.size());

    List<Course> searchResults = new ArrayList<Course>();
    for (Course c : allCourses)
    {
      if (c.points == points)
        searchResults.add(c);
      Logger.info("searchResults: " + searchResults);
    }
    render(user, searchResults);
  }

  /*
   * Method allows Student to apply for a course.
   * 
   * @param id 
   *          Course id
   */
  public static void applyForCourse(Long id)
  {
    Course course = Course.findById(id);
    course.addApplicant((Student) Accounts.getCurrentUser());
    Home.index();
  }

}