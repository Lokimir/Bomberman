package ui.view;

import java.awt.Graphics2D;

import core.Bomb;
import core.Map;
import core.Player;
import core.cell.Cell;

public interface BombermanVisitor {
	
	public void visitBomb(Bomb b);
	public void visitMap(Map m);
	public void visitPlayer(Player p);
	public void visitCell(Cell c);
	public void setGraphics(Graphics2D g2d);
}
