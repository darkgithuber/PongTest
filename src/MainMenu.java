import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;


public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	int screenWidth = 400;
	int screenHeight = 160;
	
	int buttonWidth = 100;
	int buttonHeight = 40;
	
	JButton Play, Quit;
	JCheckBox twoPlayers;
	
	public MainMenu() {
		
		getContentPane().setLayout(null);
		
		// Calling methods
		addButtons();
		addActions(this);
		
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight);
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		twoPlayers.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth, buttonHeight);
		
		// Adding buttons
		getContentPane().add(Play);
		getContentPane().add(Quit);
		getContentPane().add(twoPlayers);
		
		// JFrame stuff
		pack();
		setVisible(true);
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}
	
	private void addButtons() {
		
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		twoPlayers = new JCheckBox("2 Players?");
	}
	
	private void addActions(MainMenu menu) {
		
		Play.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Game game = new Game(menu);
				
				if(twoPlayers.isSelected()){
					game.ai.isTwoPlayer = true;
				}else {
					game.ai.isTwoPlayer = false;
				}
				
				game.start();
				
			}
			
		}); // Play button 
		
		Quit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}); // Quit button
		
	}
}
