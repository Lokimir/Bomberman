package ui.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import core.Bomb;
import core.Cell;
import core.Map;
import core.Player;

public class BasicDraftman implements BombermanVisitor	{
	private Graphics2D g2d;
		
	private final int CELL_SIZE_WIDTH = 64;
	private final int CELL_SIZE_HEIGHT = 48;
	
	public void setGraphics(Graphics2D g2d)
	{
		this.g2d = g2d;
	}
	
	@Override
	public void visitBomb(Bomb b) {
		g2d.setColor(Color.BLUE);
		g2d.drawOval(b.getX()*CELL_SIZE_WIDTH, b.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
	}

	@Override
	public void visitMap(Map m) {		
		int i = 0;
		for (ArrayList<Cell> lc: Map.getInstance().getCells()){
			int j = 0;
			for (Cell c : lc){
				if (c.isBroke())
					g2d.setColor(Color.WHITE);
				else if (c.isBreakable())
					g2d.setColor(Color.GRAY);				
				else
					g2d.setColor(Color.BLACK);
				
				g2d.fillRect(i*CELL_SIZE_WIDTH, j*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
				j++;
			}
			i++;
		}
	}

	@Override
	public void visitPlayer(Player p) {
		g2d.setColor(Color.RED);
		g2d.fillOval(p.getX()*CELL_SIZE_WIDTH, p.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
	}

}
