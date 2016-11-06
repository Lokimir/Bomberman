package model;

import java.util.ArrayList;

public class Map {
	
	private ArrayList<Player> players;
	private ArrayList<ArrayList<Cell>> cells;
	
	public Map(){
		players = new ArrayList<>();
		cells = new ArrayList<>();
		
		buildMap();
	}
	
	// buildmap est poubelle, alternance ligne pleine de DestructiveCell et d'alternance Undestructible, Destructible
	private void buildMap() {
		
		for(int j = 0; j < MapSetup.getInstance().getWidth(); j++){
			if( j % 2 == 0)
				cells.add(buildCells());
			else
				cells.add(buildUDCells());
		}
		
	}

	private ArrayList<Cell> buildUDCells() {
		ArrayList<Cell> cell2 = new ArrayList<>();
		for(int i = 0; i < MapSetup.getInstance().getHeight(); i++){
			if( i % 2 == 0)
				cell2.add(new DestructiveCell());
			else
				cell2.add(new UndestructibleCell());
		}
		return cell2;
	}

	private ArrayList<Cell> buildCells() {
		ArrayList<Cell> cell1 = new ArrayList<>();
		for(int i = 0; i < MapSetup.getInstance().getHeight(); i++)
			cell1.add(new DestructiveCell());
		return cell1;
	}

	public Cell getCell(int x, int y){
		return cells.get(x).get(y);
	}
}
