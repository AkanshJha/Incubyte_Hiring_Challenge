package incubytes.basics;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class BasicOperations extends BaseClass {

	/**
	 * @param inputBox  : WebElement of input Box, you want to set data in.
	 * @param dataToSet : Data to be set in input box
	 */
	public void setData(WebElement inputBox, String dataToSet) {
		try {
			if (inputBox.isDisplayed()) {
				inputBox.clear();
				inputBox.sendKeys(dataToSet);
				writeToReport(Status.PASS, "Input Box is cleared and data '" + dataToSet + "' is set to input box '"
						+ getWebElementDetails(inputBox) + "'.");
			} else {
				writeToReport(Status.FAIL, "Input box '" + getWebElementDetails(inputBox) + "' is not displayed.");
			}
		} catch (NoSuchElementException e) {
			writeToReport(Status.FAIL, "Could not locate the input box with '" + getWebElementDetails(inputBox) + "'.");
		}
	}

	/**
	 * @param radioButton : WebElement of RadioButton, we want to select.
	 */
	public void selectRadioButton(WebElement radioButton) {
		try {
			if (radioButton.isDisplayed()) {
				radioButton.click();
				writeToReport(Status.PASS,
						"Radio Button is selected with '" + getWebElementDetails(radioButton) + "'.");
			} else {
				writeToReport(Status.FAIL,
						"Radio Button with '" + getWebElementDetails(radioButton) + "' is not displayed.");
			}
		} catch (NoSuchElementException e) {
			writeToReport(Status.FAIL,
					"Could not locate the radio button with '" + getWebElementDetails(radioButton) + "'.");
		}
	}

	public void clickOnElement(WebElement elementToBeClicked) {
		BaseClass bc = new BaseClass();
		try {
			if (elementToBeClicked.isDisplayed()) {
				elementToBeClicked.click();
				bc.writeToReport(Status.PASS,
						"Element with '" + getWebElementDetails(elementToBeClicked) + "' is clicked successfully.");
			} else {
				bc.writeToReport(Status.FAIL, "Element with '" + getWebElementDetails(elementToBeClicked)
						+ "' is not displayed. Please verify.");
			}
		} catch (NoSuchElementException e) {
			bc.writeToReport(Status.FAIL,
					"Element with '" + getWebElementDetails(elementToBeClicked) + "' does not exists.");
		}
	}

	public void openURL(String url) {
		BaseClass bc = new BaseClass();
		if (url.equals("") || url.isEmpty() || url == null) {
			bc.writeToReport(Status.FAIL, "URL is empty. Please provide the valid URL.");
			return;
		}
		BaseClass.getDriver().get(url);
		bc.writeToReport(Status.PASS, "Application URL, '" + url + "' is opened in the selected browser.");
	}

	/**
	 * @param element : the element, for which we want to pick the locator value
	 *                details.
	 * @return : returns the locator value.
	 */
	public String getWebElementDetails(WebElement element) {
		String details = "";
		String elementToString = element.toString();
		String[] arrOfString = elementToString.split("-> ");
		elementToString = arrOfString[1];
		details = elementToString.substring(0, elementToString.length() - 1);
		return "<b>" + details + "</b>";
	}

}
