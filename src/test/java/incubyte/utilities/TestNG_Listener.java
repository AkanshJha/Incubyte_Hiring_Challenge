package incubyte.utilities;

import java.io.File;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import incubytes.basics.BaseClass;


/**
 * 
 * @author Akansh Jha
 *
 */
public class TestNG_Listener implements ITestListener {
	
	static ExtentReports extent = null;
	static ExtentTest test;
	Object[] param = null;
	Map<Object, Object> map = null;
	
	/**
	 * While the Suite execution, it returns the current Test object.
	 * And When a single class/Test is being executed, It creates new objects.
	 * @return the current ExtentTest Object
	 */
	public static ExtentTest getExtentTestObject() {
		if(test!=null) {
		return test;
		}
		else {
			extent = ExtentReporterNG.getExtentReport();
			test = extent.createTest("Current Test Case");
			return test;
		}
		
	}

	// for single test case/class execution and generating the report.
	public static void flushExtent() {
		//TestNG_Listener tngl = new TestNG_Listener();
		extent.flush();
		System.out.println("Extent Report, for current Test Case has been flushed.");
	}

	
	@SuppressWarnings("unchecked")
	public void onTestStart(ITestResult result) {
		// ITestListener.super.onTestStart(result);
		// test = extent.createTest(result.getMethod().getMethodName());
		param = result.getParameters();
		if (param.length > 0) {
			map = (Map<Object, Object>) param[0];
			// test = extent.createTest(result.getMethod().getMethodName());
			test = extent.createTest(result.getMethod().getMethodName() + " for data " + map);
		} else {
			test = extent.createTest(result.getMethod().getMethodName());
		}
		System.out.println(map);
		System.out.println("Report of the currently executing test case '" + result.getName() + "' is being created.");
	}

	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getName();
		// writing these logs to execution report

		/*
		 * test.log(Status.PASS, MarkupHelper.createLabel(result.getName(),
		 * ExtentColor.GREEN)); if (param.length > 0) { test.log(Status.PASS,
		 * "for test data, " + map); }
		 */
		test.log(Status.PASS, "Test case '" + result.getName() + "' is Passed.");

		// writing these log to the log file.
		System.out.println(testCaseName + " Status : PASS");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailure(result);
		String testCaseName = result.getMethod().getMethodName();
		String screenshotPath = "";
		File f = null;
		// result.getName() == result.getMethod().getMethodName()
		// writing these logs to the Execution Report
		/*
		 * test.log(Status.FAIL, MarkupHelper.createLabel(testCaseName,
		 * ExtentColor.RED)); if (param.length > 0) { test.log(Status.FAIL,
		 * "for test data, " + map); }
		 */
		test.fail(result.getThrowable());

		// writing these logs to the log file
		System.out.println(testCaseName + " Status : FAIL");
		System.out.println("As test case '" + testCaseName + "' is failed, taking a screenshot of it.");
		try {
			screenshotPath = BaseClass.getScreenshotForGivenTestCase(testCaseName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured while taking a screenshot. Please check the stack trace below : ");
			e.printStackTrace();
		}
		f = new File(screenshotPath);
		if (f.exists()) {
			// test.fail("Screenshot is attcahed below:"+ test.addScreenCaptureFromPath(screenshotPath, testCaseName));
			test.fail("Screenshot is attcahed below:"+ test.addScreenCaptureFromPath(screenshotPath));
		}
		
		// test.createNode("testing create node method.");
		// test.info("testing info method.");
		test.log(Status.FAIL, "Test Case is Failed.");
	}

	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		// TODO Auto-generated method stub
		// ITestListener.super.onTestSkipped(result);
		// writing these logs to the Execution Report
		test.log(Status.SKIP, MarkupHelper.createLabel(testCaseName, ExtentColor.ORANGE));
		test.skip("This Test is skipped.");

		// writing these logs to the log file
		System.out.println(testCaseName + " Status : SKIP");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		// ITestListener.super.onStart(context);
		if (extent == null) {
			extent = ExtentReporterNG.getExtentReport();
			System.out.println("ExtentReports object has been retrieved. Report creation has been started.");
		}
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		// ITestListener.super.onFinish(context);
		extent.flush();
		System.out.println("Extent Report has been flushed.");
	}

}

