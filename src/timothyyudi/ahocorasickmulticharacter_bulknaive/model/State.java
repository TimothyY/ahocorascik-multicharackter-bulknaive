package timothyyudi.ahocorasickmulticharacter_bulknaive.model;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

	String stateContentCharacter;
	State failState;
	Integer fullKeywordHashCode;
	ArrayList<Integer> fullKeywordHashCodeList;
	HashMap<String, State> nextStateCollection;

	/**Called when root is created.*/
	public State(){
		super();
		this.stateContentCharacter = null;
		this.failState = null;
		this.fullKeywordHashCode = null;
		this.fullKeywordHashCodeList = null;
		this.nextStateCollection = null;
	}

	/**Called each time a new state is created*/
	public State(String stateContentCharacter, State failState){
		super();
		this.stateContentCharacter = stateContentCharacter;
		this.failState = failState;
		this.fullKeywordHashCode = null;
		this.fullKeywordHashCodeList = null;
		this.nextStateCollection = null;
	}
	
	public String getStateContentCharacter() {
		return stateContentCharacter;
	}

	public void setStateContentCharacter(String stateContentCharacter) {
		this.stateContentCharacter = stateContentCharacter;
	}

	public State getFailState() {
		return failState;
	}

	public void setFailState(State failState) {
		this.failState = failState;
	}

	public Integer getFullKeywordHashCode() {
		return fullKeywordHashCode;
	}

	public void setFullKeywordHashCode(Integer fullKeywordHashCode) {
		this.fullKeywordHashCode = fullKeywordHashCode;
	}

	public ArrayList<Integer> getFullKeywordHashCodeList() {
		return fullKeywordHashCodeList;
	}

	public void setFullKeywordHashCodeList(
			ArrayList<Integer> fullKeywordHashCodeList) {
		this.fullKeywordHashCodeList = fullKeywordHashCodeList;
	}

	public HashMap<String, State> getNextStateCollection() {
		return nextStateCollection;
	}

	public void setNextStateCollection(HashMap<String, State> nextStateCollection) {
		this.nextStateCollection = nextStateCollection;
	}
}
