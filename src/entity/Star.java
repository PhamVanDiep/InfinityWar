package entity;

import entity.meteor.Meteor;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Star extends Meteor {
	
	private final String GOLD_STAR_IMAGE = "res/items/star_gold.png";
	private final String STAR_AUDIO = "/music/star.wav";

	public final int STAR_RADIUS = 12;
	
	public Star() {
		setMeteor(new ImageView(GOLD_STAR_IMAGE));
	}
	
	public void playAudio() {
		Media media = new Media(getClass().getResource(STAR_AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}
}
