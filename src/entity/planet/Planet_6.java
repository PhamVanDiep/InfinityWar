package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_6 extends Planet {
	private final String PLANET_STRING = "res/planets/6.png";
	
	public Planet_6() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
