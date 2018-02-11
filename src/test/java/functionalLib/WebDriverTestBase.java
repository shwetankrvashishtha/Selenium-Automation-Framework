package functionalLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * @author shwetankvashishtha
 *
 */
public abstract class WebDriverTestBase {

	protected static WebDriver driver;

	public abstract WebDriver getdriver();

	public abstract void setupBrowser(String browser, String URL);

	public abstract void clearCache();

	public abstract void shutdownBrowser();

	public abstract void closeBrowser();

	public abstract void openURL(String AUT_URL);

	public abstract void assertFalse(boolean condition, String successMessage, String failureMessage);

	public abstract void assertTrue(boolean condition, String successMessage, String failureMessage);

	public abstract boolean verifyFalse(Boolean Condition, String SuccessMessage, String FailureMessage);

	public abstract boolean verifyTrue(Boolean Condition, String SuccessMessage, String FailureMessage);

	public abstract void generateTestReport();

	public abstract void refreshPage();

	public abstract void waitForElementToBeClickable(long timeout, WebElement element);

	public abstract void waitForElementDisappear(long timeout, WebElement element);

	public abstract void waitForElementVisible(long timeout, WebElement element);

	public abstract void waitForElementPresent(long timeout, String element);

	public abstract void waitForPageLoad(long timeout);

	public abstract void fluentWait(long timeout);

	public abstract void implicitWait(long timeout);

	public abstract void pause(long timeout);

	public abstract void waitForPageExpire(long timeout);

	public abstract void waitForTextToBePresentInElement(long timeout, WebElement element, String text);

	public abstract void waitForAlertPresent(long timeout, WebElement element);

	public abstract void setAsyncScriptTimeout(long timeout);

	public abstract void waitForPageTitle(long timeout, String pageTitle);

	public abstract void frameToBeAvailableAndSwitch(long timeout, String frameID);

	public abstract void openCurrentBrowserInstance();

	public abstract void openNewBrowserTab();

	public abstract void waitForElementPresent();

	public abstract void captureScreenshotOnFailure(ITestResult result);

	public abstract void captureScreenshot(String screenshotName);

	public abstract void teardownTest();

	public abstract void onStart(ISuite arg0);

	public abstract void onFinish(ISuite arg0);

	public abstract void onStart(ITestContext arg0);

	public abstract void onFinish(ITestContext arg0);

	public abstract String returnMethodName(ITestNGMethod method);

	public abstract void afterInvocation(IInvokedMethod arg0, ITestResult arg1);

	public abstract void beforeInvocation(IInvokedMethod arg0, ITestResult arg1);

	public abstract void printTestResults(ITestResult result);

	public abstract void onTestSkipped(ITestResult arg0);

	public abstract void onTestStart(ITestResult arg0);

	public abstract void onTestFailure(ITestResult arg0);

	public abstract void onTestSuccess(ITestResult arg0);
}
