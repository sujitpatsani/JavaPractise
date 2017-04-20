import java.util.HashMap;
import java.util.Set;


public class DuplicateChar {
	
	static void duplicateChar(String str)
	{
		str=str.replaceAll(" ","" );
		char ch[]=str.toCharArray();
		HashMap<Character,Integer> hs= new HashMap<Character,Integer>();
			
		for(char csr:ch)
		{
			if (hs.containsKey(csr)){
				hs.put(csr,hs.get(csr)+1);
			}
			else{
				hs.put(csr,1);
			}
		}
		
		Set<Character> st=hs.keySet();
		for (char count:st){
			if(hs.get(count)>1){
				System.out.println(count);
			}
		}
	}

	public static void main(String[] args) {

		duplicateChar("Hello how are you"); 
		
	}

}
