package entity.planet;

import entity.meteor.Meteor;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Planet  extends Meteor{

	private final String PLANET_AUDIO = "/music/planet.mp3";
	
	public void playAudio() {
		Media media = new Media(getClass().getResource(PLANET_AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}
}
