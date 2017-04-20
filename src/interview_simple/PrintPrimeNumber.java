package interview_simple;

public class PrintPrimeNumber {
	
	
	public static boolean printPrimeNumber(int num){
		
		if(num<2 && num%2==0){
			System.out.println(num+" not a prime number");
			return false;
			
		}
		
		int top=(int)Math.sqrt(num)+1;
		for (int i = 3; i < top; i++) {

			if(num%i==0){
				
				System.out.println(num+" not a prime number");
				return false;
			}
		}
		
		System.out.println(num+" is prime number");
		return true;
	}

	public static void main(String[] args) {
		
		printPrimeNumber(207);

	}

}
