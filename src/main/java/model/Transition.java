package model;

import javafx.animation.PathTransition;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * @author LoganDuck
 * @version 1
 */
public class Transition extends JFXPanel {
	private static final long serialVersionUID = 1L;

	public static Scene createScene() {		
		Circle circle = new Circle(10, Color.ROYALBLUE);
		Path path = new Path(); 
		MoveTo moveTo = new MoveTo(10, 10);
		LineTo lineTo = new LineTo(290, 10);
		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		  
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(1000));
		pathTransition.setNode(circle);
		pathTransition.setPath(path); 
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
		pathTransition.setCycleCount(PathTransition.INDEFINITE);
		pathTransition.setAutoReverse(true);
		pathTransition.play();
		
		Group root = new Group(circle);
		Scene scene = new Scene(root, 0, 0); //300, 70 
		
		return scene;
	}
}