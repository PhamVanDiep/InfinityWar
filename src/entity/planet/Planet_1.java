package entity.planet;

import javafx.scene.image.ImageView;

public class Planet_1 extends Planet{
	
	private final String PLANET_STRING = "res/planets/1.png";
	
	public Planet_1() {
		setMeteor(new ImageView(PLANET_STRING));
	}
}
