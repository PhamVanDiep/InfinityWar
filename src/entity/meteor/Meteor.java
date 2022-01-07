package entity.meteor;

import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Meteor {
	
	private double deltaY;
	private double rotate;
	
	private ImageView meteor;
	
	private Random random;

	public final int METEOR_RADIUS = 20;
	
	private final String METEOR_AUDIO = "/music/bang-2.mp3";
	
	public Meteor() {
		
		meteor = new ImageView();
		random = new Random();
	}
	
	public void moveMeteor() {
		meteor.setLayoutY(meteor.getLayoutY() + deltaY);
		meteor.setRotate(meteor.getRotate() + rotate);
	}
	
	public void checkPassedAndRelocated() {
		if(meteor.getLayoutY() >= 700) {
			setNewElementPosition(meteor);
		}
	}
	
	public void setNewElementPosition(ImageView imageView) {
		imageView.setLayoutX(random.nextInt(1350));
		imageView.setLayoutY(-(random.nextInt(3200) + 600));
	}
	
	public void playAudioMeteor() {
		Media media = new Media(getClass().getResource(METEOR_AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}
	
	public void setMeteor(ImageView imageView) {
		this.meteor = imageView;
	}
	
	public ImageView getMeteor() {
		return meteor;
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public void setRotate(double rotate) {
		this.rotate = rotate;
	}
}
