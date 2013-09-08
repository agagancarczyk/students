package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;
import play.Logger;
import play.mvc.Controller;

public class InternshipSearch extends Controller
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
   * Method allows Student to search for an internship based on the job title.
   * 
   * @param jobTitle 
   *          Job title.
   */

  public static void findByJobTitle(String jobTitle)
  {

    String str = jobTitle.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Internship> allInternships = Internship.findAll();
    Logger.info("allInternships: " + allInternships + " size: " + allInternships.size());

    List<Internship> searchResults = new ArrayList<Internship>();
    for (Internship i : allInternships)
    {
      if (i.jobTitle.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }

    render(user, searchResults);
  }

  /*
   * Method allows Student to search for an internship based on career sector.
   * 
   * @param careerSector 
   *          Career sector.
   */
  public static void findByCareerSector(String careerSector)
  {

    String str = careerSector.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Internship> allInternships = Internship.findAll();
    Logger.info("allInternships: " + allInternships + " size: " + allInternships.size());

    List<Internship> searchResults = new ArrayList<Internship>();
    for (Internship i : allInternships)
    {
      if (i.careerSector.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }
    render(user, searchResults);
  }

  /*
   * Method allows Student to apply for an internship.
   * 
   * @param id 
   *          Internship id.
   */
  public static void applyForInternship(Long id)
  {
    Internship internship = Internship.findById(id);
    internship.addIntern((Student) Accounts.getCurrentUser());
    Home.index();
  }
}
