//How do you get the position of a particular element in an ArrayList
package arraylist;

import java.util.ArrayList;

public class GetPositionOfAnElement {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(57);
		list.add(76);

		int a = list.indexOf(76);
		System.out.println(a);
	}

}
