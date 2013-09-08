package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.mvc.Controller;
import models.*;

public class AccommodationSearch extends Controller
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
   * Method allows Student to search for an accommodation by city.
   * 
   * @param city 
   *          City where accommodation is located.
   */
  public static void findByCity(String city)
  {

    String str = city.toLowerCase();
    Logger.info("findByCity str: " + str);

    User user = Accounts.getCurrentUser();
    Logger.info("User: " + user.firstName + " " + user.lastName + " ");

    List<Accommodation> allAccommodations = Accommodation.findAll();
    Logger.info("allAccommodations: " + allAccommodations + " size: " + allAccommodations.size());

    List<Accommodation> searchResults = new ArrayList<Accommodation>();
    for (Accommodation i : allAccommodations)
    {
      if (i.city.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }

    render(user, searchResults);
  }

  /*
   * Method allows Student to search for an accommodation based on
   * accommodation's class/ category.
   * 
   * @param accommodationClass 
   *          Accommodation class.
   */
  public static void findByAccommodationClass(String accommodationClass)
  {

    String str = accommodationClass.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Accommodation> allAccommodations = Accommodation.findAll();
    Logger.info("allAccommodations: " + allAccommodations + " size: " + allAccommodations.size());

    List<Accommodation> searchResults = new ArrayList<Accommodation>();
    for (Accommodation i : allAccommodations)
    {
      if (i.accommodationClass.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }

    render(user, searchResults);
  }

  /*
   * Method allows Student to search for an accommodation by monthly rent.
   * 
   * @param rent 
   *          Monthly rent in Euro.
   */
  public static void findByRent(int rent)
  {

    User user = Accounts.getCurrentUser();

    List<Accommodation> allAccommodations = Accommodation.findAll();
    Logger.info(allAccommodations + " size " + allAccommodations.size());

    List<Accommodation> searchResults = new ArrayList<Accommodation>();
    for (Accommodation i : allAccommodations)
    {
      if (i.rent == rent)
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }
    render(user, searchResults);
  }

  /*
   * Method allows Student to search for an accommodation based on 
   * accommodation type.
   * 
   * @param accommodationType 
   *          Accommodation type.
   */
  public static void findByAccommodationType(String accommodationType)
  {

    String str = accommodationType.toLowerCase();

    User user = Accounts.getCurrentUser();

    List<Accommodation> allAccommodations = Accommodation.findAll();
    Logger.info("allAccommodations: " + allAccommodations + " size: " + allAccommodations.size());

    List<Accommodation> searchResults = new ArrayList<Accommodation>();
    for (Accommodation i : allAccommodations)
    {
      if (i.accommodationType.toLowerCase().contains(str))
        searchResults.add(i);
      Logger.info("searchResults: " + searchResults);
    }

    render(user, searchResults);
  }

  /*
   * Method allows Student to book accommodation viewing.
   * 
   * @param id 
   *          Accommodation id.
   */
  public static void bookViewing(Long id)
  {
    Accommodation accommodation = Accommodation.findById(id);
    accommodation.addAccApplicant((Student) Accounts.getCurrentUser());
    Home.index();
  }
}