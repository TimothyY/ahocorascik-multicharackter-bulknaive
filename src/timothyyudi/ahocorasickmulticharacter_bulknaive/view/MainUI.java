package timothyyudi.ahocorasickmulticharacter_bulknaive.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import timothyyudi.ahocorasickmulticharacter_bulknaive.controller.AhoCorasick;
import timothyyudi.ahocorasickmulticharacter_bulknaive.controller.Utility;

public class MainUI {

	public static long preprocessingTimer, processingTimer;
	
	public static void main(String[] args){
	
		Utility util = new Utility();
		File f = null;
		
//		String asset_Home = "src/timothyyudi/ahocorasick/asset/"; //to run anywhere
		String asset_Home = "C:\\Java AhoCorasick\\Asset\\"; //to run externally
		String output_Home = "C:\\Java AhoCorasick\\AhoCorasickMC Results\\";
		String output_PreProcessTimeURI="", output_InProcessTimeURI="", output_ProcessMemoryURI="";

		try {
			switch (args[0]) {
			case "50":
				f = new File(asset_Home+"snortruleskeyword0050.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0050.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0050.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0050.txt";
				break;
			case "100":
				f = new File(asset_Home+"snortruleskeyword0100.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0100.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0100.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0100.txt";
				break;
			case "200":
				f = new File(asset_Home+"snortruleskeyword0200.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0200.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0200.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0200.txt";
				break;
			case "300":
				f = new File(asset_Home+"snortruleskeyword0300.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0300.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0300.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0300.txt";
				break;
			case "400":
				f = new File(asset_Home+"snortruleskeyword0400.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0400.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0400.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0400.txt";
				break;
			case "500":
				f = new File(asset_Home+"snortruleskeyword0500.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0500.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0500.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0500.txt";
				break;
			case "600":
				f = new File(asset_Home+"snortruleskeyword0600.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0600.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0600.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0600.txt";
				break;
			case "700":
				f = new File(asset_Home+"snortruleskeyword0700.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0700.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0700.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0700.txt";
				break;
			case "800":
				f = new File(asset_Home+"snortruleskeyword0800.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0800.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0800.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0800.txt";
				break;
			case "900":
				f = new File(asset_Home+"snortruleskeyword0900.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime0900.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime0900.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory0900.txt";
				break;
			case "1000":
				f = new File(asset_Home+"snortruleskeyword1000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1000.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1000.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1000.txt";
				break;
			case "1100":
				f = new File(asset_Home+"snortruleskeyword1100.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1100.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1100.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1100.txt";
				break;
			case "1200":
				f = new File(asset_Home+"snortruleskeyword1200.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1200.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1200.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1200.txt";
				break;
			case "1300":
				f = new File(asset_Home+"snortruleskeyword1300.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1300.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1300.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1300.txt";
				break;
			case "1400":
				f = new File(asset_Home+"snortruleskeyword1400.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1400.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1400.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1400.txt";
				break;
			case "1500":
				f = new File(asset_Home+"snortruleskeyword1500.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1500.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1500.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1500.txt";
				break;
			case "1600":
				f = new File(asset_Home+"snortruleskeyword1600.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1600.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1600.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1600.txt";
				break;
			case "1700":
				f = new File(asset_Home+"snortruleskeyword1700.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1700.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1700.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1700.txt";
				break;
			case "1800":
				f = new File(asset_Home+"snortruleskeyword1800.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1800.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1800.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1800.txt";
				break;
			case "1900":
				f = new File(asset_Home+"snortruleskeyword1900.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime1900.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime1900.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory1900.txt";
				break;
			case "2000":
				f = new File(asset_Home+"snortruleskeyword2000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2000.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2000.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2000.txt";
				break;
			case "2100":
				f = new File(asset_Home+"snortruleskeyword2100.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2100.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2100.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2100.txt";
				break;
			case "2200":
				f = new File(asset_Home+"snortruleskeyword2200.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2200.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2200.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2200.txt";
				break;
			case "2300":
				f = new File(asset_Home+"snortruleskeyword2300.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2300.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2300.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2300.txt";
				break;
			case "2400":
				f = new File(asset_Home+"snortruleskeyword2400.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2400.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2400.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2400.txt";
				break;
			case "2500":
				f = new File(asset_Home+"snortruleskeyword2500.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2500.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2500.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2500.txt";
				break;
			case "2600":
				f = new File(asset_Home+"snortruleskeyword2600.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2600.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2600.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2600.txt";
				break;
			case "2700":
				f = new File(asset_Home+"snortruleskeyword2700.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2700.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2700.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2700.txt";
				break;
			case "2800":
				f = new File(asset_Home+"snortruleskeyword2800.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2800.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2800.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2800.txt";
				break;
			case "2900":
				f = new File(asset_Home+"snortruleskeyword2900.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime2900.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime2900.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory2900.txt";
				break;
			case "3000":
				f = new File(asset_Home+"snortruleskeyword3000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3000.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3000.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3000.txt";
				break;
			case "3100":
				f = new File(asset_Home+"snortruleskeyword3100.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3100.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3100.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3100.txt";
				break;
			case "3200":
				f = new File(asset_Home+"snortruleskeyword3200.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3200.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3200.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3200.txt";
				break;
			case "3300":
				f = new File(asset_Home+"snortruleskeyword3300.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3300.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3300.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3300.txt";
				break;
			case "3400":
				f = new File(asset_Home+"snortruleskeyword3400.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3400.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3400.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3400.txt";
				break;
			case "3500":
				f = new File(asset_Home+"snortruleskeyword3500.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3500.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3500.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3500.txt";
				break;
			case "3600":
				f = new File(asset_Home+"snortruleskeyword3600.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3600.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3600.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3600.txt";
				break;
			case "3700":
				f = new File(asset_Home+"snortruleskeyword3700.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3700.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3700.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3700.txt";
				break;
			case "3800":
				f = new File(asset_Home+"snortruleskeyword3800.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3800.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3800.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3800.txt";
				break;
			case "3900":
				f = new File(asset_Home+"snortruleskeyword3900.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime3900.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime3900.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory3900.txt";
				break;
			case "4000":
				f = new File(asset_Home+"snortruleskeyword4000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTime4000.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTime4000.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemory4000.txt";
				break;
			case "kjvcustom":
				f = new File(asset_Home+"snortruleskeyword4000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_PreProcessTimekjvcustom.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_InProcessTimekjvcustom.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_ProcessMemorykjvcustom.txt";
				break;
			default:
//				f = new File(asset_Home+"snortrulessimplekeyword.txt");
				f = new File(asset_Home+"snortruleskeyword4000.txt");
				output_PreProcessTimeURI = output_Home+"AhoCorasick_MC_PreProcessTimeSimple.txt";
				output_InProcessTimeURI = output_Home+"AhoCorasick_MC_InProcessTimeSimple.txt";
				output_ProcessMemoryURI = output_Home+"AhoCorasick_MC_ProcessMemorySimple.txt";
				break;
			}
//			File f = new File(asset_Home+"kjvkeyword_simple.txt");
//			File f = new File(asset_Home+"snortrules.txt");
//			File f = new File(asset_Home+"snortruleskeyword.txt");
//			File f = new File(asset_Home+"SimpleDatabase.txt");
			util.readKeyword(f); //load keywords from file
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AhoCorasick ahoCorasick = new AhoCorasick();
		
		preprocessingTimer = System.nanoTime();
		ahoCorasick.preparePrintableASCII();
		ahoCorasick.prepareNaiveAlpha();
		ahoCorasick.prepareGoToFunction(AhoCorasick.fullKeywordMap); //prepare ahocorasick goTo function
		ahoCorasick.prepareFailFromFunction(); //prepare ahocorasick fail function
		ahoCorasick.prepare2Trie();
//		preprocessingTimer = System.currentTimeMillis() - preprocessingTimer;
//		System.out.println("Finish preprocessing "+2+"-multi character trie in "+preprocessingTimer + " millisecond(s)");
		preprocessingTimer = System.nanoTime() - preprocessingTimer;
		System.out.println("Finish preprocessing in "+preprocessingTimer + " nanosecond(s)");
		
		// Get the Java runtime
		Runtime runtime = Runtime.getRuntime();
		// Calculate the used memory
		long preprocessingMemory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory for preprocessing: " + preprocessingMemory+" Bytes");
				
		String inputString="";	//prepare input string
		try {
//			f = new File(asset_Home+"kjv.txt");
//			f = new File(asset_Home+"kjv_bug.txt");			
//			f = new File(asset_Home+"SimpleInputString.txt");
			switch (args[0]) {
			case "kjvcustom":
				f = new File(asset_Home+"kjv_custom.txt");
				break;
			case "simple":
				f = new File(asset_Home+"snortrulesSimpleInputFile.txt");
				break;
			default:
				f = new File(asset_Home+"snortrulesInputFile.txt");
//				f = new File(asset_Home+"hbot.txt");
//				f = new File(asset_Home+"slowdownload.txt");
//				f = new File(asset_Home+"m_orange3.1_100.txt");
				break;
			}
//			inputString = util.readInputString(f, Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(""+2+"-multi character Aho Corasick is READY....BEGIN pattern matching...");
		ahoCorasick.nPatternMatching(f);
		System.out.println("Finish multi-pattern matching in "+processingTimer + " nanosecond(s)");
		
		System.out.println("DONE matching...WRITING results now...");
		
		try {
			util.writeOutput(AhoCorasick.outputList);
		} catch (Exception e) {
			System.out.println("writeOutput Error: "+e);
		}
		
		try {
		    PrintWriter preprocessTimerWriter = new PrintWriter(new BufferedWriter(new FileWriter(output_PreProcessTimeURI, true)));
		    preprocessTimerWriter.println(""+preprocessingTimer);
		    preprocessTimerWriter.close();
		    PrintWriter preprocessMemoryWriter = new PrintWriter(new BufferedWriter(new FileWriter(output_ProcessMemoryURI, true)));
		    preprocessMemoryWriter.println(""+preprocessingMemory);
		    preprocessMemoryWriter.close();
			PrintWriter processTimerWriter = new PrintWriter(new BufferedWriter(new FileWriter(output_InProcessTimeURI, true)));
			processTimerWriter.println(""+processingTimer);
		    processTimerWriter.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		System.out.println("COMPLETED");
//		ahoCorasick.trieInsight("a");
		runtime.gc();
	}
	
}
