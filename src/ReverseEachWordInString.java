
public class ReverseEachWordInString {
	static String  reverse="";
	
	public static void   reaverseEachWordInString(String line)
	{
		String words[]=line.split(" ");
		for(int i=0;i<=words.length-1;i++){
			
			String word= words[i];
			
			StringBuffer buffer = new StringBuffer(words[i]);
			 
			
			reverse=reverse+buffer.reverse().toString()+" ";
				
				
			}
		System.out.println(reverse);
			
		}

	public static void main(String[] args) {
		
		reaverseEachWordInString("Java to jaaan");
		
		

	}

}
