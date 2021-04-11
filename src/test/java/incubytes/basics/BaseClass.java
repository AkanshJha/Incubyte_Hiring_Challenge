package incubytes.basics;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import incubyte.utilities.ReadPropertiesUtils;
import incubyte.utilities.TestNG_Listener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	private static WebDriver driver = null;
	protected static Properties properties = new Properties();
	public String applicationURL = "";
	public String userName = "";
	public String password = "";
	private static String currentDirectory = System.getProperty("user.dir");
	private WebDriverWait explicit_wait;
	private JavascriptExecutor javaScriptExecutor;

	/**
	 * This is called before each test starts. This method sets up the browser as
	 * per given in the properties file.
	 */
	@BeforeTest
	//@BeforeClass
	protected void setupBrowser() {
		String propFilePath = getCurrentDirectory() + "\\configFiles\\ApplicationData.properties";

		properties = ReadPropertiesUtils.loadPropertiesFile(propFilePath);
		String requiredBrowser = properties.getProperty("browser");
		int implicitWaitTime = 5; // Default Value
		int explicitWaitTime = 10; // Default Value

		// invoking the required browser
		invokeRequiredBrowser(requiredBrowser);

		// maximizing browser window
		driver.manage().window().maximize();

		implicitWaitTime = ReadPropertiesUtils.getIntegerPropertyValue(properties, "implicit_wait_time");
		if (implicitWaitTime != -1) {
			System.out.println("Setting Implicit wait to " + implicitWaitTime + " seconds for the execution.");
		} else {
			implicitWaitTime = 5;
			System.out.println("Setting Implicit wait to default value " + implicitWaitTime
					+ " seconds for the execution. Please update the properties file if you want to update this time.");
		}
		// setting implicit wait value

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));

		explicitWaitTime = ReadPropertiesUtils.getIntegerPropertyValue(properties, "explicit_wait_time");
		if (explicitWaitTime != -1) {
			System.out.println("Setting Explicit wait to " + explicitWaitTime + " seconds for the execution.");
		} else {
			explicitWaitTime = 10;
			System.out.println("Setting Explicit wait to default value " + explicitWaitTime
					+ " seconds for the execution. Please update the properties file if you want to update this time.");
		}
		// setting explicit wait
		explicit_wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));

		applicationURL = ReadPropertiesUtils.getStringPropertyValue(properties, "application_url");
		if (applicationURL.equals("") && applicationURL == null && applicationURL.isEmpty()) {
			System.out.println(
					"Application URL is Blank/Null. Please provide the URL correctly in .properties file.\nTerminating the execution.");
			System.exit(0);
		}
		userName = ReadPropertiesUtils.getStringPropertyValue(properties, "userName");
		password = ReadPropertiesUtils.getStringPropertyValue(properties, "password");

		// initializing the JavaScriptExecutor object
		setJavaScriptExecutor();

	}

	/**
	 * This method will be called After each test ends to close the Browser and
	 * sessions.
	 * 
	 * @throws InterruptedException
	 */
	//@AfterClass
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Tearing Down the Class.");
		System.out.println("Closing all the opened browsers.");
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("Error occured while closing all the browsers.");
			System.out.println("Execution finished.");
		}
	}

	/**
	 * 
	 * @return the current directory of the project.
	 */
	public static String getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * 
	 * @return the current driver object.
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * this method invokes the browser using Selenium given in parameter
	 * 
	 * @param browserName
	 */
	private void invokeRequiredBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.out.println("Fetching the Chrome driver from WebDriverManager.");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("Instantiated the Chrome Driver.");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.out.println("Fetching the Firefox driver from WebDriverManager.");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("Instantiated the Firefox Driver.");
			} else if (browserName.equalsIgnoreCase("ie")) {
				System.out.println("Fetching the Internet Explorer driver from WebDriverManager.");
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				System.out.println("Instantiated the Internet Explorer Driver.");

			} else if (browserName.equalsIgnoreCase("edge")) {
				System.out.println("Fetching the Edge driver from WebDriverManager.");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				System.out.println("Instantiated the Edge Driver.");
			} else {
				System.out.println(
						"Browser value is not given or incorrect in properties file. Please check.\nRunning on Chrome Browser by default.");
				// System.setProperty("webdriver.chrome.driver",currentDirectory+"\\driver\\chromedriver.exe");
				System.out.println("Fetching the Chrome driver from WebDriverManager.");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("Instantiating the Chrome Driver.\n");
			}
		} catch (Exception e) {
			System.out.println("Error occured while fetching/instantiating the browser driver. Please check.");
			e.printStackTrace();
			System.out.println("Terminating the whole execution.\n");
			System.exit(0);
		}
	}

	/**
	 * This method takes the screenshot and saves the file with
	 * 
	 * @param testCaseName
	 * @return
	 * @throws Exception
	 */
	public static String getScreenshotForGivenTestCase(String testCaseName) throws Exception {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		String destination = currentDirectory + "//reports//screenshots//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	/**
	 * 
	 * @param status  : Status you wanna pass, Status.PASS o Status.FAIL
	 * @param message : message you want to attach to the report for the test case.
	 */
	public void writeToReport(Status status, String message) {
		ExtentTest test = TestNG_Listener.getExtentTestObject();
		test.log(status, message);
		System.out.println("message has been added to the extent report from the test case.");
	}

	/**
	 * To open the application URL given in properties file
	 */
	public void openApplicationURL() {
		// BasicOperationsOnElements op = new BasicOperationsOnElements(); // this we
		// can use if we want to keep additional logging/messages
		// op.openURL(applicationURL);
		driver.get(applicationURL);
	}

	/**
	 * 
	 * @return the current object of WebDrvierWait, initiated in setupBrowser()
	 *         method
	 */
	public WebDriverWait getExplicit_wait() {
		return explicit_wait;
	}

	/**
	 * @return the javaScriptExecutor
	 */
	public JavascriptExecutor getJavaScriptExecutor() {
		if(javaScriptExecutor!=null)
			return javaScriptExecutor;
		else
			return (JavascriptExecutor) driver;
	}

	/**
	 * @param javaScriptExecutor the javaScriptExecutor to set
	 */
	public void setJavaScriptExecutor() {
		javaScriptExecutor = (JavascriptExecutor) driver;
	}
}
