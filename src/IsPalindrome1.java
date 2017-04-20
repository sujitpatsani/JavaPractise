
public class IsPalindrome1 {
	
	
	public static boolean isPalindrome(String inputString){
		
		int length=inputString.length();
		
		if(inputString==null)
			
			return false;
		
		else {
			
			for(int i=0;i<length/2;i++){
				
				if(inputString.charAt(i)!=inputString.charAt(length-i-1)){
					
					return false;
					
				}
			}
			
			
			return true;
		}
		
	}

	public static void main(String[] args) {
		
		System.out.println(isPalindrome("sas"));

	}

}
