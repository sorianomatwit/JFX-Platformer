package platformer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Wall{
	private double x;
	private double y;
	private double hsp;
	private double spd = 4.5;
	private double mass;
	public Obstacle() {
		setX(500);
		setY(100);
		setHeight(50);
		setWidth(60);
		mass = 60;
		x = 500;
		y = 100;
	}
	
	public void show() {
		this.setStrokeWidth(2);
		this.setStroke(Color.BLACK);
		this.setFill(Color.WHITE);
		this.setX(x);
		this.setY(y);
	}
	
	public void UpdateObstacle(Wall floor) {
		y = floor.getY() - this.getHeight();
		hsp = -1 * spd/2;
		x += hsp;
	}
	
	public double getMass(){
		return mass;
	}
}
