package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_9 extends Planet {
	private final String PLANET_STRING = "res/planets/9.png";
	
	public Planet_9() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
