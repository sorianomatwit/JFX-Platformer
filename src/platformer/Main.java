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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Main extends Application {

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
		EventHandler<KeyEvent> keyboardCheckPressed = new EventHandler<KeyEvent>() {

			// anykeypress will run this method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.W && p.collision(p.getX(), p.getY()+1, w)) {
					p.setVsp((double) -15);
				} else if(event.getCode() == KeyCode.S) {
					p.crouch(true);
				}
				if(event.getCode() == KeyCode.D) {
					p.movement(1, w);
				} else if(event.getCode() == KeyCode.A) {
					p.movement(-1, w);
				}
			}

		};
		EventHandler<KeyEvent> KeyboardCheckRelease = new EventHandler<KeyEvent>() {

			// anykeypress will run this method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.S) {
					p.crouch(false);
				}
			}

		};

		EventHandler<ActionEvent> step = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				pane.setOnKeyPressed(keyboardCheckPressed);
				//pane.setOnKeyReleased(KeyboardCheckRelease);
				w.UpdateWall(scene);
				//obstacles
				o.UpdateObstacle(w);
				o.show();
				
				//player
				p.display();
				p.WorldCollide(w);
				if(p.collision(o.getX(), o.getY(), o.getMass())) {
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
