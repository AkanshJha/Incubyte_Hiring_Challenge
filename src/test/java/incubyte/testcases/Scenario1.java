package incubyte.testcases;

import java.util.Map;

import org.testng.annotations.Test;

import incubyte.pageobject.AuthenticatedMicrosoftAccountHomePage;
import incubyte.pageobject.LaunchPage;
import incubyte.pageobject.OutlookHome;
import incubytes.basics.BaseClass;

public class Scenario1 extends BaseClass{
  @Test
  public void tc001() {
	  LaunchPage lp = new LaunchPage(getDriver());
	  openApplicationURL();
	  lp.login(userName, password);
	  
	  if(lp.microsoftAccount_Link.isDisplayed()) {
		  System.out.println("Logged in Successfully.");
	  }
	  else {
		  System.out.println("Could not login Successfully.");
	  }
	  
  }
  
  @Test
  public void tc002() {
	  AuthenticatedMicrosoftAccountHomePage home = new AuthenticatedMicrosoftAccountHomePage(getDriver());
	  home.navigateToOutlookMails();
	  
	  if(home.outlookHome_WE.isDisplayed()) {
		  System.out.println("Navigated to Outlook site");
	  }
	  else {
		  System.out.println("Could not Navigate to Outlook Site");
	  }

  }
  
  @Test(dataProvider = "DataSupplier", dataProviderClass = incubyte.utilities.DataSupplierClass.class)
  public void tc003(Map<Object, Object> map) {
	  OutlookHome oh = new OutlookHome(getDriver());
	  String recipients = (String) map.get("recipient");
	  String subject = (String) map.get("subject");
	  String body = (String) map.get("body");
	  
	  oh.clickOnNewMessageButton();
	  //oh.sendNewMessgae(recipients, subject, body);
	  
	  System.out.println(recipients);
	  System.out.println(subject);
	  System.out.println(body);
	  
  }
  
}
