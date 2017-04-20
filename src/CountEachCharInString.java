import java.util.HashMap;
import java.util.Set;


public class CountEachCharInString {
	
	static void charCountEachString(String str){
		
		str=str.replaceAll(" ", "");
		char ch[]=str.toLowerCase().toCharArray();
		
		HashMap<Character,Integer> count=new HashMap<Character,Integer>();
		
		for(char each:ch){
			if(count.containsKey(each)){
				count.put(each,count.get(each)+1);
			}
			else{
				count.put(each,1);
			}
		}
		
		Set<Character> st=count.keySet();
		
		for(char ne:st){
			System.out.println(ne+" Count "+count.get(ne));
		}
	}

	public static void main(String[] args) {

		charCountEachString("Hello how are you");
		
	}

}
