package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListValueCount {
	
	static void listValueCount(List<String> list){
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		
		Set<String> unique=new HashSet<String>(list);
		
		for(String count:unique){
			
			int countValue=Collections.frequency(list, count);
			System.out.println(count+" "+countValue);
			
			
		}
		
		
	}

	public static void main(String[] args) {
		
		listValueCount(new ArrayList<String>());

	}

}
