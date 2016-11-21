package ui;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.Bomb;
import core.BonusBomb;
import core.BonusSpread;
import core.Cell;
import core.Model;
import core.Player;
import ui.controller.BasicController;
import ui.controller.KeyBoardOptions;
import ui.controller.PlayerController;
import ui.view.BasicDraftman;
import ui.view.BombermanVisitor;
import ui.view.SkinDraftman;

public class GameView extends JPanel {

	private Model model;
	private Map<Player,PlayerController> controllers;
	private JPanel cardPanel;

	private boolean skinned;
	
	public GameView(Model model, JPanel cardPanel)
	{
		this.cardPanel = cardPanel;
		this.model = model;
		
		this.controllers = new HashMap<>();
		this.controllers.put(model.getPlayer(0),new PlayerController(model, model.getPlayer(0), new KeyBoardOptions(KeyEvent.VK_Z,KeyEvent.VK_S,KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_SPACE)));
		this.controllers.put(model.getPlayer(1),new PlayerController(model, model.getPlayer(1), new KeyBoardOptions(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER)));
		this.controllers.get(model.getPlayer(0)).setView(this);
		this.controllers.get(model.getPlayer(1)).setView(this);
		this.addKeyListener(this.controllers.get(model.getPlayer(0)));
		this.addKeyListener(this.controllers.get(model.getPlayer(1)));

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
		Cell.unbreakableSprite = ImageIO.read(new File(skinURL + "unbreakable.png"));
		Cell.breakableSprite = ImageIO.read(new File(skinURL + "breakable.png"));	
		Cell.floorSprite = ImageIO.read(new File(skinURL + "floor.png"));
		Player.playerSprite = ImageIO.read(new File(skinURL + "player1.png"));
		BonusBomb.setSprite(ImageIO.read(new File(skinURL + "bonusBomb.png")));
		BonusSpread.setSprite(ImageIO.read(new File(skinURL + "bonusSpread.png")));
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
	
	public BasicController getController(Player p){
		return controllers.get(p);
	}

	public Map<Player,PlayerController> getControllers() {
		return controllers;
	}
	
	public void switchEndGameView(){
		((CardLayout)cardPanel.getLayout()).show(cardPanel, Bomberman.ENDGAMEVIEW);
	}

}
