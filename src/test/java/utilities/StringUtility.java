package utilities;

public class StringUtility {

	public static String generateRandomString() {
		final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			long number = NumberUtility.generateRandomNumber();
			char ch = CHAR_LIST.charAt((int) number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static String generateAlphaNumericString(int count) {
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
