package platformer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Thing implements Mover {
	private double spd = 4.5;
	private double offset = 0;
	boolean isLow = false;

	public Obstacle() {
		super(500, -1, 30, 20);
		this.setStrokeWidth(2);
		this.setStroke(Color.BLACK);
	}

	public Obstacle(double offset, boolean isLow) {
		super(500, -1, 30, 20);
		this.setStrokeWidth(2);
		this.setStroke(Color.BLACK);
		this.offset = offset;
		this.isLow = isLow;
	}

	public void movement(int dir, Wall floor) {
		if (!isLow) {
			y = floor.getY() - this.getHeight() - 40;
		}
		hsp = dir * spd / 2;
		x += hsp;
		if (x < 0) {
			x = floor.getWidth() + (offset * 40);
		}
	}
}
