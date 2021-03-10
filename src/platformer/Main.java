package platformer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Main extends Application {
	boolean isCrouching = false;
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Player p = new Player();
		Wall w = new Wall();
		Obstacle o = new Obstacle();
		Pane pane = new Pane();
		pane.getChildren().addAll(o, w, p);

		Scene scene = new Scene(pane, 600, 600);
		
		arg0.setTitle("Platformer");
		arg0.setScene(scene);
		arg0.show();
		pane.requestFocus();
		w.UpdateWall(scene);
		EventHandler<KeyEvent> keyboardCheckPressed = new EventHandler<KeyEvent>() {

			// anykeypress will run this method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.S) {
					isCrouching = true;
					p.crouch(isCrouching); 
				}
				if(event.getCode() == KeyCode.W && p.collision(p.getX(), p.getY()+1, w) && !isCrouching) {
					p.setVsp((double) -15);
				} 
				
				if(event.getCode() == KeyCode.D) {
					p.movement(1, w);
				}
				if(event.getCode() == KeyCode.A) {
					p.movement(-1, w);
				}
			}

		};
		EventHandler<KeyEvent> KeyboardCheckRelease = new EventHandler<KeyEvent>() {

			// anykeypress will run this method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.S) {
					isCrouching = false;
					p.crouch(isCrouching);
				}
			}

		};

		EventHandler<ActionEvent> step = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				//obstacles
				o.movement(-1,w);
				o.display();
				if(p.collision(o.getX(), o.getY(), o.getWidth(),o.getHeight()) && !isCrouching) {
					p.setFill(Color.DARKBLUE);
				}
				  
				//player
				p.display();
				p.WorldCollide(w);
				pane.setOnKeyPressed(keyboardCheckPressed);
				pane.setOnKeyReleased(KeyboardCheckRelease);
				 
				if(isCrouching && o.collision(p.getX() - p.getWidth()/2 - 5, p.getY() + p.getHeight()/4 + 4, p.getHeight(), p.getWidth())) {
					p.setFill(Color.HOTPINK);
				}
			}
		};
		// tick
		Timeline s = new Timeline(new KeyFrame(Duration.millis(16.7), step));
		s.setCycleCount(Timeline.INDEFINITE);
		s.play();

		arg0.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				s.stop();
			}
		});

	}

}
