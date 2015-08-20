package timothyyudi.ahocorasickmulticharacter_bulknaive.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import timothyyudi.ahocorasickmulticharacter_bulknaive.model.Output;
import timothyyudi.ahocorasickmulticharacter_bulknaive.model.State;

public class AhoCorasick {

	State root;
	State currState;
	int keywordInsertionCounter, lineNumberCounter, columnNumberCounter;
	
	public static HashMap<Integer, String> fullKeywordMap = new HashMap<>();
	public static ArrayList<Output> outputList = new ArrayList<Output>();
	public static ArrayList<String> printableASCIIList = new ArrayList<>();
	
	long ahoCorasickTimeTotal;
	long ahoCorasickTimeFragment;
	long algoStart, algoEnd;
	
//	String inputString
	String bufferStr0, bufferStr1;
	
	public AhoCorasick(){
		root= new State();
	}
	
	public void preparePrintableASCII() {
		String tempPrintableASCII = "";
		printableASCIIList.add(tempPrintableASCII);
		tempPrintableASCII = "\n";
		printableASCIIList.add(tempPrintableASCII);
		tempPrintableASCII = "\r";
		printableASCIIList.add(tempPrintableASCII);
		
		for(int i=32;i<127;i++){ //printable ascii
			tempPrintableASCII = Character.toString((char)i);
			printableASCIIList.add(tempPrintableASCII);
		}
//		System.out.println(printableASCIIList.size());
	}
	
//	public void prepareNaiveAlpha(){
//		root.setNextStateCollection(new HashMap<String,State>());
//		for (String printableASCII : printableASCIIList) {
//			root.getNextStateCollection().put(printableASCII, new State(printableASCII, root));
//		}
////		System.out.println("rootchild size: "+root.getNextStateCollection().size());
//	}
	
	/**A function to move from 1 node of a trie to the others based on next input character*/
	private State goTo(State node, String nextInputChar){
		try {
			return node.getNextStateCollection().get(nextInputChar);
		} catch (Exception e) {
			return null;
		}
	}
		
	/**Prepare AhoCorasick goto function/ successful state of AhoCorasick trie*/
	public void prepareGoToFunction(HashMap<Integer, String> keywords){
		for (Integer hashCodeKey : keywords.keySet()) {
			enterKeyword(keywords.get(hashCodeKey));
		}
	}
	
