package timothyyudi.ahocorasickmulticharacter_bulknaive.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public void prepareNaiveAlpha(){
		root.setNextStateCollection(new HashMap<>());
		for (String printableASCII : printableASCIIList) {
			root.getNextStateCollection().put(printableASCII, new State(printableASCII, root));
		}
//		System.out.println("rootchild size: "+root.getNextStateCollection().size());
	}
	
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
		currState = root;
		keywordInsertionCounter = 0;
		String initialCharacter = Character.toString(keyword.charAt(0));
		State naiveAlphaState;
		
		while(keywordInsertionCounter<keyword.length() && goTo(currState, Character.toString(keyword.charAt(keywordInsertionCounter)))!=null){ //while state already exist then go there.
			currState = goTo(currState, Character.toString(keyword.charAt(keywordInsertionCounter)));
			keywordInsertionCounter++;
		}
	
		while(keywordInsertionCounter<keyword.length() && goTo(currState, Character.toString(keyword.charAt(keywordInsertionCounter)))==null){ //while state doesnt exist then create new node and go there
			if(currState.getNextStateCollection()==null)currState.setNextStateCollection(new HashMap<String,State>());
				currState.getNextStateCollection().put(Character.toString(keyword.charAt(keywordInsertionCounter)), new State(Character.toString(keyword.charAt(keywordInsertionCounter)), root));
			currState = goTo(currState, Character.toString(keyword.charAt(keywordInsertionCounter)));
			
			keywordInsertionCounter++;
		}
		
		if(keywordInsertionCounter==keyword.length()){
			currState.setFullKeywordHashCode(keyword.hashCode());
//			System.out.println("Finale: "+currState.getStateContentCharacter());
		}

		//add naive omega
		if(currState.getNextStateCollection()==null) {
			currState.setNextStateCollection(new HashMap<>());
		}
		
		currState.getNextStateCollection().putIfAbsent("", new State("", root));
		currState.getNextStateCollection().putIfAbsent("\n", new State("\n", root));
		currState.getNextStateCollection().putIfAbsent("\r", new State("\r", root));
		for (int i = 32; i < 127; i++) {
			currState.getNextStateCollection().putIfAbsent(Character.toString((char)i), new State(Character.toString((char)i), root));
		}
		
//		System.out.println(currState.getNextStateCollection().size());
		
		//add naive alpha @root
