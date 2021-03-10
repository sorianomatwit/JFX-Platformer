package platformer;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Thing implements Mover{

	protected double walkspd = 6;
	private double grv = 0.5;

	public Player() {
		super(150, 100, 30, 70);
		color = Color.LIME;
		this.setStrokeWidth(2);
		this.setStroke(Color.PURPLE);
	}

	public boolean collision(double x, double y, Wall floor) {
		if (this.getRotate() == 90) {
			if (x   <= floor.getX() + floor.getWidth() && x + this.massx >= floor.getX()
					&& y- (this.massy/4 + 2)  <= floor.getY() + floor.getHeight() 
					&& y - (this.massy/4 + 2) + this.massy >= floor.getY()) {
				return true;
			}
		} else {
			if (x <= floor.getX() + floor.getWidth() && x + this.massx >= floor.getX()
					&& y <= floor.getY() + floor.getHeight() && y + this.massy >= floor.getY()) {
				return true;
			}
		}
		return false;
	}

	public void WorldCollide(Wall floor) {
		vsp += grv;
		while (collision(x, y + vsp, floor)) {
			while (!collision(x, y + sign(vsp), floor)) {
				y += sign(vsp);
			}
			vsp = 0;
		}
		y += vsp;
	}

	
	public void movement(int dir, Wall floor) {
		hsp = dir * walkspd;
		while (collision(x + hsp, y, floor)) {
			while (!collision(x + sign(hsp), y, floor)) {
				x += sign(vsp);
			}
			hsp = 0;
		}
		x += hsp;
	}
	public void crouch(boolean isCrouch) {
		double  tempy = getY();
		
		if(isCrouch) {
			setRotate(90);
		} else {
			y = (tempy - massy/2 + (massy - 10)/4);
			setRotate(0);
		}
	}
}
