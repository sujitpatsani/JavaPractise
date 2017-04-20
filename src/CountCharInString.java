
public class CountCharInString {
	
	static void  countChar(String str){
		
		int a=str.length()-str.replaceAll("o","").length();
		System.out.println(a);
	}

	public static void main(String[] args) {
		
		countChar("Heloo how are you");

	}

}
