package ui.controller;

import java.util.HashMap;

public class KeyBoardOptions {
	
	HashMap<Action, Integer> keyboard;
	
	public KeyBoardOptions(int up, int down, int left, int right, int dropBomb){
		keyboard = new HashMap<>();
		keyboard.put(Action.UP, up);
		keyboard.put(Action.DOWN, down);
		keyboard.put(Action.LEFT, left);
		keyboard.put(Action.RIGHT, right);
		keyboard.put(Action.DROP, dropBomb);
	}
	
	public int getKey(Action action){
		return keyboard.get(action);
	}
}
