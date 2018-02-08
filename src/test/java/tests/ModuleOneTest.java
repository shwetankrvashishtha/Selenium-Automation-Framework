package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functionalLib.TestBase;
import pages.ModuleOnePage;
import utilities.DataProviders;
import utilities.PropertyManager;

public class ModuleOneTest extends DataProviders {

	PropertyManager propertyManager = new PropertyManager();
	TestBase base = new TestBase();

	@BeforeClass
	public void openTest() {
		base.setupBrowser(propertyManager.getResourceBundle.getProperty("BROWSER"),
				propertyManager.getResourceBundle.getProperty("BASE_URL"));
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) {
		base.captureScreenshotOnFailure(result);
	}

	@AfterClass
	public void closeTest() {
		base.teardownTest();
	}

	@Test(description = "Verify Current URL", priority = 0)
	public void TCID_1() {

		new ModuleOnePage(base.getdriver());

		String CURRENTURL = base.getdriver().getCurrentUrl();
		Assert.assertTrue(CURRENTURL.contains(CURRENTURL), "TCID_1 PASSED");
	}

	@Test(description = "Verify alt Attribute", priority = 1, dependsOnMethods = "TCID_1")
	public void TCID_2() {

		ModuleOnePage moduleOnePage = new ModuleOnePage(base.getdriver());

		String altAttribut = moduleOnePage.getGoogleImg().getAttribute("alt");
		Assert.assertTrue(altAttribut.contains("Google"), "TCID_2 PASSED");
	}

	@Test(description = "login credentials verification", priority = 2, dataProvider = "Authentication")
	public void TCID_3(String username, String password) {

		ModuleOnePage moduleOnePage = new ModuleOnePage(base.getdriver());

		moduleOnePage.enterTextToSearch(propertyManager.getResourceBundle.getProperty("TEXT_TO_SEARCH"));
		moduleOnePage.clickGoogleSearchBtn();
		moduleOnePage.clickGmailLink();
		moduleOnePage.enterUsername(username);
		moduleOnePage.clickNextBtn();
		moduleOnePage.enterPassword(password);
		moduleOnePage.clickSignInBtn(username, password);
	}
}