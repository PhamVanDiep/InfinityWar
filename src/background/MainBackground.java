package background;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import view.ViewManager;

public class MainBackground {
	
	private Image mainBackgroundImage ;
	private BackgroundImage backgroundImage;
	
	public MainBackground() {
		mainBackgroundImage = new Image("res/backgrounds/deep-blue.jfif", ViewManager.WIDHT, ViewManager.HEIGHT, false, true);
		backgroundImage = new BackgroundImage(mainBackgroundImage, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
	}
	
	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}
}
