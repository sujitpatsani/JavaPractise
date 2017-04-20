
public class RemoveWhiteSpace {
	
	static void removeSpace(String words){
		char ch []=words.toCharArray();
		StringBuffer sb=new StringBuffer();
		
		for (int i = 0; i < ch.length; i++) {
			
			if(ch[i]!=' '){
				sb=sb.append(ch[i]);
			}
			
			
			
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		removeSpace("hello here you");

	}

}