	/**insert keywords to trie*/
	private void enterKeyword(String keyword){
		ArrayList<State> currState0 = new ArrayList<State>();
		ArrayList<State> nextCurrState0 = new ArrayList<State>();
		currState0.add(root);
		State currState1 = root;
		String buffer0 = "", buffer1=""; //buffer0 untuk naive.
		
		for (int keywordInsertionCounter = 0; keywordInsertionCounter < keyword.length(); keywordInsertionCounter++) {
			if(keywordInsertionCounter==0){
				State currState0Item = currState0.get(0);
				//clear
				for (String printableASCII : printableASCIIList) {
					String tempConcatenatedBuffer = printableASCII+keyword.charAt(0);
					if(goTo(currState0Item, tempConcatenatedBuffer)==null){
						if(currState0Item.getNextStateCollection()==null)currState0Item.setNextStateCollection(new HashMap<String,State>());
						currState0Item.getNextStateCollection().put(tempConcatenatedBuffer, new State(tempConcatenatedBuffer, root));
					}
					nextCurrState0.add(goTo(currState0Item, tempConcatenatedBuffer));
				}
				currState0.clear();
				currState0.addAll(nextCurrState0);
				nextCurrState0.clear();
				buffer0=""; //make sure it's empty.
				
				buffer1+=Character.toString(keyword.charAt(keywordInsertionCounter));
				
			}else if(keywordInsertionCounter==keyword.length()-1){
				
				if(buffer0.length()==2)buffer0="";
				if(buffer1.length()==2)buffer1="";
				
				buffer0+=Character.toString(keyword.charAt(keywordInsertionCounter));
				buffer1+=Character.toString(keyword.charAt(keywordInsertionCounter));
				
				if(buffer1.length()==1){
					//input concatenated with printable ascci
					for (String printableASCII : printableASCIIList) {
						String tempConcatenatedBuffer = buffer1+printableASCII;
						if(goTo(currState1, tempConcatenatedBuffer)==null){
							if(currState1.getNextStateCollection()==null)currState1.setNextStateCollection(new HashMap<String,State>());
							currState1.getNextStateCollection().put(tempConcatenatedBuffer, new State(tempConcatenatedBuffer, root));
						}
						if(goTo(currState1, tempConcatenatedBuffer).getFullKeywordHashCodeList()==null)currState1.setFullKeywordHashCodeList(new ArrayList<Integer>());
						currState1.getFullKeywordHashCodeList().add(keyword.hashCode());
					}
				}else if(buffer1.length()==2){
					if(goTo(currState1, buffer1)==null){
						if(currState1.getNextStateCollection()==null)currState1.setNextStateCollection(new HashMap<String,State>());
						currState1.getNextStateCollection().put(buffer1, new State(buffer1, root));
					}
					currState1 = goTo(currState1, buffer1);
					if(currState1.getFullKeywordHashCodeList()==null){
						currState1.setFullKeywordHashCodeList(new ArrayList<Integer>());
					};
					currState1.getFullKeywordHashCodeList().add(keyword.hashCode());
				}
				
				if(buffer0.length()==1){
					//input concatenated with printable ascci
					Iterator<State> i = currState0.iterator();
					while(i.hasNext()){
						State currState0Item = i.next();
						for (String printableASCII : printableASCIIList) {
							String tempConcatenatedBuffer = buffer0+printableASCII;
							if(goTo(currState0Item, tempConcatenatedBuffer)==null){
								if(currState0Item.getNextStateCollection()==null)currState0Item.setNextStateCollection(new HashMap<String,State>());
								currState0Item.getNextStateCollection().put(tempConcatenatedBuffer, new State(tempConcatenatedBuffer, root));
							}
							if(goTo(currState0Item, tempConcatenatedBuffer).getFullKeywordHashCodeList()==null)currState0Item.setFullKeywordHashCodeList(new ArrayList<Integer>());
							currState0Item.getFullKeywordHashCodeList().add(keyword.hashCode());
						}
						i.remove();
					}
				}else if(buffer0.length()==2){
					Iterator<State> i = currState0.iterator();
					while(i.hasNext()){
						State currState0Item = i.next();
						if(goTo(currState0Item, buffer0)==null){
							if(currState0Item.getNextStateCollection()==null)currState0Item.setNextStateCollection(new HashMap<String,State>());
							currState0Item.getNextStateCollection().put(buffer0, new State(buffer0, root));
						}
						if(goTo(currState0Item, buffer0).getFullKeywordHashCodeList()==null)goTo(currState0Item, buffer0).setFullKeywordHashCodeList(new ArrayList<Integer>());
						goTo(currState0Item, buffer0).getFullKeywordHashCodeList().add(keyword.hashCode());
						//nextCurrState0.add(goTo(currState0Item, buffer0)); //not used anymore
						i.remove();
					}
					//currState0.addAll(nextCurrState0); //not used anymore
					//nextCurrState0.clear(); //not used anymore
				}
				
			}else{	
			
				if(buffer0.length()==2)buffer0="";
				if(buffer1.length()==2)buffer1="";
				
				buffer0+=Character.toString(keyword.charAt(keywordInsertionCounter));
				buffer1+=Character.toString(keyword.charAt(keywordInsertionCounter));
				
				if(buffer0.length()==2){
					
					Iterator<State> i = currState0.iterator();
					while(i.hasNext()){
						State currState0Item = i.next();
						if(goTo(currState0Item, buffer0)==null){
							if(currState0Item.getNextStateCollection()==null)currState0Item.setNextStateCollection(new HashMap<String,State>());
							currState0Item.getNextStateCollection().put(buffer0, new State(buffer0, root));
						}
						nextCurrState0.add(goTo(currState0Item, buffer0));
						i.remove();
					}
					currState0.addAll(nextCurrState0);
					nextCurrState0.clear();
					
				}
				
				if(buffer1.length()==2){
					if(goTo(currState1, buffer1)==null){
						if(currState1.getNextStateCollection()==null)currState1.setNextStateCollection(new HashMap<String,State>());
						currState1.getNextStateCollection().put(buffer1, new State(buffer1, root));
					}
					currState1 = goTo(currState1, buffer1);
				}
				
			}
			
		}
	}
	
	/**A function to move from 1 node of a trie to it's fail node*/
	private State failFrom(State node){
		return node.getFailState();
	}
	
	/**Create the fail fall back state of AhoCorasick trie*/
	public void prepareFailFromFunction(){
		LinkedList<State> queue = new LinkedList<State>(); //a linked list is needed for BFS
		
		for (State state : root.getNextStateCollection().values()) {
			queue.add(state);
			state.setFailState(root);
		}
		
		State tempState;
		
		while(!queue.isEmpty()){
			tempState = queue.pop(); //pop node and get the childrens
			if(tempState.getNextStateCollection()!=null){
				for (State state: tempState.getNextStateCollection().values()) { //implementation differ based on nextStateCollection data structure
					queue.add(state);
					currState=failFrom(tempState);
					while(goTo(currState, state.getStateContentCharacter())==null&&!currState.equals(root)){ //while fail 
						currState = failFrom(currState); //current state = failState	
					}//exit while when found a match from goTo of a failState or when it reach root
					if(goTo(currState, state.getStateContentCharacter())!=null){
						state.setFailState(goTo(currState, state.getStateContentCharacter()));
					}
				}
			}
		}
	}
	
