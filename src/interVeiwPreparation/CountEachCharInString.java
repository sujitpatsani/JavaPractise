package interVeiwPreparation;

import java.util.HashMap;
import java.util.Set;

public class CountEachCharInString {

	public static void countEachCharIn(String str) {

		char ch[] = str.replaceAll(" ","").toCharArray();
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();

		for (char any : ch) {
			if (charMap.containsKey(any)) {

				charMap.put(any, charMap.get(any) + 1);
			} else {
				charMap.put(any, 1);

			}

		}
		
		Set<Character> set=charMap.keySet();
		for (Character character : set) {
		System.out.println(character+"character::"+charMap.get(character));
			
		}

	}

	public static void main(String[] args) {
		CountEachCharInString.countEachCharIn("HELLO HAY HOOO");
	}

}
