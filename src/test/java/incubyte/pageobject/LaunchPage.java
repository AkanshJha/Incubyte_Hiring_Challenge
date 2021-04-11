package incubyte.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import incubytes.basics.BaseClass;

public class LaunchPage extends BaseClass {

	WebDriver driver;

	public LaunchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Page Factory is instantiated.");
	}

	@FindBy(xpath = "//input[@name='loginfmt']")
	WebElement email_IB; // nameOfTheElement_TypeOfTheElement IB = InputBox

	@FindBy(xpath = "//input[@value='Next']")
	WebElement next_Email_Button;

	@FindBy(xpath = "//input[@name='passwd']")
	WebElement password_IB;

	@FindBy(xpath = "//input[@value='Sign in']")
	WebElement signIn_Button;

	// This element is available on the authenticated Home Page. This appears after
	// user logs in successfully.
	@FindBy(xpath = "//a[text()='Microsoft account']")
	public WebElement microsoftAccount_Link;

	public void login(String userName, String password) {
		email_IB.sendKeys(userName);
		next_Email_Button.click();
		password_IB.sendKeys(password);
		signIn_Button.click();

	}
}