	/**used to convert the trie state as a linkedlist).
	 * Only use this on original trie. Usage on derivated trie will cause loop. 
	 * This includes root state.*/
	private LinkedList<State> walkthroughTrie(){
		LinkedList<State> queue = new LinkedList<State>(); //a linked list is needed for BFS
		LinkedList<State> resultQueue = new LinkedList<State>(); //a linked list is needed for BFS
		resultQueue.add(root);
		for (State state : root.getNextStateCollection().values()) {
			queue.add(state);
			resultQueue.add(state);
		}
		
		State tempState;
		
		while(!queue.isEmpty()){
			tempState = queue.pop(); //pop node and get the childrens
			if(tempState.getNextStateCollection()!=null){
				for (State state : tempState.getNextStateCollection().values()) {
					queue.add(state);
					resultQueue.add(state);
				}
			}
		}
		return resultQueue;
	}

	/**A function to match input string against constructed AhoCorasick trie*/
	public void nPatternMatching(File inputFile){
		
		currState = root;
		lineNumberCounter=1;
		columnNumberCounter=1;
		char[] cBuf = new char[2];
		String sBuf = null;
		
		algoStart=System.nanoTime();
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.read(cBuf, 0, 2) != -1) {
				sBuf = String.valueOf(cBuf);
				columnNumberCounter+=2;
				if(sBuf.contains("\n")){
					lineNumberCounter++;
					columnNumberCounter=1;
				}
//				System.out.println("ME SEARCH: "+sBuf+"@L"+lineNumberCounter+"C"+columnNumberCounter);
				while (goTo(currState, sBuf)==null&&!currState.equals(root)) { //repeat fail function as long goTo function is failing
					currState= failFrom(currState);
				}
				if(goTo(currState, sBuf)!=null){
					currState = goTo(currState, sBuf); //set the current node to the result of go to function
					prepareOutput(currState,lineNumberCounter, columnNumberCounter);
//					System.out.println("FOUND ME A: "+sBuf+"@L"+lineNumberCounter+"C"+columnNumberCounter);
				}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		algoEnd = System.nanoTime();
		Utility.writeAhoCorasickTime(algoEnd-algoStart);
	}

	/**prepare output for the matching keywords found*/
	private void prepareOutput(State state,int lineNumber, int endColumnNumber){
		if(state.getFullKeywordHashCodeList()!=null){//jika currNode = fullword
			for (Integer keywordHashCode : state.getFullKeywordHashCodeList()) {
				if(keywordHashCode!=null)outputList.add(new Output(keywordHashCode, lineNumber, endColumnNumber));
			}
		}
		
		while(!failFrom(state).equals(root)){//jika state tersebut punya fail node yang bukan root
			state = failFrom(state);
			if(state.getFullKeywordHashCodeList()!=null){//jika currNode = fullword
				for (Integer keywordHashCode : state.getFullKeywordHashCodeList()) {
					if(keywordHashCode!=null)outputList.add(new Output(keywordHashCode, lineNumber, endColumnNumber));
				}
			}
		}
	}
	
//	/**prepare output for the matching keywords found*/
//	private void prepareOutputFail(State state,int lineNumber, int endColumnNumber){
//		if(state.getFullKeywordHashCode()!=null){//jika currNode = fullword
//			outputList.add(new Output(state.getFullKeywordHashCode(), lineNumber, endColumnNumber));
//		}
//	}
//	
//	/**prepare output for the matching keywords found*/
//	private void prepareOutputSuccess(State state,int lineNumber, int endColumnNumber){
//		if(state.getFullKeywordHashCode()!=null){//jika currNode = fullword
//			outputList.add(new Output(state.getFullKeywordHashCode(), lineNumber, endColumnNumber));
//		}
//		while(!failFrom(state).equals(root)){//jika state tersebut punya fail node yang bukan root
//			state = failFrom(state);
//			if(state.getFullKeywordHashCode()!=null){//jika failState == fullword
//				outputList.add(new Output(state.getFullKeywordHashCode(), lineNumber, endColumnNumber));
//			}
//		}
//	}

	public void trieInsight(String keyword){
		State pewState = root;
		System.out.print("root: ");
		for (State state : root.getNextStateCollection().values()) {
			System.out.print(state.getStateContentCharacter()+",");
		}System.out.println();
		for (int i = 0; i < keyword.length(); i++) {
			pewState=goTo(pewState, keyword);
			System.out.print(pewState.getStateContentCharacter()+": ");
			for (State yadaState : pewState.getNextStateCollection().values()) {
				System.out.print(yadaState.getStateContentCharacter()+",");
			}System.out.println("");
		}
		
	}
	
}
