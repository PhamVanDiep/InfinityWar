package attack;

import java.util.Random;

import entity.Ship;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CartridgeBelt{
	
	private Label cartridgeBeltLabel;
	
	private int numberOfCart;
	
	private Label numberOfCartLabel;
	
	private final String cartridgeBeltLabelStyle = "-fx-background-color: transparent; -fx-background-image: url('/res/bullet/cartridge_belt.png');";
	private final String cartridgeBeltLabelImage = "res/bullet/cartridge_belt.png";
	private ImageView cartridgeBeltImageView;
	
	private Random random;
	
	private double deltaY;

	private final String AUDIO = "/music/fuel.mp3";
	
	public CartridgeBelt() {
		deltaY = 6;
		
		numberOfCart = 0;
		
		random = new Random();
		
		Image image = new Image(cartridgeBeltLabelImage);
		cartridgeBeltLabel = new Label();
		cartridgeBeltLabel.setStyle(cartridgeBeltLabelStyle);
		cartridgeBeltLabel.setPrefSize(image.getWidth(), image.getHeight());
		cartridgeBeltLabel.setLayoutX(350);
		cartridgeBeltLabel.setLayoutY(5);
		
		numberOfCartLabel = new Label("" + numberOfCart);
		numberOfCartLabel.setTextFill(Color.WHITE);
		numberOfCartLabel.setLayoutX(350 + image.getWidth() / 2 - 10);
		numberOfCartLabel.setLayoutY(image.getHeight() + 7);
		
		cartridgeBeltImageView = new ImageView(cartridgeBeltLabelImage);
		setNewElementPosition(cartridgeBeltImageView);

	}
	public void playAudio() {
		Media media = new Media(getClass().getResource(AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}

	public void changeNumberOfCart(int i) {
		numberOfCart += i;
		numberOfCartLabel.setText("" + numberOfCart);
	}
	
	public void moveCartridgeBeltImage() {
		checkPassedAndRelocated();
		cartridgeBeltImageView.setLayoutY(cartridgeBeltImageView.getLayoutY() + deltaY);
	}
	
	public void setNewElementPosition(ImageView imageView) {
		imageView.setLayoutX(random.nextInt(1350));
		imageView.setLayoutY(-( random.nextInt(5000) + 600));
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public boolean checkCollisionWithShip(Ship ship) {
		Rectangle rectangle = new Rectangle(ship.getShipView().getLayoutX(), ship.getShipView().getLayoutY(), ship.getShipView().getImage().getWidth(), ship.getShipView().getImage().getHeight());
		Rectangle rectangle2 = new Rectangle(cartridgeBeltImageView.getLayoutX(), cartridgeBeltImageView.getLayoutY(), cartridgeBeltImageView.getImage().getWidth(), cartridgeBeltImageView.getImage().getHeight());
		
		if (rectangle.intersects(rectangle2.getBoundsInLocal())) {
			return true;
		}
		
		else return false;
	}
	
	public void checkPassedAndRelocated() {
		if(cartridgeBeltImageView.getLayoutY() >= 700) {
			setNewElementPosition(cartridgeBeltImageView);
		}
	}
	
	public ImageView getCartridgeBeltImageView() {
		return cartridgeBeltImageView;
	}
	
	public Label getCartridgeBeltLabel() {
		return cartridgeBeltLabel;
	}
	
	public Label getNumberOfCartLabel() {
		return numberOfCartLabel;
	}
	
	public int getNumberOfCart() {
		return numberOfCart;
	}
	
}
