//How To Find Percentage Of Uppercase Letters, Lowercase Letters, Digits And Special Characters In String?

public class CalculatePercentageOfChar {

	static void calculatePersentage(String s) {

		char ch[] = s.toCharArray();
		int length = ch.length;
		int Uppercaseletter = 0;
		int loweraseletter = 0;
		int digit = 0;

		for (int i = 0; i < length; i++) {

			if (Character.isUpperCase(ch[i])) {
				Uppercaseletter++;

			} else if (Character.isLowerCase(ch[i])) {
				loweraseletter++;
			} else if (Character.isDigit(ch[i])) {

				digit++;
			}

		}
		System.out.println((Uppercaseletter*100 / length));
		System.out.println((loweraseletter*100.00 / length));
		System.out.println((digit*100.00 / length));
	}

	public static void main(String[] args) {
		
		calculatePersentage("hello i saW 200 apples in a tree");

	}

}
