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

public class EndGameView extends JPanel {

	private Model model;
	private final Button restartButton;
	private final JTextArea message;

	public EndGameView(Model _model, JPanel cardPanel){
		this.model = _model;
		this.setPreferredSize(new Dimension(model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48));
		
		this.message = new JTextArea();
		this.add(message);
		
		restartButton = new Button("Restart");
		restartButton.addActionListener(new ActionListener() {

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
		this.add(restartButton);

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
			
		this.message.setText("Player "+ winner +" win !\nGood Job !");
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48);
	}
}
