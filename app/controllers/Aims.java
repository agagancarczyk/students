package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Aims extends Controller
{

  public static void index()
  {
    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Accounts.login();
    }
    else
    {
      String prog = getPercentTargetAchieved();
      String progress = prog + "%";
      Logger.info("Donation ctrler : user is " + user.email);
      Logger.info("Donation ctrler : percent target achieved " + progress);
      List<Donation> donations = report();
      render(user, progress, donations);
    }
  }

  /*
   * Method allows currently logged in user to donate.
   * 
   * @param amountDonated 
   *          Amount donated
   * @param methodDonated 
   *          Method donated
   */
  public static void donate(long amountDonated, String methodDonated)
  {
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);

    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Logger.info("Donation class : Unable to getCurrentuser");
      Accounts.login();
    }
    else
    {
      addDonation(user, amountDonated, methodDonated);
      report();
    }
    index();
  }

  /*
   * Method adds donations.
   * 
   * @param user 
   *          User
   * @param amountDonated 
   *          Amount donated
   */
  private static void addDonation(User user, long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, amountDonated, methodDonated);
    bal.save();
  }

  public static void donation()
  {
    Aims.index();
  }

  /*
   * Method returns target of 800.
   */
  private static long getDonationTarget()
  {
    return 800;
  }

  /*
   * Method returns target achieved in percentages.
   */
  public static String getPercentTargetAchieved()
  {
    List<Donation> allDonations = Donation.findAll();
    long total = 0;
    for (Donation donation : allDonations)
    {
      total += donation.received;
    }
    long target = getDonationTarget();
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress + " percentachieved (long)= " + percentachieved);
    return progress;
  }

  /*
   * Method returns list of donations.
   */
  public static List<Donation> report()
  {
    User user = Accounts.getCurrentUser();
    List<Donation> donations = user.donations;
    Logger.error("donations size: " + donations.size());
    return donations;
  }
}