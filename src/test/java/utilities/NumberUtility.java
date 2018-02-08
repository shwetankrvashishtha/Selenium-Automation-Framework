package utilities;

public class NumberUtility {

	public static long generateRandomNumber() {
		long randomNumber = Math.round(Math.random() * 1000);
		return randomNumber;
	}
}
