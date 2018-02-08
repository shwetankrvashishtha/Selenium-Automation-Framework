package locators;

public interface WebLocators {

	public interface moduleOneLocators {
		String GOOGLE_IMG = "//*[@id='hplogo']";
		String GOOGLE_SEARCH_BAR = "//*[@id='lst-ib']";
		String GOOGLE_SEARCH_BTN = "//center/input[@value='Google Search']";
		String GMAIL_LINK = "//a[@href='https://www.google.com/gmail/']";
		String GMAIL_LOADER = "//*[@id='loading']";
		String GMAIL_USERNAME = "//*[@id='identifierId']";
		String NEXT_BTN = "//*[@id='identifierNext']/content/span";
		String GMAIL_PASSWORD = "//*[@id='password']//input";
		String SIGN_IN_BTN = "//*[@id='passwordNext']/div[2]";
		String INCORRECT_PASSWORD = "//*[@id='password']//div[@class='LXRPh']";
	}
}