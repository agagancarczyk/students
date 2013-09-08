package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InternshipApplicants extends Controller
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
   * Method allows Employer to reject Student who applied for an internship.
   * 
   * @param internshipId 
   *          Internship id
   * @param applicantId
   *          Student's id
   */
  public static void rejectFromInternship(Long internshipId, Long applicantId)
  {
    Internship internship = Internship.findById(internshipId);
    Student student = Student.findById(applicantId);
    internship.rejectIntern(student);
    InternshipApplicants.index();
  }

}