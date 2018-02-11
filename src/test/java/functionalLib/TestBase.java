package functionalLib;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import utilities.PropertyManager;

/**
 * @author shwetankvashishtha
 *
 */
public class TestBase extends WebDriverTestBase implements ITestListener, ISuiteListener, IInvokedMethodListener{

	PropertyManager propertyManager = new PropertyManager();

	@Override
	public WebDriver getdriver() {
		return driver;
	}

	@Override
	public void setupBrowser(String browser, String URL) {
		if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", propertyManager.getResourceBundle.getProperty("IE_DRIVER_PATH"));
			driver = new InternetExplorerDriver();
			openURL(URL);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", propertyManager.getResourceBundle.getProperty("GECKO_DRIVER_PATH"));
			driver = new FirefoxDriver();
			openURL(URL);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					propertyManager.getResourceBundle.getProperty("CHROME_DRIVER_PATH"));
			driver = new ChromeDriver();
			openURL(URL);
			driver.manage().window().maximize();
		}
		Reporter.log(browser + " launched successfully");
		Reporter.log(URL + " passed to browser");
	}

	@Override
	public void openURL(String AUT_URL) {
		driver.get(AUT_URL);
		Reporter.log(AUT_URL + " has been opened in browser successfully");
	}

	@Override
	public void teardownTest() {
		refreshPage();
		shutdownBrowser();
		generateTestReport();
	}

	@Override
	public void refreshPage() {
		driver.navigate().refresh();
	}

	@Override
	public void shutdownBrowser() {
		clearCache();
		closeBrowser();
	}

	@Override
	public void clearCache() {
		driver.manage().deleteAllCookies();
	}

	@Override
	public void closeBrowser() {
		driver.quit();
	}

	@Override
	public void generateTestReport() {
		setupBrowser(propertyManager.getResourceBundle.getProperty("BROWSER1"),
				propertyManager.getResourceBundle.getProperty("REPORT_LOCATION"));
	}

	@Override
	public void openCurrentBrowserInstance() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
	}

	@Override
	public void openNewBrowserTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	@Override
	public void pause(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void implicitWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void fluentWait(long timeout) {
		new FluentWait(driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

	}

	@Override
	public void waitForPageLoad(long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	@Override
	public void waitForElementPresent(long timeout, String element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
	}

	@Override
	public void waitForElementVisible(long timeout, WebElement element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		// new WebDriverWait(driver,
		// timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
	}

	@Override
	public void waitForElementDisappear(long timeout, WebElement element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
	}

	@Override
	public void waitForElementToBeClickable(long timeout, WebElement element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void waitForPageExpire(long timeout) {
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void setAsyncScriptTimeout(long timeout) {
		driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void waitForTextToBePresentInElement(long timeout, WebElement element, String text) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	@Override
	public void waitForAlertPresent(long timeout, WebElement element) {

	}

	@Override
	public void waitForPageTitle(long timeout, String pageTitle) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.titleIs(pageTitle));
	}

	@Override
	public void frameToBeAvailableAndSwitch(long timeout, String frameID) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameID)));
	}

	@Override
	public void captureScreenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result.getName());
		}
	}

	@Override
	public void captureScreenshot(String screenshotName) {
		Calendar cal = Calendar.getInstance();
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File screenshotSrc = takesScreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotSrc,
					new File(propertyManager.getResourceBundle.getProperty("SCREENSHOT_LOCATION") + cal.getTime()
							+ screenshotName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyTrue(Boolean Condition, String SuccessMessage, String FailureMessage) {
		if (Condition) {
			Reporter.log(SuccessMessage, true);
			return true;
		} else {
			Reporter.log(FailureMessage, false);
			return false;
		}
	}

	@Override
	public boolean verifyFalse(Boolean Condition, String SuccessMessage, String FailureMessage) {
		if (!Condition) {
			Reporter.log(SuccessMessage, true);
			return true;
		} else {
			Reporter.log(FailureMessage, false);
			return false;
		}
	}

	@Override
	public void assertTrue(boolean condition, String successMessage, String failureMessage) {
		if (condition) {
			Reporter.log(successMessage, true);
		} else {
			Reporter.log(failureMessage, false);
			throw new AssertionError("Assertion Error");
		}
	}

	@Override
	public void assertFalse(boolean condition, String successMessage, String failureMessage) {
		if (condition) {
			Reporter.log(successMessage, true);
		} else {
			Reporter.log(failureMessage, false);
			throw new AssertionError("Assertion Error");
		}
	}

	@Override
	public void waitForElementPresent() {
		// TODO Auto-generated method stub

	}

	// This belongs to ISuiteListener and will execute before the Suite start
	@Override
	public void onStart(ISuite arg0) {
		Reporter.log("About to begin executing Suite " + arg0.getName(), true);
	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished
	@Override
	public void onFinish(ISuite arg0) {
		Reporter.log("About to end executing Suite " + arg0.getName(), true);
	}

	// This belongs to ITestListener and will execute before starting of Test
	// set/batch
	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("About to begin executing Test " + arg0.getName(), true);
	}

	// This belongs to ITestListener and will execute, once the Test set/batch is
	// finished
	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	// This belongs to ITestListener and will execute only when the test is pass
	@Override
	public void onTestSuccess(ITestResult arg0) {
		// This is calling the printTestResults method
		printTestResults(arg0);
	}

	// This belongs to ITestListener and will execute only on the event of fail test
	@Override
	public void onTestFailure(ITestResult arg0) {
		// This is calling the printTestResults method
		printTestResults(arg0);
	}

	// This belongs to ITestListener and will execute before the main test start
	// (@Test)
	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("The execution of the main test starts now");
	}

	// This belongs to ITestListener and will execute only if any of the main
	// test(@Test) get skipped
	@Override
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	// This belongs to IInvokedMethodListener and will execute before every method
	// including @Before @After @Test
	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	// This belongs to IInvokedMethodListener and will execute after every method
	// including @Before @After @Test
	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	// This will return method names to the calling function
	@Override
	public String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

	// This will print results
	@Override
	public void printTestResults(ITestResult result) {
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}
