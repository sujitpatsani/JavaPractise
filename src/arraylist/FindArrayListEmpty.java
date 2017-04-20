// How do you find out whether the given ArrayList is empty or not
package arraylist;

import java.util.ArrayList;

public class FindArrayListEmpty {

	public static void main(String[] args) {
  
		ArrayList<String> listString= new ArrayList<String>();
		listString.add("hello");
		System.out.println(listString.isEmpty());
		ArrayList<Integer> listInteger= new ArrayList<Integer>();
		System.out.println(listInteger.isEmpty());
	}

}
