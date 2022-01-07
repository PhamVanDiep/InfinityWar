package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_7 extends Planet {
	private final String PLANET_STRING = "res/planets/7.png";
	
	public Planet_7() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
