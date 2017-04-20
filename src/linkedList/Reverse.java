package linkedList;

import java.util.Collections;
import java.util.LinkedList;

public class Reverse {

	public static void main(String[] args) {

		LinkedList<String> li= new LinkedList<String>();
		
		li.add("Hello");
		li.add("java");
		li.add("ajax");
		li.add("system");
		li.add("game");
		//Reverse the collection
		Collections.reverse(li);
		for(String rev:li){
			System.out.println(rev);
		}
	//shuffle the collection	
		Collections.shuffle(li);
		for (String string : li) {
			System.out.println(string);
		}
		
	//Swap two elements
		Collections.swap(li, 3, 4);
		for (String string1 : li) {
			System.out.println(string1);
			
		}
	}

}
