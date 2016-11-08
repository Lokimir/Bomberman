package core;

import java.util.ArrayList;

import ui.view.BasicDraftman;

public class Map {
	
	private final static int WIDTH = 10;
	private final static int HEIGHT = 10;
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
	
	// buildmap est poubelle, alternance ligne pleine de DestructiveCell et d'alternance Undestructible, Destructible
	private void buildMap() {
		
		for(int j = 0; j <= WIDTH; j++){
			for(int i = 0; i <= HEIGHT; i++){
				if( j % 2 == 0 || i % 2 == 0)
					cells.add(buildDestCell(j,i));
				else
					cells.add(buildUnDestCells(j,i));				
			}
		}
		getCell(0, 0).setState(StateCell.BROKE);
		getCell(0, 1).setState(StateCell.BROKE);
		getCell(1, 0).setState(StateCell.BROKE);
		getCell(10, 10).setState(StateCell.BROKE);
		getCell(10, 9).setState(StateCell.BROKE);
		getCell(9, 10).setState(StateCell.BROKE);
	}

	private Cell buildUnDestCells(int x, int y) {
		return new Cell(x, y, StateCell.UNBREAKABLE);
	}

	private Cell buildDestCell(int x, int y) {
		return new Cell(x, y, StateCell.BREAKABLE);
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
