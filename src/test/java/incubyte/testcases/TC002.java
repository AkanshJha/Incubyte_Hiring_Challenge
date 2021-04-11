package incubyte.testcases;

import org.testng.annotations.Test;

import incubyte.pageobject.AuthenticatedMicrosoftAccountHomePage;
import incubyte.pageobject.LaunchPage;
import incubytes.basics.BaseClass;

public class TC002 extends BaseClass {
	@Test
	public void tc002() {
		LaunchPage lp = new LaunchPage(getDriver());
				
		openApplicationURL();
		//System.out.println(userName + "\n" + password);
		lp.login(userName, password); // These are the use name and password are fetched
		
		if(lp.microsoftAccount_Link.isDisplayed()) {
			System.out.println("Logged in Successfully.");
		}
		else {
			System.out.println("Could not login successfully.");
		}
		
		AuthenticatedMicrosoftAccountHomePage loggedIn = new AuthenticatedMicrosoftAccountHomePage(getDriver());
		//Navigate to Outlook Mails
		loggedIn.navigateToOutlookMails();
		
		 if(loggedIn.outlookHome_WE.isDisplayed()) {
			  System.out.println("Navigated to Outlook site");
		  }
		  else {
			  System.out.println("Could not Navigate to Outlook Site");
		  }
		
	}
}
