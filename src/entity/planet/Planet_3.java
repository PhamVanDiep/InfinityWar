package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_3 extends Planet {
	private final String PLANET_STRING = "res/planets/3.png";
	
	public Planet_3() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
