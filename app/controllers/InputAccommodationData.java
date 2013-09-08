package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class InputAccommodationData extends Controller
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
   * Method allows Agency to add new accommodation to the list of
   * accommodations. Play validation has also been added.
   * 
   * @param city 
   *      City where accommodation is located
   * @param accommodationClass 
   *      Accommodation's class
   * @param furnishStatus 
   *      Accommodation's furnish status
   * @param rent 
   *      Rent per month for an accommodation
   * @param accommodationType   
   *      Accommodation's type 
   * @param nmrBathrooms 
   *      Number of bathrooms in an accommodation
   * @param nmrCommonRooms 
   *      Number of common rooms in an accommodation
   * @param nmrSingleBedrooms 
   *      Number of single bedrooms in an accommodation
   * @param nmrDoubleBedrooms 
   *      Number of double of bedrooms in an accommodation
   * @param nmrTwinBedrooms 
   *      Number of twin bedrooms in an accommodation
   * @param nmrOtherBedrooms; 
   *      Number of other bedrooms in an accommodation
   */
  public static void addAccommodation(String city, String accommodationClass, String furnishStatus, int rent,
      String accommodationType, int nmrBathrooms, int nmrCommonRooms, int nmrSingleBedrooms, int nmrDoubleBedrooms,
      int nmrTwinBedrooms, int nmrOtherBedrooms)
  {

    Logger.info("Adding Accomodations" + " " + city + " " + accommodationClass + " " + furnishStatus + " " + rent + " "
        + accommodationType + " " + nmrBathrooms + " " + nmrCommonRooms + " " + nmrSingleBedrooms + " "
        + nmrDoubleBedrooms + " " + nmrTwinBedrooms + " " + nmrOtherBedrooms + " ");

    Agency agency = (Agency) Accounts.getCurrentUser(); 
    Logger.info("addAccommodation" + agency);

    validation.required("City", city);
    validation.required("Accommodation class", accommodationClass);
    validation.required("Furnish status", furnishStatus);
    validation.required("Rent", rent);
    validation.required("Accommodation type", accommodationType);
    validation.required("Number of bathrooms", nmrBathrooms);
    validation.required("Number of common rooms", nmrCommonRooms);
    validation.required("Number of single bedrooms", nmrSingleBedrooms);
    validation.required("Number of double bedrooms", nmrDoubleBedrooms);
    validation.required("Number of twin bedrooms", nmrTwinBedrooms);
    validation.required("Number of other bedrooms", nmrOtherBedrooms);

    if (validation.hasErrors())
    {
      params.flash();
      validation.keep();
      index();
    }

    Accommodation newAccommodation = new Accommodation(agency, city, accommodationClass, furnishStatus, rent,
        accommodationType, nmrBathrooms, nmrCommonRooms, nmrSingleBedrooms, nmrDoubleBedrooms, nmrTwinBedrooms,
        nmrOtherBedrooms);
    newAccommodation.save();
    Logger.info("Accommodation newAccommodation" + " " + newAccommodation.city + " "
        + newAccommodation.accommodationClass + " " + newAccommodation.furnishStatus + " " + newAccommodation.rent
        + " " + newAccommodation.accommodationType + " " + newAccommodation.nmrBathrooms + " "
        + newAccommodation.nmrCommonRooms + " " + newAccommodation.nmrSingleBedrooms + " "
        + newAccommodation.nmrDoubleBedrooms + " " + newAccommodation.nmrTwinBedrooms + " "
        + newAccommodation.nmrOtherBedrooms + " ");

    agency.accommodations.add(newAccommodation);
    agency.save();
    index();
  }

  /*
   * Method allows Agency to remove accommodation from the list of
   * accommodations.
   * 
   * @param id 
   *          Accommodation id
   */
  public static void removeAccommodation(Long id)
  {
    Agency agency = (Agency) Accounts.getCurrentUser();
    Logger.info("Accommodation id:" + id);
    Accommodation accommodation = Accommodation.findById(id);
    agency.accommodations.remove(accommodation);
    agency.save();
    accommodation.delete();
    Logger.info("Removing accommodation" + accommodation);
    index();
  }
}