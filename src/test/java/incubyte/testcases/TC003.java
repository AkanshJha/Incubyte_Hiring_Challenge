package incubyte.testcases;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import incubyte.pageobject.AuthenticatedMicrosoftAccountHomePage;
import incubyte.pageobject.LaunchPage;
import incubyte.pageobject.OutlookHome;
import incubytes.basics.BaseClass;

public class TC003 extends BaseClass {
	// @Test(dataProvider = "DataSupplier", dataProviderClass =
	// incubyte.utilities.DataSupplierClass.class)
	@Test
	public void tc003() {

		LaunchPage lp = new LaunchPage(getDriver());
		SoftAssert sftasrt = new SoftAssert();
		String recipients = BaseClass.properties.getProperty("recipients");
		String subject = BaseClass.properties.getProperty("subject");
		String body = BaseClass.properties.getProperty("body");

		openApplicationURL();
		// System.out.println(userName + "\n" + password);
		lp.login(userName, password); // These are the use name and password are fetched

		if (lp.microsoftAccount_Link.isDisplayed())
			System.out.println("Logged in Successfully.");
		else
			sftasrt.assertEquals(false, true, "Could not login successfully.");

		AuthenticatedMicrosoftAccountHomePage loggedIn = new AuthenticatedMicrosoftAccountHomePage(getDriver());
		// Navigate to Outlook Mails
		loggedIn.navigateToOutlookMails();

		if (loggedIn.outlookHome_WE.isDisplayed())
			System.out.println("Navigated to Outlook site");
		else
			sftasrt.assertEquals(false, true, "Could not Navigate to Outlook Site");

		OutlookHome oh = new OutlookHome(getDriver());

		oh.clickOnNewMessageButton();
		oh = new OutlookHome(getDriver());
		if (oh.sendMail_Button.isDisplayed())
			System.out.println("You are ready to draft the message.");
		else
			sftasrt.assertEquals(false, true, "You can not draft the new messgae.");

		oh.sendNewMessgae(recipients, subject, body);

		try {
			getExplicit_wait().until(ExpectedConditions.invisibilityOf(oh.sendMail_Button));
			System.out.println("Message Sent Successfully.");
		} catch (TimeoutException e) {
			sftasrt.assertEquals(false, true, "Message is not sent.");
		} catch (NoSuchElementException e2) {
			sftasrt.assertEquals(false, true, "Message sent Successfully(Send Button is not displayed).");
		}
	}
}
