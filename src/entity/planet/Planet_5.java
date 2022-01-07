package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_5 extends Planet {
	private final String PLANET_STRING = "res/planets/5.png";
	
	public Planet_5() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
