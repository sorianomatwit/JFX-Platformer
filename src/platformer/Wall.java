package platformer;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle{
	
	public Wall() {
		super(0,1,20,20);
	}
	
	public void UpdateWall(Scene room) {
		this.setX(0);
		this.setY(room.getHeight() - this.getHeight());
		this.setWidth(room.getWidth());
		
	}
}
