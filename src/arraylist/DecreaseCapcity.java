//How do you decrease the current capacity of an ArrayList to the current size?
package arraylist;

import java.util.ArrayList;

public class DecreaseCapcity {

	public static void main(String[] args) {

		
		ArrayList<String> list=new ArrayList<String>();
		list.ensureCapacity(20);
		list.add("Hello");
		list.add("hai");
		
		list.trimToSize();
	}

}
