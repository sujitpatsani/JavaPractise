//How do you find the number of elements present in an ArrayList
package arraylist;

import java.util.ArrayList;

public class FindNumberOfElementInArrayList {

	public static void main(String[] args) {

		
		ArrayList<String> list= new ArrayList<String>();
		list.add("hello");
		list.add("hai");
		list.add("haire");
		list.trimToSize();
		int a=list.size();
		System.out.println(a);
	}

}
