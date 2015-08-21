import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener{
	
	MainMenu menu;

	public InputHandler(Game game, MainMenu menu) {
		game.addKeyListener(this);
		this.menu = menu;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// Player 1 controls
		if(keyCode == e.VK_W){
			Game.getPlayer().goingUp = true;
		}

		if(keyCode == e.VK_S){
			Game.getPlayer().goingDown = true;
		}
		
		// Player 2 controls
		if(keyCode == e.VK_UP){
			Game.ai.goingUp = true;
		}

		if(keyCode == e.VK_DOWN){
			Game.ai.goingDown = true;
		}
		
		// Other controls
		if(keyCode == KeyEvent.VK_ESCAPE){
			Game.frame.setVisible(false);
			setVisible(true);	
			Game.gameRunning = false;
			
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// Player 1 controls
		if(keyCode == e.VK_W){
			Game.getPlayer().goingUp = false;
		}

		if(keyCode == e.VK_S){
			Game.getPlayer().goingDown = false;
		}

		// Player 2 controls
		if(keyCode == e.VK_UP){
			Game.ai.goingUp = false;
		}

		if(keyCode == e.VK_DOWN){
			Game.ai.goingDown = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
	
	public void setVisible(boolean bool){
		
		menu.setVisible(true);
	}
}
