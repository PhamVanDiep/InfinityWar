package button;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;
import view.ViewManager;

public class ButtonScene extends SubScene {

	private final static String BACKGROUND_IMAGE = "res/backgrounds/yellow-background.jpg";
	
	private boolean isHidden;
	
	public ButtonScene() {
		super(new AnchorPane(), 400, 300);
		BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 400, 300, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane)this.getRoot();
		root2.setBackground(new Background(backgroundImage));
		isHidden = true;
		setLayoutX(1366);
		setLayoutY(200);
	}
	
	public void moveSubScene() {

		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if (isHidden) {
			transition.setToX(-1016);
			isHidden = false;
			ViewManager.preTemp = this;
		}else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
	
	public void removeSubScene() {

		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		transition.setToX(0);
		isHidden = true;
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane)this.getRoot();
	}

}
