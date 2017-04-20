//How do you retrieve an element from a particular position of an ArrayList
package arraylist;

import java.util.ArrayList;
import java.util.List;

public class GetElementFromArryList {

	public static void main(String[] args) {

		ArrayList<Integer> list= new ArrayList<Integer>();
		list.add(67);
		list.add(87);
		list.add(4);
		list.add(46);

		ArrayList<Integer> list1= new ArrayList<Integer>();
		list1.add(67);
		list1.add(87);
		list1.add(4);
		list1.add(46);
		
		System.out.println(list.get(3));
		
		list.set(3, 78);
		System.out.println(list.get(3));
		
		list.add(4, 79);

		System.out.println(list);
		
		
		//How do you retrieve a portion of an ArrayList
		List<Integer> ar=list.subList(0,3);
		System.out.println(ar+"hhhh");
		
		list.remove(4);
		System.out.println(list);
		try{
		list.remove(87);
		System.out.println(list);
		}
		catch(Exception e){
			
		}
		
		list.addAll(list1);
		System.out.println(list);
		list.clear();
		System.out.println(list);
		
		list.addAll(0, list1);
		System.out.println(list);
		
		
		
		
	}

}
