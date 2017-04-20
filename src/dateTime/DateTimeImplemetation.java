package dateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeImplemetation {
	
	

	public static void main(String[] args) {
		
		Date date = new Date(); 
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String datestr=format.format(date);
		
		System.out.println(datestr);
		
		SimpleDateFormat format1= new SimpleDateFormat("dd-MMM-yyyy z E, HH mm ss");
String datestr1=format1.format(date);
		
		System.out.println(datestr1);
		
		
		try {
			Date date1=format.parse("20-12-2016");
			
			System.out.println(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
