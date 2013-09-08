package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller
{

  public static void index()
  {
    User user = Accounts.getCurrentUser();
    render(user);
  }

  public static void terms()
  {
    render();
  }
}