package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import play.data.validation.*;
import play.data.validation.Error;

public class Accounts extends Controller
{

  public static void signup(boolean found)
  {
    Logger.info("Signup " + found);
    render(found);
  }

  public static void login()
  {
    String str = "";
    if (session.contains("Wrong Login"))
    {
      str = session.get("Wrong Login");
    }
    boolean wrongLogin = (str.equals("true")) ? true : false;

    render(wrongLogin);
  }

  public static void logout()
  {
    session.clear();
    index();
  }

  public static void index()
  {
    render();
  }

  /*
   * Method allows Student to register with service. Play validation for all
   * fields has been applied. If any of the fields are omitted then the same
   * registration form is displayed with omitted fields listed in red. Method
   * does not allow Student to take email which has already been taken by
   * another user.
   * 
   * @param firstName 
   *          Student's first name
   * @param lastName 
   *          Student's last name 
   * @param email 
   *          Student's email 
   * @param password 
   *          Student's password
   * @param date 
   *          Student's date of birth
   * @param gender 
   *          Student's gender
   * @param nationality 
   *          Student's nationality
   * @param mobileCountryCode 
   *          Student's Mobile Country Code
   * @param mobile 
   *          Student's mobile number
   * @param securityQestion 
   *          Student's security question
   * @param answer 
   *          Student's answer to security question.
   */
  public static void registerStudent(String firstName, String lastName, String email, String password, Date date,
      String gender, String nationality, String mobileCountryCode, int mobile, String securityQuestion, String answer)
  {

    Logger.info(firstName + " " + lastName + " " + email + " " + password + " " + date + " " + gender + " "
        + nationality + " " + mobileCountryCode + " " + mobile + " " + securityQuestion + " " + answer + " ");

    validation.required("First name", firstName);
    validation.required("Last name", lastName);
    validation.required("Email", email);
    validation.required("Password", password);
    validation.required("Date", date);
    validation.required("Gender", gender);
    validation.required("Nationality", nationality);
    validation.required("Mobile country code", mobileCountryCode);
    validation.required("Mobile", mobile);
    validation.required("Security question", securityQuestion);
    validation.required("answer", answer);

    if (validation.hasErrors())
    {
      render("Accounts/signup_forms/student_form.html");
    }

    boolean found = true;
    User user = User.findByEmail(email);

    if (user == null)
    {
      found = false;
      Student newStudent = new Student(firstName, lastName, email, password, date, gender, nationality,
          mobileCountryCode, mobile, securityQuestion, answer);
      newStudent.save();
      Accounts.authenticate(email, password);

    }
    else
    {
      Logger.info("This email has already been taken", email);
      Accounts.signup(found);
    }
  }

  /*
   * Method allows College representative to register with service. Play
   * validation for all fields has been applied. If any of the fields are
   * omitted then the same registration form is displayed with omitted fields
   * listed in red. Method does not allow person to take email which has already
   * been taken by another user.
   * 
   * @param firstName 
   *          College representative's first name
   * @param lastName 
   *          College representative's last name
   * @param email 
   *          College representative's email
   * @param password 
   *          College representative's password
   * 
   * @param mobileCountryCode 
   *          College representative's Mobile Country Code 
   * @param mobile 
   *          College representative's mobile number
   * @param securityQestion 
   *          College representative's security question
   * @param answer 
   *          College representative's answer to security question
   * @param collegeName 
   *          Name of the college
   * @param collegeAddress 
   *          Address of the college
   */
  public static void registerCollege(String firstName, String lastName, String email, String password,
      String mobileCountryCode, int mobile, String securityQuestion, String answer, String collegeName,
      String collegeAddress)
  {

    Logger.info(firstName + "\n" + lastName + "\n " + email + "\n " + password + " \n" + mobileCountryCode + " \n"
        + mobile + "\n " + securityQuestion + " \n" + answer + "\n " + collegeName + "\n " + collegeAddress + "\n ");

    validation.required("First name", firstName);
    validation.required("Last name", lastName);
    validation.required("Email", email);
    validation.required("Password", password);
    validation.required("Mobile country code", mobileCountryCode);
    validation.required("Mobile", mobile);
    validation.required("Security question", securityQuestion);
    validation.required("Answer", answer);
    validation.required("College name", collegeName);
    validation.required("College address", collegeAddress);

    if (validation.hasErrors())
    {
      render("Accounts/signup_forms/college_form.html");
    }

    boolean found = true;
    User user = User.findByEmail(email);
    if (user == null)
    {
      found = false;
      College newCollege = new College(firstName, lastName, email, password, mobileCountryCode, mobile,
          securityQuestion, answer, collegeName, collegeAddress);
      Logger.info("firstName: " + newCollege.firstName + " lastName: " + newCollege.lastName + "\n" + "email: "
          + newCollege.email + " password: " + newCollege.password);
      newCollege.save();
      Accounts.authenticate(email, password);

    }
    else
    {
      Logger.info("This email has already been taken", email);
      Accounts.signup(found);
    }
  }

