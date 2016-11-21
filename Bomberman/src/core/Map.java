package core;

import java.util.ArrayList;

import ui.view.BombermanVisitor;

public class Map {
	
	// 2 row & columns are for walls
	private final static int WIDTH = 12;
	private final static int HEIGHT = 12;
	
	private ArrayList<Cell> cells;

	public Map(){
		cells = new ArrayList<>();
		buildMap();
	}
	
	private void buildMap() {
		
		// Build du terrain
		for(int j = 1; j <= WIDTH-1; j++){
			for(int i = 1; i <= HEIGHT-1; i++){
				if( j % 2 == 1 || i % 2 == 1)
					cells.add(new Cell(j, i, StateCell.BREAKABLE));
				else
					cells.add(new Cell(j, i, StateCell.UNBREAKABLE));				
			}
		}
		
		for (int j = 0; j <= WIDTH; j++){
			cells.add(new Cell(j,0,StateCell.UNBREAKABLE));
			cells.add(new Cell(j,HEIGHT,StateCell.UNBREAKABLE));
		}
		
		for (int i = 1; i < HEIGHT; i++){
			cells.add(new Cell(0,i,StateCell.UNBREAKABLE));
			cells.add(new Cell(WIDTH, i, StateCell.UNBREAKABLE));
		}
		
		
		// Gestion cas particulier
		getCell(1, 1).setState(StateCell.BROKE);
		getCell(1, 1).takeBonus();
		getCell(1, 2).setState(StateCell.BROKE);
		getCell(1, 2).takeBonus();
		getCell(2, 1).setState(StateCell.BROKE);
		getCell(2, 1).takeBonus();
		getCell(WIDTH-1, HEIGHT-1).setState(StateCell.BROKE);
		getCell(WIDTH-1, HEIGHT-1).takeBonus();
		getCell(WIDTH-1, HEIGHT-2).setState(StateCell.BROKE);
		getCell(WIDTH-1, HEIGHT-2).takeBonus();
		getCell(WIDTH-2, HEIGHT-1).setState(StateCell.BROKE);
		getCell(WIDTH-2, HEIGHT-1).takeBonus();
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

	public void accept(BombermanVisitor bv) {
		bv.visitMap(this);
	}
}
