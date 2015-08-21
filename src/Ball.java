import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	
	int x, y;
	int size = 16;
	static int speed = 2;
	int vx, vy;
	
	Rectangle boundingBox;
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(x, y, size, size);
	}

	public void tick(Game game){
		 
		boundingBox.setBounds(x, y, size, size);
		
		if(x <= 0){		
			vx = speed;
			game.p2score++;
		}else if(x + size >= game.getWidth()){
			vx = -speed;
			game.p1score++;
		}
		if(y <= 0){
			vy = speed;
		}else if(y + size >= game.getHeight()){
			vy = -speed;
		}
		
		
		paddleCollide(game);
		
		x += vx;
		y += vy;
		
		
	}
	
	private void paddleCollide(Game game){
		
		if(boundingBox.intersects(Game.getPlayerBoundingBox())){
			
			vx = speed;
		}else if(boundingBox.intersects(Game.getAIBoundingBox())){
			
			vx = -speed;
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, size, size);
	}
}
