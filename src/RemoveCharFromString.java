
public class RemoveCharFromString {
	
	public static String removeChar(String str, char c){
		if(str==null)
			return "novalue";
		
		
		return str.replaceAll(Character.toString(c), " ");
		
	}

	public static void main(String[] args) {
		
System.out.println(removeChar("LOOK towards java",'o' ));
	}

}
