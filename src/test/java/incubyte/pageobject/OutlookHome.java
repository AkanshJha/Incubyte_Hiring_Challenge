package incubyte.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import incubytes.basics.BaseClass;

public class OutlookHome {
	WebDriver driver;

	public OutlookHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='New message']")
	WebElement newMessgae_Button;
	
	@FindBy(xpath = "//div[text()='To']/following-sibling::input")
	WebElement toRecepient_IB;
	
	@FindBy(xpath = "//input[@placeholder='Add a subject']")
	WebElement subjectOfMail_IB;
	
	@FindBy(xpath = "//div[@aria-label='Message body']")
	WebElement bodyOfMail_IB;
	
	@FindBy(xpath = "//button[@aria-label='Send']")
	public WebElement sendMail_Button;
	
	
	public void clickOnNewMessageButton() {
		//newMessgae_Button.click();
		new BaseClass().getJavaScriptExecutor().executeScript("arguments[0].click();", newMessgae_Button);
	}
	
	public void sendNewMessgae(String recipients, String subject, String body) {
		//newMessgae_Button.click();
		subjectOfMail_IB.sendKeys(subject);
		bodyOfMail_IB.sendKeys(body);
		toRecepient_IB.sendKeys(recipients);
		sendMail_Button.click();
	}
}
