import java.util.HashMap;
import java.util.Set;

public class CalculateFirstRepeatedNonrepeatedChar2 {

	public static void firstRepeatedNonRepeatedChar(String charstring) {

		char word[] = charstring.replaceAll(" ", "").toCharArray();
		HashMap<Character, Integer> wordMap = new HashMap<Character, Integer>();

		for (char chars : word) {

			if (wordMap.containsKey(chars)) {
				wordMap.put(chars, wordMap.get(chars) + 1);
			} else
				wordMap.put(chars, 1);

		}
		
		Set <Character> keySet=wordMap.keySet();
		
		for (Character eachcharacter : keySet) {
			if(wordMap.get(eachcharacter)==1){
				System.out.println(eachcharacter+"first non repeated char");
				break;
				
			}
			
		}

		for (Character eachcharacter : keySet) {
			if(wordMap.get(eachcharacter)>1){
				System.out.println(eachcharacter+"first repeated char");
				break;
				
			}
			
		}
		
		
	}

	public static void main(String[] args) {
		
		firstRepeatedNonRepeatedChar("z Hello how are you");

	}

}
