package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class ApplicationsStatus extends Controller
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

}