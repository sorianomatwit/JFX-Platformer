package platformer;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Thing extends Rectangle {
	protected double x;
	protected double y;
	protected double vsp;
	protected double hsp;
	protected double massx;
	protected double massy;
	protected Color color = Color.WHITE;

	protected Thing(double a, double b, double c, double d) {
		super(a, b, c, d);
		x = a;
		y = b;
		massx = c;
		massy = d;
		setFill(color);
	}

	protected Thing(double[] a) {
		super(a[0], a[1], a[2], a[3]);
		x = a[0];
		y = a[1];
		massx = a[2];
		massy = a[3];
	}

	protected void display() {
		this.setFill(color);
		this.setX(x);
		this.setY(y);
		this.setWidth(massx);
		this.setHeight(massy);
	}

	protected int sign(double i) {
		if (i > 0) {
			return 1;
		} else if (i < 0) {
			return -1;
		}
		return 0;
	}

	public void setVsp(Double vsp) {
		this.vsp = vsp;
	}

	public void setHsp(Double hsp) {
		this.hsp = hsp;
	}

	public double getVsp() {
		return vsp;
	}

	public double getHsp() {
		return hsp;
	}

	public boolean collision(double x, double y, double massx, double massy) {
		if (this.x <= x + massx && this.x + this.massx >= x 
				&& this.y + this.massy >= y && this.y <= y + massy) {
			return true;
		}
		return false;
	}
	public boolean collision(ArrayList<Double> p) {
		if (this.x <= p.get(0) + p.get(2) && this.x + this.massx >= p.get(0) 
				&& this.y + this.massy >= p.get(1) && this.y <=p.get(1) + p.get(3)) {
			return true;
		}
		return false;
	}
}
