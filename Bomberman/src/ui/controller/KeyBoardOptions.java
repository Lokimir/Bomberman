package ui.controller;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;

public class KeyBoardOptions {
	
	HashMap<String, Integer> keyboard;
	
	public KeyBoardOptions(int up, int down, int left, int right, int dropBomb){
		keyboard = new HashMap<String, Integer>();
		keyboard.put(Action.UP.toString(), up);
		keyboard.put(Action.DOWN.toString(), down);
		keyboard.put(Action.LEFT.toString(), left);
		keyboard.put(Action.RIGHT.toString(), right);
		keyboard.put(Action.DROP.toString(), dropBomb);
	}
	
	public int getKey(Action action){
		return keyboard.get(action.toString());
	}
}
