package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_4 extends Planet {
	private final String PLANET_STRING = "res/planets/4.png";
	
	public Planet_4() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
