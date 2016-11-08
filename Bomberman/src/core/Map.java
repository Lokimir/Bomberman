package core;

import java.util.ArrayList;

import ui.view.BasicDraftman;

public class Map {
	
	// 2 row & columns are for walls
	private final static int WIDTH = 12;
	private final static int HEIGHT = 12;
	
	private ArrayList<Cell> cells;

	private static Map map;

	private Map(){
		cells = new ArrayList<>();
		buildMap();
	}
	
	public static Map getInstance(){
		if(map == null)
			map = new Map();
		return map;
	}
	
	private void buildMap() {
		
		// Build du terrain
		for(int j = 0; j <= WIDTH; j++){
			for(int i = 0; i <= HEIGHT; i++){
				if( j % 2 == 0 || i % 2 == 0)
					cells.add(new Cell(j, i, StateCell.BREAKABLE));
				else
					cells.add(new Cell(j, i, StateCell.UNBREAKABLE));				
			}
		}
		
		// Gestion cas particulier
		getCell(0, 0).setState(StateCell.BROKE);
		getCell(0, 0).takeBonus();
		getCell(0, 1).setState(StateCell.BROKE);
		getCell(0, 1).takeBonus();
		getCell(1, 0).setState(StateCell.BROKE);
		getCell(1, 0).takeBonus();
		getCell(10, 10).setState(StateCell.BROKE);
		getCell(10, 10).takeBonus();
		getCell(10, 9).setState(StateCell.BROKE);
		getCell(10, 9).takeBonus();
		getCell(9, 10).setState(StateCell.BROKE);
		getCell(9, 10).takeBonus();
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public Cell getCell(int x, int y){
		for(Cell c : cells){
			if(c.getX() == x && c.getY() == y)
				return c;
		}
		return null;
	}
	
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void accept(BasicDraftman bd) {
		bd.visitMap(this);
	}
}
