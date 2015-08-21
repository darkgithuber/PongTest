import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	InputHandler IH;

	static JFrame frame; 
	public final int WIDTH = 400;
	public final int HEIGHT = 300; //WIDTH / 9 * 16
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Pong";

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	static boolean gameRunning = false; 

	int p1score, p2score;
	
	Thread thread;

	public void run() {

		while(gameRunning) {
			tick();
			render();

			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public synchronized void start() {
		gameRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);	
	}

	public Game(MainMenu menu) {

		frame = new JFrame();
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		
		
		IH = new InputHandler(this, menu);

		setPlayer(new PlayerPaddle(10, 60));

		ai = new AIPaddle(getWidth() - 25, 60);
		ball = new Ball(getWidth() / 2, getHeight() /2);
	}

	public void tick() {
		getPlayer().tick(this);
		ai.tick(this);
		ball.tick(this);
	}
	
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		
		g.drawString("Player 1: " + p1score, 5, 15);
		g.drawString("Player 2: " + p2score, getWidth() - 65, 15);
		 
		

		getPlayer().render(g);
		ai.render(g);
		ball.render(g);

		g.dispose();
		bs.show();
	}

	public static PlayerPaddle getPlayer() {
		return player;
	}

	public static void setPlayer(PlayerPaddle player) {
		Game.player = player;
	}

	public static Rectangle getPlayerBoundingBox() {

		return Game.player.boundingBox;
	}

	public static Rectangle getAIBoundingBox() {

		return Game.ai.boundingBox;
	}
}



