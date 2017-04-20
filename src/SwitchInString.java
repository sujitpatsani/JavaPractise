
public class SwitchInString {
	
	public static void recogniseColourIf(String colour){
		
		
		if(colour.equalsIgnoreCase("blue")){
			System.out.println("BLUE");
		}
		else if (colour.equalsIgnoreCase("red")) {
			System.out.println("RED");
		}
		else
			System.out.println("INVALID");
		
	}
	
	public static void recogniseColourSwitch(String colour){
		
		switch(colour)
		{
		case "blue":
			System.out.println("BLUE");
			break;
		case "red":
			System.out.println("RED");
			break;
		default :
			System.out.println("INVALID");
			break;
		}
	
	}
		
	

	public static void main(String[] args) {
		recogniseColourIf("blue");
		recogniseColourSwitch("green");

	}

}
