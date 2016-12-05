package ui;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ui.controller.BasicController;
import ui.controller.KeyBoardOptions;
import ui.controller.PlayerController;
import ui.view.BasicDraftman;
import ui.view.BombermanVisitor;
import ui.view.SkinDraftman;
import core.Bomb;
import core.Model;
import core.Player;
import core.bonus.BonusBomb;
import core.bonus.BonusSpread;
import core.cell.BreakableCell;
import core.cell.FloorCell;
import core.cell.UnbreakableCell;

public class GameView extends JPanel {

	private Model model;

	private boolean skinned;
	private JPanel cardPanel;

	public GameView(Model model, JPanel cardPanel)
	{
		this.cardPanel = cardPanel;
		this.model = model;
		BasicController controller = new PlayerController(model, model.getPlayer(0), new KeyBoardOptions(KeyEvent.VK_Z,KeyEvent.VK_S,KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_SPACE));
		controller.setView(this);
		this.addKeyListener(controller);
		controller = new PlayerController(model, model.getPlayer(1), new KeyBoardOptions(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER));
		controller.setView(this);
		this.addKeyListener(controller);

		this.setFocusable(true);

		try {
			this.setSkins();
			skinned = true;
		} catch (IOException e) {
			System.out.println("Skin manquant !");
			skinned = false;
		}
	}
	
	private void setSkins() throws IOException {
		String skinURL = "res" + File.separatorChar + "sprites" + File.separatorChar;
		Bomb.setBombSprite(ImageIO.read(new File(skinURL + "bomb.png")));
		Bomb.setExplosionSprite(ImageIO.read(new File(skinURL + "explosion.png")));
		UnbreakableCell.setSprite(ImageIO.read(new File(skinURL + "unbreakable.png")));
		BreakableCell.setSprite(ImageIO.read(new File(skinURL + "breakable.png")));	
		FloorCell.setSprite(ImageIO.read(new File(skinURL + "floor.png")));
		BonusBomb.setSprite(ImageIO.read(new File(skinURL + "bonusBomb.png")));
		BonusSpread.setSprite(ImageIO.read(new File(skinURL + "bonusSpread.png")));
		
		int i = 1;
		for(Player p : model.getPlayers()){
			p.setSprite(ImageIO.read(new File(skinURL + "player" + i++ + ".png")));
		}
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		super.paintComponent(g2d);
		
		BombermanVisitor draftman;
		if (skinned){
			draftman = new SkinDraftman();
		} else
			draftman = new BasicDraftman();
		
		draftman.setGraphics(g2d);
		
		this.model.getMap().accept(draftman);
		
		for (Bomb b : this.model.getBombs())
		{
			b.accept(draftman);
		}
		
		for (Player p : this.model.getPlayers())
		{
			p.accept(draftman);
		}
	}
	
	public Model getModel(){
		return model;
	}
	
	public void switchEndGameView(){
		((CardLayout)cardPanel.getLayout()).show(cardPanel, Bomberman.ENDGAMEVIEW);
	}
}
