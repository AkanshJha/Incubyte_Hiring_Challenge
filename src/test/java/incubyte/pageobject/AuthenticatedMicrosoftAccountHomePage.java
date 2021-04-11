package incubyte.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import incubytes.basics.BaseClass;

public class AuthenticatedMicrosoftAccountHomePage extends BaseClass {

	WebDriver driver;

	public AuthenticatedMicrosoftAccountHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@role='presentation' and contains(@class,'ms-Icon--WaffleOffice365')]")
	WebElement waffleOfficeIcon_WE; // WE = Web Element

	@FindBy(id = "O365_AppTile_Mail")
	WebElement outlook_Link;

	// This element is visible when the user navigates to Outlook Mails Page
	@FindBy(id = "O365_AppName")
	public WebElement outlookHome_WE;

	public void navigateToOutlookMails() {
		
		getJavaScriptExecutor().executeScript("arguments[0].click();", waffleOfficeIcon_WE);
		//waffleOfficeIcon_WE.click();
		//outlook_Link.click();
		getJavaScriptExecutor().executeScript("arguments[0].click();", outlook_Link);
	}

}
