package ui;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Model;

public class StartView extends JPanel {
	
	private final int BUTTON_SIZE_WIDTH = 100;
	private final int BUTTON_SIZE_HEIGHT = 30;
	public static final Graphics2D DEFAULT_GRAPHICS = (Graphics2D) new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB).getGraphics();
	
	
	private Model model;
	private final Button newGameButton;
	private final Button exitButton;
	private final JLabel message;
	

	public StartView(Model _model, JPanel cardPanel){
		this.setLayout(null);
		this.model = _model;
		this.setPreferredSize(new Dimension(model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48));
		
		/* Ajout du message de fin de partie */
		this.message = new JLabel();
		this.message.setText("BOMBERMAN !");
		this.add(message);
		FontRenderContext frc= DEFAULT_GRAPHICS.getFontRenderContext();
		int width=(int)this.message.getFont().getStringBounds(message.getText(), frc).getWidth();
		int height=(int)this.message.getFont().getStringBounds(message.getText(), frc).getHeight();
		Rectangle rectangle = new Rectangle((624-width)/2,(624-height)/2,width,height);
		message.setBounds(rectangle);
		
		/* Ajout du bouton restart */
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 624, 624);
	}
}
