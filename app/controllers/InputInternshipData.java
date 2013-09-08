package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputInternshipData extends Controller
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
   * Method allows Employer to add new internship to the list of internships.
   * Validation has also been added.
   * 
   * @param jobTitle 
   *          Job title.
   * @param careerSector 
   *          Career Sector
   */
  public static void addInternship(String jobTitle, String careerSector)
  {

    Logger.info("Adding Internships" + " " + jobTitle + " " + careerSector + " ");

    Employer employer = (Employer) Accounts.getCurrentUser(); 
    Logger.info("addInternship" + employer);

    validation.required("Job title", jobTitle);
    validation.required("Career sector", careerSector);

    if (validation.hasErrors())
    {
      params.flash();
      validation.keep();
      index();
    }

    Internship newInternship = new Internship(employer, jobTitle, careerSector);
    newInternship.save();
    Logger.info("Internship newInternship" + " " + newInternship + " " + newInternship.careerSector + " ");

    employer.internships.add(newInternship);
    employer.save();
    index();
  }

  /*
   * Method allows Employer to remove internship from the list of internships.
   * 
   * @param id 
   *          Internship id
   */
  public static void removeInternship(Long id)
  {

    Employer employer = (Employer) Accounts.getCurrentUser();
    Logger.info("Internship id:" + id);
    Internship internship = Internship.findById(id);
    employer.internships.remove(internship);
    employer.save();
    internship.delete();
    Logger.info("Removing internship" + internship);
    index();
  }
}