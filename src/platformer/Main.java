package platformer;

import java.util.ArrayList;
import java.util.Random;

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
	ArrayList<Double> hitbox = new ArrayList<Double>();
	Random rand = new Random();
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Player p = new Player();
		Wall w = new Wall();
		ArrayList<Obstacle> lowL = new ArrayList<Obstacle>();
		ArrayList<Obstacle> highL = new ArrayList<Obstacle>();
		for(int i = 0;i < 3;i++) {
			double temp = rand.nextInt(10);
			if(temp < 6) {
				temp+=6;
			}
			lowL.add(new Obstacle(temp,true));
			highL.add(new Obstacle(temp,false));
		}
		Obstacle high = new Obstacle(40,false);
		Obstacle low = new Obstacle(0,true);
		Pane pane = new Pane();
		pane.getChildren().addAll(low,high, w, p);
		pane.getChildren().addAll(highL);
		pane.getChildren().addAll(lowL);

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
				if(event.getCode() == KeyCode.S && p.getVsp() == 0) {
					
					isCrouching = true;
					p.crouch(isCrouching); 
				}
				if(event.getCode() == KeyCode.W && p.collision(p.getX(), p.getY()+1, w) && !isCrouching) {
					p.setVsp((double) -10);
				} 
				
//				if(event.getCode() == KeyCode.D) {
//					p.movement(1, w);
//				}
//				if(event.getCode() == KeyCode.A) {
//					p.movement(-1, w);
//				}
			}

		};
		EventHandler<KeyEvent> KeyboardCheckRelease = new EventHandler<KeyEvent>() {

			// anykeypress will run this method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.S && isCrouching) {
					isCrouching = false;
					p.crouch(isCrouching);
				}
			}

		};

		EventHandler<ActionEvent> step = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				//wall
				w.UpdateWall(scene);
				//obstacles
				low.movement(-1,w);
				low.display();
				high.movement(-1,w);
				high.display();
				
				//player
				p.display();
				p.WorldCollide(w);
				pane.setOnKeyPressed(keyboardCheckPressed);
				pane.setOnKeyReleased(KeyboardCheckRelease);
				if(p.collision(low.getX(), low.getY(), low.getWidth(),low.getHeight()) && !isCrouching ||
						(isCrouching && low.collision(p.getX() - p.getWidth()/2 - 5,
								p.getY() + p.getHeight()/4 + 4,p.getHeight(),p.getWidth()))) {
					p.setFill(Color.DARKBLUE);
				}
				if(p.collision(high.getX(), high.getY(), high.getWidth(),high.getHeight()) && !isCrouching ||
						(isCrouching && low.collision(p.getX() - p.getWidth()/2 - 5,
								p.getY() + p.getHeight()/4 + 4,p.getHeight(),p.getWidth()))) {
					p.setFill(Color.DARKBLUE);
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
