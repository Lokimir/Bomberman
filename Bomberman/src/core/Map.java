package core;

import java.util.ArrayList;
import java.util.Random;

import core.bonus.Bonus;
import core.bonus.BonusBomb;
import core.bonus.BonusSpread;
import core.cell.BreakableCell;
import core.cell.Cell;
import core.cell.FloorCell;
import core.cell.UnbreakableCell;
import ui.view.BombermanVisitor;

public class Map {
	
	// 2 rows & columns are for the surrounding walls
	private final static int WIDTH = 12;
	private final static int HEIGHT = 12;
	
	private final static int EMPTY_CASE = 10;
	
	
	private ArrayList<Cell> cells;

	public Map(){
		cells = new ArrayList<>();
		buildMap();
	}
	
	private void buildMap() {
		// Build the playable map
		for(int j = 1; j <= WIDTH-1; j++){	
			for(int i = 1; i <= HEIGHT-1; i++){
				if( j % 2 == 1 || i % 2 == 1){
					if((i == 1 || j == 1) && Math.abs(i - j) <= 1)
						cells.add(new FloorCell(j, i));
					else if((i == WIDTH-1 || j == HEIGHT-1) && Math.abs(i - j) <= 1)
						cells.add(new FloorCell(j, i));
					else
						cells.add(new BreakableCell(j, i, this.randomBonus()));
				}
				else
					cells.add(new UnbreakableCell(j, i));				
			}
		}
		
		for (int i = 0; i < EMPTY_CASE; i++){
			Random r = new Random();
			int next = r.nextInt(cells.size());
			Cell c = cells.get(next);
			cells.remove(c);
			cells.add(new FloorCell(c));
		}
		
		// Build the surrounding walls
		for (int j = 0; j <= WIDTH; j++){
			cells.add(new UnbreakableCell(j,0));
			cells.add(new UnbreakableCell(j,HEIGHT));
		}
		// Build the surrounding walls
		for (int i = 1; i < HEIGHT; i++){
			cells.add(new UnbreakableCell(0,i));
			cells.add(new UnbreakableCell(WIDTH, i));
		}
	}

	private Bonus randomBonus() {
		Bonus bonus;
		double percent = Math.random()*100;
		if(percent < 7.5){
			bonus = new BonusSpread();
		} else if (percent < 15)
			bonus = new BonusBomb();
		else 
			bonus = null;
		return bonus;
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
