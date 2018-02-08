package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Authentication")

	public static Object[][] credentials() {

		return new Object[][] { { "TestAccount123", "Test@123" }, { "qafiction", "Gl0b@l$t3p@123$" } };

	}
}
