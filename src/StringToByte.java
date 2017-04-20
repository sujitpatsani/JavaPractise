import java.util.Arrays;


public class StringToByte {
	
	
	public static void convertion(String str){
		
		byte a[]= str.getBytes();
		System.out.println(Arrays.toString(a));
		
		String s= new String(a);
		System.out.println(s);
		
	}

	public static void main(String[] args) {

		convertion("Hay how are you");
	}

}
