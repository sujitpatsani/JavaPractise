package arraylist;

import java.util.ArrayList;

public class ConvertArrayListToArray {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(56);
		list.add(76);
		list.add(98);
		
		Object o[]=list.toArray();
		
		for(Object or:o){
			System.out.println(or);
		}
		
	}

}
