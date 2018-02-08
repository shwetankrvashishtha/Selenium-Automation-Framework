package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import functionalLib.PageBase;
import functionalLib.TestBase;
import locators.WebLocators.moduleOneLocators;

public class ModuleOnePage extends PageBase implements moduleOneLocators {

	public ModuleOnePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	TestBase base = new TestBase();

	@FindBy(xpath = GOOGLE_IMG)
	private WebElement googleImg;

	public WebElement getGoogleImg() {
		return googleImg;
	}

	@FindBy(xpath = GOOGLE_SEARCH_BAR)
	private WebElement googleSearchBar;

	public WebElement getGoogleSearchBar() {
		return googleSearchBar;
	}

	@FindBy(xpath = GOOGLE_SEARCH_BTN)
	private WebElement googleSearchBtn;

	public WebElement getGoogleSearchBtn() {
		return googleSearchBtn;
	}

	@FindBy(xpath = GMAIL_LINK)
	private WebElement gmailLink;

	public WebElement getGmailLink() {
		return gmailLink;
	}

	@FindBy(xpath = GMAIL_LOADER)
	private WebElement gmailLoader;

	public WebElement getGmailLoader() {
		return gmailLoader;
	}

	@FindBy(xpath = GMAIL_USERNAME)
	private WebElement gmailUsername;

	public WebElement getGmailUsername() {
		return gmailUsername;
	}

	@FindBy(xpath = GMAIL_PASSWORD)
	private WebElement gmailPassword;

	public WebElement getGmailPassword() {
		return gmailPassword;
	}

	@FindBy(xpath = NEXT_BTN)
	private WebElement nextBtn;

	public WebElement getNextBtn() {
		return nextBtn;
	}

	@FindBy(xpath = SIGN_IN_BTN)
	private WebElement signInBtn;

	public WebElement getSignInBtn() {
		return signInBtn;
	}

	@FindBy(xpath = INCORRECT_PASSWORD)
	private WebElement incorrectPassword;

	public WebElement getIncorrectPassword() {
		return incorrectPassword;
	}

	public void enterTextToSearch(String text) {
		base.waitForElementVisible(10, getGoogleSearchBar());
		getGoogleSearchBar().clear();
		getGoogleSearchBar().sendKeys(text);
	}

	public void clickGoogleSearchBtn() {
		base.waitForElementVisible(10, getGoogleSearchBtn());
		getGoogleSearchBtn().click();
	}

	public void clickGmailLink() {
		base.waitForElementVisible(10, getGmailLink());
		getGmailLink().click();
		base.waitForPageLoad(10);
	}

	public void enterUsername(String username) {
		base.waitForElementVisible(10, getGmailUsername());
		getGmailUsername().clear();
		getGmailUsername().sendKeys(username);
	}

	public void clickNextBtn() {
		base.waitForElementToBeClickable(10, getNextBtn());
		getNextBtn().click();
	}

	public void enterPassword(String password) {
		base.waitForElementVisible(10, getGmailPassword());
		getGmailPassword().clear();
		getGmailPassword().sendKeys(password);
	}

	public void clickSignInBtn(String username, String password) {
		base.waitForElementVisible(10, getSignInBtn());
		((JavascriptExecutor) base.getdriver()).executeScript("arguments[0].click();", getSignInBtn());
		base.pause(5000);
		if (getIncorrectPassword().isDisplayed()) {
			System.out.println("Incorrect password observed. User signin blocked.");
			base.getdriver().navigate().back();
			enterUsername(username);
			clickNextBtn();
			base.pause(4000);
			String fwgpwd = base.getdriver().findElement(By.xpath("//*[@id='forgotPassword']")).getText();
			System.out.println(fwgpwd);
			enterPassword(password);
		}
	}
}
