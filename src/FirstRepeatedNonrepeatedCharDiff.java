import java.util.HashMap;

public class FirstRepeatedNonrepeatedCharDiff {

	public static void firstNonRepeatedchar(String str) {
		char charArray[] = str.replaceAll(" ", "").toCharArray();

		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		for (char eachchar : charArray) {
			if (charMap.containsKey(eachchar)) {

				charMap.put(eachchar, charMap.get(eachchar) + 1);
			} else {
				charMap.put(eachchar, 1);
			}

		}

		for (char c : charArray) {

			if (charMap.get(c) == 1) {
				System.out.println("Non repeated char is " + "\t" + c);
				break;
			}

		}
		for (char c1 : charArray) {

			if (charMap.get(c1) > 1) {
				System.out.println("repeated char is " + "\t" + c1);
				break;
			}
		}

	}

	public static void main(String[] args) {
		firstNonRepeatedchar("hay HOw  are you to FF");

	}

}
