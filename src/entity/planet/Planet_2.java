package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_2 extends Planet{
	private final String PLANET_STRING = "res/planets/2.png";
	
	public Planet_2() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
