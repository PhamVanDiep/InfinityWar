package entity.meteor;

import javafx.scene.image.ImageView;

public class GreyMeteor extends Meteor {
	
	private final String METEOR_GREY_IMAGE = "res/meteor_grey.png";
	
	public GreyMeteor() {
		setMeteor(new ImageView(METEOR_GREY_IMAGE));
	}
}
