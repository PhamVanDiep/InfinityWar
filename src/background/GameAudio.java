package background;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GameAudio {
	
	private final String GAME_AUDIO = "/music/gameAudio.mp3";
	public static final String TURN_ON_VOL = "-fx-background-color: transparent; -fx-background-image: url('/res/turn_on_vol.png');";
	public static final String TURN_OFF_VOL = "-fx-background-color: transparent; -fx-background-image: url('res/turn_off_vol.png');";
	
	private Label volLabel;
	
	private Media media;
	private MediaPlayer mediaPlayer;
	
	private boolean isPlayed;
	
	public GameAudio(AnchorPane root) {
		volLabel = new Label();
		volLabel.setPrefSize(50, 50);
		volLabel.setStyle(TURN_ON_VOL);
		volLabel.setLayoutX(0);
		volLabel.setLayoutY(0);
		root.getChildren().add(volLabel);
		media = new Media(getClass().getResource(GAME_AUDIO).toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
		isPlayed = true;
		
		setAudio();
	}
	
	private void setAudio() {
		volLabel.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if(isPlayed == false) {
						mediaPlayer.play();
						volLabel.setStyle(TURN_ON_VOL);
						isPlayed = true;
					}
					else {
						mediaPlayer.pause();
						volLabel.setStyle(TURN_OFF_VOL);
						isPlayed = false;
					}
				}
				
			}
		});
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public Label getVolumeButton() {
		return volLabel;
	}
}
