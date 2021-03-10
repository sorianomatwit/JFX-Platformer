package platformer;

public interface Mover {
	public boolean collision(double x, double y, double massx, double massy);
	public void movement(int dir, Wall floor);
}
