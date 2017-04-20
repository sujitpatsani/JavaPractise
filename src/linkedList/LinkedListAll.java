package linkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListAll {

	public static void main(String[] args) {

		LinkedList<Integer> li = new LinkedList<Integer>();
		li.add(10);
		li.add(20);
		li.add(30);
		li.add(40);
		li.add(50);
		Iterator<Integer> itr = li.iterator();

		while (itr.hasNext()) {

			System.out.println(itr.next());
				
		}
	
	
		System.out.println(li.size());
		System.out.println(li.isEmpty());
		System.out.println(li.indexOf(30));
		System.out.println(li.contains(40));
		/*li.clear();
		System.out.println(li);*/
		System.out.println(li.subList(0, 3));
		li.add(4, 60);
		li.addAll(li);
		System.out.println(li);
		li.addAll(2,li);
		System.out.println(li);
		Object a[]=li.toArray();
		for (Object object : a) {
			
			System.out.println(object);
			
		}
		
		li.remove(5);
	/*	li.removeAll(li);
		System.out.println(li);*/
		
		li.set(3, 200);
		li.get(2);
		System.out.println(li.toString());
		
		LinkedList<Integer> copy=(LinkedList<Integer>)li.clone();
		System.out.println(copy);
		
		
	
	}
	

}
