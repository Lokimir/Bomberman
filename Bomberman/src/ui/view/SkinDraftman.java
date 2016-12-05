package ui.view;

import java.awt.Color;
import java.awt.Graphics2D;

import core.Bomb;
import core.Map;
import core.Player;
import core.cell.Cell;

public class SkinDraftman implements BombermanVisitor {

	private Graphics2D g2d;
	
	private final int CELL_SIZE_WIDTH = 48;
	private final int CELL_SIZE_HEIGHT = 48;

	@Override
	public void visitBomb(Bomb bomb) {
		if (bomb.isExplosing()){
			g2d.setColor(Color.ORANGE);
			for (Cell c : bomb.getTargets()){
				g2d.drawImage(bomb.getExplosionSprite(), c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, null);
			}
		} else {
			g2d.drawImage(bomb.getCurrentSprite().getCurrent(), bomb.getX()*CELL_SIZE_WIDTH , bomb.getY()*CELL_SIZE_HEIGHT, null);
		}
	}

	@Override
	public void visitMap(Map m) {
		for (Cell c: m.getCells()){
			c.accept(this);
		}	
	}

	@Override
	public void visitPlayer(Player p) {
		g2d.setColor(Color.RED);
		g2d.drawImage(p.getSprite(), p.getX()*CELL_SIZE_WIDTH, p.getY()*CELL_SIZE_HEIGHT, null);	
	}

	@Override
	public void visitCell(Cell c) {
		g2d.drawImage(c.getSprite(), c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, null);
		if(c.containBonus() && c.isWalkable()){
			g2d.drawImage(c.getBonus().getSprite(), c.getX()*CELL_SIZE_WIDTH, c.getY()*CELL_SIZE_HEIGHT, null);			
		}
	}

	public void setGraphics(Graphics2D g2d) {
		this.g2d = g2d;
	}

}
