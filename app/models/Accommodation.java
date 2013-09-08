package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Accommodation extends Model
{
  public String city;
  public String accommodationClass;
  public String furnishStatus;
  public int rent;
  public String accommodationType;
  public int nmrBathrooms;
  public int nmrCommonRooms;
  public int nmrSingleBedrooms;
  public int nmrDoubleBedrooms;
  public int nmrTwinBedrooms;
  public int nmrOtherBedrooms;
  public Date date;

  @ManyToOne
  public Agency agency;

  @ManyToMany
  public List<Student> bookingApplicants = new ArrayList<Student>();

  public Accommodation(Agency agency, String city, String accommodationClass, String furnishStatus, int rent,
      String accommodationType, int nmrBathrooms, int nmrCommonRooms, int nmrSingleBedrooms, int nmrDoubleBedrooms,
      int nmrTwinBedrooms, int nmrOtherBedrooms)
  {

    this.agency = agency;
    this.city = city;
    this.accommodationClass = accommodationClass;
    this.furnishStatus = furnishStatus;
    this.rent = rent;
    this.accommodationType = accommodationType;
    this.nmrBathrooms = nmrBathrooms;
    this.nmrCommonRooms = nmrCommonRooms;
    this.nmrSingleBedrooms = nmrSingleBedrooms;
    this.nmrDoubleBedrooms = nmrDoubleBedrooms;
    this.nmrTwinBedrooms = nmrTwinBedrooms;
    this.nmrOtherBedrooms = nmrOtherBedrooms;
    this.date = new Date();
  }

  /*
   * Method allows Student to be added to the list of accommodation bookings.
   * 
   * @param student 
   *          Student
   */
  public void addAccApplicant(Student student)
  {
    bookingApplicants.add(student);
    save();
    student.bookedViewings.add(this);
    student.save();
  }

  /*
   * Method allows Student to be removed from the list of Students who booked
   * accommodation viewing.
   * 
   * @param student 
   *          Student
   */
  public void rejectApplicant(Student student)
  {
    bookingApplicants.remove(student);
    save();
    student.bookedViewings.remove(this);
    student.save();
  }
}
