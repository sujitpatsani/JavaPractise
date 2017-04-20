public class StringReverse {

	String reverstring = "";

	void reverse(String rev) {

		String str[] = rev.split(" ");
		System.out.println(str.length);

		for (int j = 0; j < str.length; j++) {

			String word = str[j];
			String reverse = "";

			for (int i = word.length() - 1; i >= 0; i--) {

				reverse = reverse + word.charAt(i);

			}
			reverstring = reverstring + reverse + " ";

		}

		System.out.println(reverstring);

	}

	public static void main(String[] args) {

		StringReverse sbgh = new StringReverse();

		sbgh.reverse("Hello java hello");

	}

}
