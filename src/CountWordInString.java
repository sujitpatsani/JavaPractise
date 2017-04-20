
public class CountWordInString {
	
	static void countWord(String word){
		
		String str[]= word.trim().split(" ");
		
		System.out.println(str.length);
		
	}

	public static void main(String[] args) {

		
		countWord( " Helo how are you ");
	}

}