  /*
   * Method allows Agency representative to register with service. Play
   * validation for all fields has been applied. If any of the fields are
   * omitted then the same registration form is displayed with omitted fields
   * listed in red. Method does not allow person to take email which has already
   * been taken by another user.
   * 
   * @param firstName 
   *          Agency representative's first name
   * @param lastName 
   *          Agency representative's last name
   * @param email 
   *          Agency representative's email
   * @param password 
   *          Agency representative's password
   * @param mobileCountryCode 
   *          Agency representative's Mobile Country Code
   * @param mobile 
   *          Agency representative's mobile number
   * @param securityQestion 
   *          Agency representative's security question
   * @param answer 
   *          Agency representative's answer to security question
   * @param agencyName 
   *          Name of the agency 
   * @param agencyAddress 
   *          Address of the agency
   */
  public static void registerAgency(String firstName, String lastName, String email, String password,
      String mobileCountryCode, int mobile, String securityQuestion, String answer, String agencyName,
      String agencyAddress)
  {

    Logger.info(firstName + "\n" + lastName + "\n " + email + "\n " + password + " \n" + mobileCountryCode + " \n"
        + mobile + "\n " + securityQuestion + " \n" + answer + "\n " + agencyName + "\n " + agencyAddress + "\n ");

    validation.required("First name", firstName);
    validation.required("Last name", lastName);
    validation.required("Email", email);
    validation.required("Password", password);
    validation.required("Mobile country code", mobileCountryCode);
    validation.required("Mobile", mobile);
    validation.required("Security question", securityQuestion);
    validation.required("Answer", answer);
    validation.required("Agency name", agencyName);
    validation.required("Agency address", agencyAddress);

    if (validation.hasErrors())
    {
      render("Accounts/signup_forms/agency_form.html");
    }

    boolean found = true;
    User user = User.findByEmail(email);
    if (user == null)
    {
      found = false;
      Agency newAgency = new Agency(firstName, lastName, email, password, mobileCountryCode, mobile, securityQuestion,
          answer, agencyName, agencyAddress);
      newAgency.save();
      Accounts.authenticate(email, password);

    }
    else
    {
      Logger.info("This email has already been taken", email);
      Accounts.signup(found);
    }
  }

  /*
   * Method allows Employer representative to register with service. Play
   * validation for all fields has been applied. If any of the fields are
   * omitted then the same registration form is displayed with omitted fields
   * listed in red. Method does not allow person to take email which has already
   * been taken by another user.
   * 
   * @param firstName 
   *          Employer representative's first name
   * @param lastName 
   *          Employer representative's last name
   * @param email 
   *          Employer representative's email
   * @param password 
   *          Employer representative's password
   * @param mobileCountryCode 
   *          Employer representative's Mobile Country Code
   * @param mobile 
   *          Employer representative's mobile number
   * @param securityQestion 
   *          Employer representative's security question 
   * @param answer 
   *          Employer representative's answer to security question
   * @param employerName 
   *          Name of the employer
   * @param employerAddress 
   *           Address of the employer
   */
  public static void registerEmployer(String firstName, String lastName, String email, String password,
      String mobileCountryCode, int mobile, String securityQuestion, String answer, String employerName,
      String employerAddress)
  {

    Logger.info(firstName + "\n" + lastName + "\n " + email + "\n " + password + " \n" + mobileCountryCode + " \n"
        + mobile + "\n " + securityQuestion + " \n" + answer + "\n " + employerName + "\n " + employerAddress + "\n ");

    validation.required("First name", firstName);
    validation.required("Last name", lastName);
    validation.required("Eamil", email);
    validation.required("Password", password);
    validation.required("Mobile country code", mobileCountryCode);
    validation.required("Mobile", mobile);
    validation.required("Security question", securityQuestion);
    validation.required("Answer", answer);
    validation.required("Employer name", employerName);
    validation.required("Employer address", employerAddress);

    if (validation.hasErrors())
    {
      render("Accounts/signup_forms/employer_form.html");
    }

    boolean found = true;
    User user = User.findByEmail(email);
    if (user == null)
    {
      found = false;
      Employer newEmployer = new Employer(firstName, lastName, email, password, mobileCountryCode, mobile,
          securityQuestion, answer, employerName, employerAddress);
      newEmployer.save();
      Accounts.authenticate(email, password);

    }
    else
    {
      Logger.info("This email has already been taken", email);
      Accounts.signup(found);
    }
  }

  /*
   * Method allows user to login. If login is successful user is taken to his
   * home page. If user's email or password are incorrect then appropriate
   * communicate is displayed and he is forced to login again.
   * 
   * @param email 
   *          User's email
   * @param password 
   *          User's password
   */
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + " pwd:" + password);
    User user = User.findByEmail(email);

    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_userid", user.id);
      Home.index();

    }
    else
    {
      Logger.info("Authentication failed");
      boolean wrongLogin = true;
      session.put("Wrong Login", wrongLogin);
      Accounts.login();
    }
  }

  /*
   * Method returns currently logged in user.
   */
  public static User getCurrentUser()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      return null;
    }
    User logged_in_user = User.findById(Long.parseLong(userId));
    Logger.info("In Accounts controller: Logged in user is " + logged_in_user);
    return logged_in_user;
  }
}
