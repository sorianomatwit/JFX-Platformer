package platformer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Thing implements Mover{
	private double spd = 4.5;
	public Obstacle() {
		super(500,100,50,60);
		this.setStrokeWidth(2);
		this.setStroke(Color.BLACK);
		
	}
	
	public void movement(int dir, Wall floor) {
		y = floor.getY() - this.getHeight();
		hsp = dir * spd/2;
		if(x > floor.getWidth()/2) x += hsp;
		
	}	
}
