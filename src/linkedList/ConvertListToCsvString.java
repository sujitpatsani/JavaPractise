package linkedList;

import java.util.LinkedList;

public class ConvertListToCsvString {

	public static String conVertToStringCsv(LinkedList<String> list) {

		StringBuilder sb = new StringBuilder();

		for (String string : list) {

			if (sb.length() != 0) {
				sb.append(',');
			}
			sb = sb.append(string);

		}

		return sb.toString();

	}

	public static void main(String[] args) {

		LinkedList<String> li = new LinkedList<String>();

		li.add("Hello");
		li.add("java");
		li.add("ajax");
		li.add("system");
		li.add("game");
		
		String print=conVertToStringCsv(li);
		System.out.println(print);

	}

}
