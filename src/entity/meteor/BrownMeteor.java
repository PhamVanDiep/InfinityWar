package entity.meteor;

import javafx.scene.image.ImageView;

public class BrownMeteor extends Meteor {
	
	private final String METEOR_BROWN_IMAGE = "res/meteor_brown.png";

	
	public BrownMeteor() {
		setMeteor(new ImageView(METEOR_BROWN_IMAGE));
	}
}
