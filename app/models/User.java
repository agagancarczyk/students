package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import play.Logger;
import play.db.jpa.Model;

@Entity
public abstract class User extends Model
{

  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public String mobileCountryCode;
  public int mobile;
  public String securityQuestion;
  public String answer;

  @OneToMany(mappedBy = "from")
  public List<Donation> donations = new ArrayList<Donation>();

  public User(String firstName, String lastName, String email, String password, String mobileCountryCode, int mobile,
      String securityQuestion, String answer)
  {

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.mobileCountryCode = mobileCountryCode;
    this.mobile = mobile;
    this.securityQuestion = securityQuestion;
    this.answer = answer;
  }

  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}
