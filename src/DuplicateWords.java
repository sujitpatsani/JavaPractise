import java.util.HashMap;
import java.util.Set;


public class DuplicateWords {
	
	static void duplicate(String str){
		String words [] =str.split(" ");
		
		HashMap<String ,Integer> wordcount= new HashMap<String ,Integer>();
		
		for(String word: words){
			
			if(wordcount.containsKey(word.toLowerCase()))
			{
				wordcount.put(word.toLowerCase(),wordcount.get(word.toLowerCase())+1);
			}
			else{
				wordcount.put(word.toLowerCase(), 1);
			}
		}
		
		Set<String> keys= wordcount.keySet();
		
		for (String st:keys){
			if(wordcount.get(st)>1){
				System.out.println(st+" count "+(wordcount.get(st)));
			}
		}
	}

	public static void main(String[] args) {
		
		duplicate("Helo helo are u ready to talk are u");

	}

}
