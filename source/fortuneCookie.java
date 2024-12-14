/*	Author: Michael Melendez
	Date: 12.03.10
	Descrip: Fortune Cookie Application
*/


import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.*;
import java.util.regex.*;


public class fortuneCookie {

	Random generator;
	int randomInt;
	int lineNum;
	StringBuffer sb;
	String theFortuneInfo;
	String theFortuneNums;

	public fortuneCookie () throws Exception {
	
		// generate a random integer
		
		generator = new Random();
		randomInt = generator.nextInt(12);
		
		if (randomInt == 0 ) {
		
			lineNum = (randomInt + 1);
		
		}
		else {
		
			lineNum = randomInt;
		
		}
		
		// open file and get line that matches the random integer
		
		InputStream is = getClass().getResourceAsStream("data/fortunelist.csv");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		sb = new StringBuffer();
		String line;

		int count = 1;
		while ((line = br.readLine()) != null)   {	
			
			if (count == lineNum) {
			
			sb.append(line);
			
			}
		
			count++;
		
		}
		
		br.close();
		isr.close();
		is.close();
		
		// parse the line using regex and split to retrieve the fortune and lucky numbers
		
		Pattern pat = Pattern.compile(",");
		String theFortuneLine[] = pat.split(sb);
		
		theFortuneInfo = theFortuneLine[0];
		theFortuneNums = theFortuneLine[1];
		

	} // end fortune function

	public int getMyFortuneIndex() {
	
		return lineNum;
	
	}
	
	public String getMyFortune() {
	
		return theFortuneInfo;
	
	}// end getData function
	
	
	public String getMyNumbers() {
	
		return theFortuneNums;
	
	}// end getNumbers function

}// end class fortune