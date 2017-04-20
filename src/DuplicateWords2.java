import java.util.HashMap;
import java.util.Set;


public class DuplicateWords2 {
	
	
	public static void findDuplicateWords(String line ){
		
		String words[]=line.trim().split(" ");

		HashMap<String,Integer> wordCache=new HashMap<String,Integer>();
		
		for (String string : words) {
			if(wordCache.containsKey(string)){
				wordCache.put(string, wordCache.get(string)+1);
			}else
				wordCache.put(string, 1);
			
		}
		
		Set<String> keySet=wordCache.keySet();
		
		for(String st:keySet){
			if(wordCache.get(st)>1){
				System.out.println(st);
			}
		}
}

	public static void main(String[] args) {

		findDuplicateWords("Hello how are you and how is he");
	}

}
