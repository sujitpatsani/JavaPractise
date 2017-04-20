import java.util.HashMap;
import java.util.Set;

public class CountOccurenceCharInString {

	static void countOccurence(String word) {

		word = word.replaceAll(" ", "").toLowerCase();

		char each[] = word.toCharArray();

		HashMap<Character, Integer> words = new HashMap<Character, Integer>();

		for (char c : each) {
			if (words.containsKey(c)) {
				words.put(c, words.get(c) + 1);
			} else {
				words.put(c, 1);
			}
		}

		Set<Character> st = words.keySet();

		for (Character character : st) {

			if (words.get(character) > 1) {

				System.out.println(character + " " + words.get(character));
			}
		}

	}

	public static void main(String[] args) {
		countOccurence("Helo how are you");
	}

}
