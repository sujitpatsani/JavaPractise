package interview;

public class ArrayException {
	
	public static void show(){
		
		int a []={1,2,3};
		
		try{
			int div = a[1]/0;
		}catch(Exception e){
			e.printStackTrace();
		}
			
	/*	catch(ArithmeticException ae){
			
		}*/
	}

	public static void main(String[] args) {
		
		show();

	}

}
