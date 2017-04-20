import java.util.HashMap;
import java.util.Set;

//How do you find first repeated and non-repeated character in the given string in java
public class CalculateFirstRepeatedNonRepeatedChar {

	public static void claculateFirstRepetedNonRepetedCharracter(String s) {
		char word[] = s.replaceAll(" ", "").toCharArray();

		HashMap<Character, Integer> wordmap = new HashMap<Character, Integer>();

		for (char chars : word) {

			if (wordmap.containsKey(chars)) {
				wordmap.put(chars, wordmap.get(chars) + 1);
			} else {

				wordmap.put(chars, 1);
			}

		}

		Set<Character> setOfChar = wordmap.keySet();

		for (Character NonRepeatedcharacter : setOfChar) {

			if (wordmap.get(NonRepeatedcharacter) == 1) {
				System.out.println("First Non-Repeated Character Is"
						+ "\t"+NonRepeatedcharacter);
				break;
			}

		}
		for (Character Repeatedcharacter : setOfChar) {

			if (wordmap.get(Repeatedcharacter) > 1) {
				System.out.println("First Repeated Character Is"
						+"\t"+ Repeatedcharacter);
				break;
			}
		}
	}

	public static void main(String[] args) {

		claculateFirstRepetedNonRepetedCharracter("helo how are you doing");

	}

}
