package ui.view;

import java.awt.Color;
import java.awt.Graphics2D;

import core.Bomb;
import core.Map;
import core.Player;
import core.cell.BreakableCell;
import core.cell.Cell;
import core.cell.FloorCell;

public class BasicDraftman implements BombermanVisitor	{
	
	private Graphics2D g2d;

	private final int CELL_SIZE_WIDTH = 48;
	private final int CELL_SIZE_HEIGHT = 48;

	public void setGraphics(Graphics2D g2d)
	{
		this.g2d = g2d;
	}

	@Override
	public void visitBomb(Bomb bomb) {
		if (!bomb.isExplosing()){
			g2d.setColor(Color.BLUE);
			g2d.drawOval(bomb.getX()*CELL_SIZE_WIDTH, bomb.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
		} else {
			g2d.setColor(Color.ORANGE);
			for (Cell c : bomb.getTargets()){
				g2d.fillOval(c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
			}
		}
	}

	@Override
	public void visitMap(Map m) {		
		for (Cell c: m.getCells()){
			c.accept(this);
		}
	}

	public void visitCell(Cell c){
		if (c.getClass() == FloorCell.class)
			g2d.setColor(Color.WHITE);
		else if (c.getClass() == BreakableCell.class)
			g2d.setColor(Color.GRAY);	
		else {
			g2d.setColor(Color.BLACK);				
		}
		g2d.fillRect(c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);

		if(c.containBonus() && c.isWalkable()){
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
