
public class StringToChar {
	
	
	public static void convertion(String str){
		
		char a[]=str.toCharArray();
		
		System.out.println(a[0]);
		
		String s=new String(a);
		System.out.println(a);
		
	}

	public static void main(String[] args) {
		convertion("hello");

	}

}
