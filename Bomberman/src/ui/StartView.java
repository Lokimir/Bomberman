package ui;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import core.Model;

public class StartView extends JPanel {
	
	private final int BUTTON_SIZE_WIDTH = 100;
	private final int BUTTON_SIZE_HEIGHT = 30;
	
	
	private Model model;
	private final Button newGameButton;
	private final Button exitButton;
	private final JTextArea message;
	

	public StartView(Model _model, JPanel cardPanel){
		this.setLayout(null);
		this.model = _model;
		this.setPreferredSize(new Dimension(model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48));
		
		/* Ajout du message de fin de partie */
		this.message = new JTextArea();
		this.add(message);
		message.setBounds(624/2-40, 624/2-20,80 ,40);
		
		/* Ajout du bouton restart */
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.restore();
				((CardLayout) cardPanel.getLayout()).show(cardPanel, Bomberman.GAMEVIEW);
				for(Component comp : cardPanel.getComponents())
					if(comp.getClass() == GameView.class){
						comp.requestFocusInWindow();
						return;
					}
			}
		});
		this.add(newGameButton);
		newGameButton.setBounds(((model.getMap().getWidth()+1)*48-BUTTON_SIZE_WIDTH)/2, 500, BUTTON_SIZE_WIDTH, BUTTON_SIZE_HEIGHT);
		
		/* ajout du bouton exit */
		exitButton = new Button("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(exitButton);
		exitButton.setBounds(((model.getMap().getWidth()+1)*48-BUTTON_SIZE_WIDTH)/2, 540, BUTTON_SIZE_WIDTH, BUTTON_SIZE_HEIGHT);
		
		
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int winner = 0;
		for(int i = 0; i < model.getPlayers().size(); i++)
			if(model.getPlayer(i).isAlive()){
				winner = ++i;
				break;
			}			
			
		this.message.setText("BOMBERMAN !");
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 624, 624);
	}
}
