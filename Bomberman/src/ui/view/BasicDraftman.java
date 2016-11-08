package ui.view;

import java.awt.Color;
import java.awt.Graphics2D;

import core.Bomb;
import core.Cell;
import core.Map;
import core.Player;

public class BasicDraftman implements BombermanVisitor	{
	private Graphics2D g2d;
		
	private final int CELL_SIZE_WIDTH = 48;
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
		for (Cell c: Map.getInstance().getCells()){
			c.accept(this);
		}
	}
	
	public void visitCell(Cell c){
		if (c.isBroke())
			g2d.setColor(Color.WHITE);
		else if (c.isBreakable())
			g2d.setColor(Color.GRAY);				
		else if (c.isExplosing())
			g2d.setColor(Color.ORANGE);
		else {
			g2d.setColor(Color.BLACK);				
		}
		g2d.fillRect(c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
		
		if(c.containBonus() && c.isBroke()){
			g2d.setColor(Color.GREEN);
			g2d.fillOval(c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
		}
	}

	@Override
	public void visitPlayer(Player p) {
		g2d.setColor(Color.RED);
		g2d.fillOval(p.getX()*CELL_SIZE_WIDTH, p.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
	}

}
