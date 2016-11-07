package ui.view;

import core.Bomb;
import core.Map;
import core.Player;

public interface BombermanVisitor {

	public void visitBomb(Bomb b);
	public void visitMap(Map m);
	public void visitPlayer(Player p);
}
