package entity.items;

import button.SHIP;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LifeItem extends Item {
		
	public static final int LIFE_ITEM_RADIUS = 0;
	private final String SUB_SHIP_AUDIO = "/music/subShip.wav";
	
	public LifeItem(SHIP choosenShip) {
		setMeteor(new ImageView(choosenShip.getUrlLife()));
	}
	
	public void playAudio() {
		Media media = new Media(getClass().getResource(SUB_SHIP_AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}
}
