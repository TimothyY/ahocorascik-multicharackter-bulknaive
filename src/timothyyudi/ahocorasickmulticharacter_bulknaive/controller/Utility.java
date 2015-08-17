package timothyyudi.ahocorasickmulticharacter_bulknaive.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import timothyyudi.ahocorasickmulticharacter_bulknaive.model.Output;
import timothyyudi.ahocorasickmulticharacter_bulknaive.view.MainUI;

public class Utility {

	/**read keyword file from snort rules*/
	public void readKeyword(File f){
		Scanner scanner;
		String temp;
		int firstQuotes=0, secondQuotes=0, thirdQuotes=0, fourthQuotes=0;
		try {
			scanner = new Scanner(f);
			
			while (scanner.hasNextLine()){
				temp = scanner.nextLine().trim();
//				System.out.println("inserting: "+temp);
				AhoCorasick.fullKeywordMap.put(temp.hashCode(), temp); //ambil per spasi.
//			while (scanner.hasNextLine()){
//			    temp = scanner.nextLine();
//			    firstQuotes=temp.indexOf("\"")+1;
////			    secondQuotes=temp.indexOf("\"", firstQuotes); //to be used on snort rules message [TEMP]
//			    secondQuotes=temp.indexOf("\"", firstQuotes)+1; //to be used in snort rules content
//			    thirdQuotes=temp.indexOf("\"", secondQuotes)+1;
//			    fourthQuotes=temp.indexOf("\"", thirdQuotes);
//			    if(firstQuotes!=0){
////			    	list.add(temp.substring(firstQuotes, secondQuotes).trim()); //to be used on snort rules message [TEMP]
//			    	list.add(temp.substring(thirdQuotes, fourthQuotes).trim()); //to be used in snort rules content
//			    }
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**Read input string file as inputString*/
	public String readInputString(File f, Charset encoding) throws IOException {
//		byte[] encoded = Files.readAllBytes(Paths.get(f));
		RandomAccessFile rf = new RandomAccessFile(f, "r");
		byte[] encoded = new byte[(int)rf.length()];
		rf.read(encoded);
		rf.close();
		return new String(encoded, encoding);
	}
	
	/**Write output to output.txt
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException */
	public void writeOutput(ArrayList<Output> outputList) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("src/timothyyudi/ahocorasick/asset/AhoCorasickOutput.txt", "UTF-8");
		for (Output output : outputList) {
			writer.println("Found "+output.getOutputString()+" @line: "+output.getLineNumber()+"("+output.getOutputStartPoint()+"-"+output.getOutputEndPoint()+")");
		}
		writer.close();
	}
	
	public static void writeAhoCorasickTime(long ahoCorasickTime){
		MainUI.processingTimer = ahoCorasickTime;
	}
	
}
