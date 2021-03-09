package platformer;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{
	protected double x = 150;
	protected double y = 100;
	protected double vsp;
	protected double hsp;
	protected double walkspd = 6;
	private double grv = 0.5;
	private double massx = 30;
	private double massy = 50;
	
	public Player() {
		super(150,100,30,50);
	}
	
	public void display() {
		this.setStrokeWidth(2);
		this.setStroke(Color.PURPLE);
		this.setFill(Color.LIME);
		this.setX(x);
		this.setY(y);
		this.setWidth(massx);
		this.setHeight(massy);
	
	}
	
	public boolean collision(double x,double y,double mass) {
		if(this.x <= x+mass && this.x+this.massx >= x && this.y >= y  && this.y + this.massy <= y + mass) {
			return true;
		}
		return false;
	}
	public boolean collision(double x,double y,Wall floor) {
		if(x <= floor.getX() + floor.getWidth() && x+this.massx >= floor.getX() && 
				y <= floor.getY() + floor.getHeight() && y + this.massy >= floor.getY()) {
			return true;
		}
		return false;
	}
	public void WorldCollide(Wall floor) {
		vsp += grv;
		while(collision(x,y+vsp,floor)) {
			while(!collision(x,y+sign(vsp),floor)) {
				y += sign(vsp);
			}
			vsp = 0;
		}
		y += vsp;
	}
	public void movement(int dir, Wall floor) {
		hsp = dir * walkspd;
		while(collision(x+hsp,y,floor)) {
			while(!collision(x+sign(hsp),y,floor)) {
				x += sign(vsp);
			}
			hsp = 0;
		}
		x += hsp;
	}

	protected int sign(double vsp2) {
		if(vsp > 0) {
			return 1;
		} else if(vsp < 0) {
			return -1;
		}
		return 0;
	}
	public void setVsp(Double vsp){
		this.vsp = vsp;
	}
	public void setHsp(Double hsp){
		this.hsp = hsp;
	}
	
	public void crouch(boolean isCrouch) {
		if(isCrouch) {
			this.setRotate(90);
		} else {
			this.setRotate(0);
		}
	}
}
