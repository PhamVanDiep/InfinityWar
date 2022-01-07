package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_8 extends Planet{
	private final String PLANET_STRING = "res/planets/8.png";
	
	public Planet_8() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
