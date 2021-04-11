package incubyte.testcases;

import org.testng.annotations.Test;

import incubyte.pageobject.LaunchPage;
import incubytes.basics.BaseClass;

//TC001 - Login in Microsoft with valid credentials

public class TC001 extends BaseClass {

	@Test
	public void tc001() {
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
	}

}
