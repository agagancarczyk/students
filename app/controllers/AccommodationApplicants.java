package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class AccommodationApplicants extends Controller
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
   * Method allows Agency to reject Student who booked accommodation viewing.
   * 
   * @param accommodationId 
   *          Accommodation id
   * @param applicantId
   *          Student's id
   *          
   */
  public static void rejectFromViewing(Long accommodationId, Long applicantId)
  {
    Accommodation accommodation = Accommodation.findById(accommodationId);
    Student student = Student.findById(applicantId);
    accommodation.rejectApplicant(student);
    AccommodationApplicants.index();
  }

}