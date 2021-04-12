package stepdefinitions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import incubyte.pageobject.AuthenticatedMicrosoftAccountHomePage;
import incubyte.pageobject.LaunchPage;
import incubyte.pageobject.OutlookHome;
import incubytes.basics.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Steps_TC003 extends BaseClass {

	WebDriver driver;
	SoftAssert sftasrt = new SoftAssert();
	
	@Given("^User has successfully logged in Outlook application$")
	public void user_has_successfully_logged_in_outlook_application() {
		//In this method we are setting up the browser. Launching Application. Logging in. Navigating to Outlook.
		
		// Setting up the browser, given in the properties file.
		setupBrowser();
		
		driver = getDriver();
		// Opening the Outlook URL
		openApplicationURL();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Loading the Launch page class objects/UI Elements
		LaunchPage lp = new LaunchPage(driver);
		// logging into the system. >> userName and password are fetched from the
		// BaseClass
		lp.login(userName, password);

		// validating whether the user has logged in successfully.
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
	}

	@When("^User clicks on New Message button$")
	public void user_clicks_on_new_message_button() {
		//Instantiating the OutllokHome Page and its UI elements.
		OutlookHome oh = new OutlookHome(driver);

		//Clicking on New Message Button
		oh.clickOnNewMessageButton();
		//overriding its value again, so the newly visible elements can be instantiated
		oh = new OutlookHome(getDriver());
		if (oh.sendMail_Button.isDisplayed())
			System.out.println("You are ready to draft the message.");
		else
			sftasrt.assertEquals(false, true, "You can not draft the new messgae.");
	}

	@When("drafts email and sends email")
	public void drafts_email_and_sends_email() {
		OutlookHome oh = new OutlookHome(driver);
		String recipients = BaseClass.properties.getProperty("recipients");
		String subject = BaseClass.properties.getProperty("subject");
		String body = BaseClass.properties.getProperty("body");
		oh.sendNewMessgae(recipients, subject, body);

		try {
			getExplicit_wait().until(ExpectedConditions.invisibilityOf(oh.sendMail_Button));
			System.out.println("Message Sent Successfully.");
		} catch (TimeoutException e) {
			sftasrt.assertEquals(false, true, "Message is not sent.");
		} catch (NoSuchElementException e2) {
			sftasrt.assertEquals(false, true, "Message sent Successfully(Send Button is not displayed).");
		}
		
		//tearing down the browser
		try {
			tearDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
