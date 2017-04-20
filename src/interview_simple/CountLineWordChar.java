package interview_simple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountLineWordChar {
	
	static int wordcount=0;
	static int charcount=0;
	
	public static void countLineWordChar() throws IOException{
		
		BufferedReader br= new BufferedReader(new FileReader("C:\\sample.txt"));
		String line=br.readLine();
		int count=0;
		while(line!= null){
			count++;
			
			String word[]=line.trim().split(" ");
			wordcount=wordcount+word.length;
			
			for (String string : word) {
				
				char [] ch= string.toCharArray();
				for (char c : ch) {
					charcount++;
					
					
				}
			}
			
			
			
			
		}
		line = br.readLine();
		
	}

	public static void main(String[] args) {

	}

}
