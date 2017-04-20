//How do you check whether the given element is present in an ArrayList or not
package arraylist;

import java.util.ArrayList;

public class FindElementisPresentInArrayList {

	public static void main(String[] args) {

		
		ArrayList<Integer> ar= new ArrayList<Integer>();
		ar.add(45);
		ar.add(67);
		
		System.out.println(ar.contains(45));
	}

}
