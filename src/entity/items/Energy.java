package entity.items;

import entity.Ship;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Energy extends Item {

	private ImageView fuel;
	private ImageView energy;
	private Label fuelBin;
	
	private double deltaY;
	
	public static int numOfFuelBin;
	
	private Label numOfFuelBinLabel;
	
	public static final String FUEL_BIN = "-fx-background-color: transparent; -fx-background-image: url('/res/items/fuel.png');";
	
	private final String FUEL_AUDIO = "/music/fuel.mp3";
	
	private Rectangle rectangle;
	private Ship ship;
	private Label percentOfEnergy;
	public Energy(AnchorPane root, Ship ship) {
		numOfFuelBin = 0;
		
		fuel = new ImageView("res/items/fuel.png");
		
		fuelBin = new Label();
		fuelBin.setPrefSize(fuel.getImage().getWidth(), fuel.getImage().getHeight());
		fuelBin.setStyle(FUEL_BIN);
		fuelBin.setLayoutX(60);
		fuelBin.setLayoutY(0);
		
		numOfFuelBinLabel = new Label();
		numOfFuelBinLabel.setPrefSize(30, 20);
		numOfFuelBinLabel.setText("" + numOfFuelBin);
		numOfFuelBinLabel.setTextFill(Color.WHITE);
		numOfFuelBinLabel.setLayoutX(60 + (fuel.getImage().getWidth() - 30)/2 + 10);
		numOfFuelBinLabel.setLayoutY(fuel.getImage().getHeight() - 10);
		
		energy = new ImageView("res/items/energy.png");
		energy.setLayoutX(120);
		energy.setLayoutY(0);
		root.getChildren().addAll(fuel, energy ,fuelBin, numOfFuelBinLabel);
		deltaY = 4;
		setNewElementPosition(fuel);
		
		this.ship = ship;
		
		rectangle = new Rectangle(175, 20, 100, 15);
		
		rectangle.setFill(Color.AQUA);
		
		percentOfEnergy = new Label("" + ship.getEnergyPercent());
		percentOfEnergy.setLayoutX(35 + 175);
		percentOfEnergy.setLayoutY(20);
		
		root.getChildren().add(rectangle);
		root.getChildren().add(percentOfEnergy);

	}
	
	public void moveFuelImageView() {
		fuel.setLayoutY(fuel.getLayoutY() + deltaY);
		if(fuel.getLayoutY() >= 700) {
			setNewElementPosition(fuel);
		}
		numOfFuelBinLabel.setText("" + numOfFuelBin);
	}
	
	public void setPercentOfEnergyString() {
		percentOfEnergy.setText("" + ship.getEnergyPercent());
		percentOfEnergy.setTextFill(Color.BLACK);
		if (ship.getEnergyPercent() < 35) {
			percentOfEnergy.setTextFill(Color.WHITE);
		}
	}
	
	public void playAudio() {
		Media media = new Media(getClass().getResource(FUEL_AUDIO).toExternalForm());
		MediaPlayer startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.play();
	}
	
	public ImageView getFuel() {
		return fuel;
	}
	
	public ImageView getEnergy() {
		return energy;
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public void addFuelBin() {
		numOfFuelBin ++;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

}