//		System.out.println("inserting "+keyword);
//		System.out.println("root child size: "+root.getNextStateCollection().size());
		for (State rootChildState : root.getNextStateCollection().values()) {
			if(rootChildState.getNextStateCollection()==null)rootChildState.setNextStateCollection(new HashMap<String,State>());
			rootChildState.getNextStateCollection().putIfAbsent(initialCharacter, new State(initialCharacter, root));
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
	
	public void prepare2Trie(){
		
		LinkedList<State> oriStateQueue = walkthroughTrie();
//		LinkedList<State> oriStateQueueForPhase1 = new LinkedList<>(oriStateQueue); 
		LinkedList<State> oriStateQueueForPhase2 = new LinkedList<>(oriStateQueue); 
		
		State tempState; //aka Si, placeholder for loop.
		HashMap<String, State> tempNextLiteratedStatePointerMap;
		String tempNewPattern="";
		
		// [Phase 2] Start creating derivation node
		while(!oriStateQueueForPhase2.isEmpty()) {
			tempState=oriStateQueueForPhase2.pop();
			tempNextLiteratedStatePointerMap = new HashMap<String,State>();
			//create a copy so the hashmap can be added, avoid concurrency problem
			HashMap<String,State> cpOriChildStateHM;
			if(tempState.getNextStateCollection()!=null){
				cpOriChildStateHM = new HashMap<String,State>(tempState.getNextStateCollection()); //jika curr state =0 maka ini adalah node lvl 1	
			
				for (State stateNXi : cpOriChildStateHM.values()) {//BEGIN 4th phase
					HashMap<String, State> stateNXiChilds = stateNXi.getNextStateCollection();//contoh anak node level 1
					if(stateNXiChilds!=null){//???
						for (State stateNXj : stateNXiChilds.values()) {//BEGIN 5th phase//ada 2 node baru dengan multichar di queuetmpset
							tempNewPattern = stateNXi.getStateContentCharacter()+stateNXj.getStateContentCharacter();
							tempNextLiteratedStatePointerMap.putIfAbsent(tempNewPattern, stateNXj);
							//implementasi fullKeywordHashCodeList pada state kedua
							if(stateNXj.getFullKeywordHashCodeList()==null)stateNXj.setFullKeywordHashCodeList(new ArrayList<>());
							stateNXj.getFullKeywordHashCodeList().add(stateNXi.getFullKeywordHashCode());
							stateNXj.getFullKeywordHashCodeList().add(stateNXj.getFullKeywordHashCode());
//							System.out.println(stateNXi.getStateContentCharacter()+": "+stateNXi.getNextStateCollection().size());
//							System.out.println(tempNewPattern+" state created");
						}//END 5th phase
					}
					//mungkin bisa improve dengan add fullKeyword dari failnode di sini kekny salah tempat
				}//END 4th phase
				tempState.getNextStateCollection().putAll(tempNextLiteratedStatePointerMap);
			}
		}//END 2nd Phase
	}//END
	
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
	
//	/**A function to match input string against constructed AhoCorasick trie*/
//	public void nPatternMatching(File inputFile){
//		
//		currState = root;
//		lineNumberCounter=1;
//		columnNumberCounter=1;
//		String inputStringBuffer="";
//		char[] cBuf = new char[2];
//		String sBuf = null;
//		
//		algoStart=System.nanoTime();
//		try {
//			FileReader fileReader = new FileReader(inputFile);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			while (bufferedReader.read(cBuf, 0, 2) != -1) {
//				sBuf = String.valueOf(cBuf);
//				
//				columnNumberCounter+=2;
//				if(sBuf.equals("\n")){
//					lineNumberCounter++;
//					columnNumberCounter=1;
//				}
//
//				while (goTo(currState, sBuf)==null&&!currState.equals(root)) { //repeat fail function as long goTo function is failing
//					try {
//						if(sBuf.length()==2)
//							prepareOutputFail(currState.getNextStateCollection().get(Character.toString(cBuf[0])), lineNumberCounter, columnNumberCounter);
//					} catch (Exception e) {}
//					
//					currState= failFrom(currState);
//				}
//				if(goTo(currState, sBuf)!=null){
//					try {
//						if(sBuf.length()==2)
//							prepareOutputSuccess(currState.getNextStateCollection().get(Character.toString(cBuf[0])), lineNumberCounter, columnNumberCounter);// tadinya buat cetak yang ke skip tapi malah kena yang emang cuman 1 input.
//					} catch (Exception e) {}
//					currState = goTo(currState, sBuf); //set the current node to the result of go to function
//					try {
////						prepareOutput(currState, lineNumberCounter, columnNumberCounter, AhoCorasick.SUPPORTSKIPPEDNODEFORSUCCESS);
//						prepareOutputSuccess(currState, lineNumberCounter, columnNumberCounter);
//					} catch (Exception e) {}
////					System.out.println("input: "+inputStringBuffer);
//				}else if(goTo(currState, sBuf)==null&&currState.equals(root)){	//shifting enforcer
//					try {
//						currState = goTo(currState, Character.toString(cBuf[1])); //set the current node to the result of go to function
////						prepareOutput(currState, lineNumberCounter, columnNumberCounter, AhoCorasick.SUPPORTSKIPPEDNODEFORSUCCESS);
//						prepareOutputSuccess(currState, lineNumberCounter, columnNumberCounter);
//					} catch (Exception e) {
//						currState = root;
//					}
//				}
//			}
//			fileReader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		algoEnd = System.nanoTime();
//		Utility.writeAhoCorasickTime(algoEnd-algoStart);
//		
//	}

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
//				columnNumberCounter+=2;
//				if(sBuf.contains("\n")){
//					lineNumberCounter++;
//					columnNumberCounter=1;
//				}
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

	public void trieInsight(String initialStateChara){
		State initialState = root.getNextStateCollection().get(initialStateChara);
		System.out.print(initialState.getStateContentCharacter()+": ");
		for (String key : initialState.getNextStateCollection().keySet()) {
			System.out.print(key+",");
		}
		
	}
	
}
