
public class ReplaceAllWhiteSpace {
	
	static void replace(String str){
		
		char c[]=str.toCharArray();
		StringBuffer sb=new StringBuffer();
		
		for (int i = 0; i < c.length; i++) {
			
			if((c[i]!=' ')&&(c[i]!='\t')){
				
				 sb.append(c[i]);
				
			}
			
		}
		System.out.println(sb);
		
	}

	public static void main(String[] args) {
		replace("hello hay are you ok");

	}

}
