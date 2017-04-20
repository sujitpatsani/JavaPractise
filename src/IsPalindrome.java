
public class IsPalindrome {
	
	public static boolean isPalindrome(String inputString){
		if(inputString== null)
		{
			return false;
		}
		else 
		{
			StringBuilder strbuilder = new StringBuilder(inputString);
			
			strbuilder.reverse();
			return strbuilder.toString().equals(inputString);
		}
		
		
	}

	public static void main(String[] args) {
		
		System.out.println(isPalindrome("sujit"));

	}

}
