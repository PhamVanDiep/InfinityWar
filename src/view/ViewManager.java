package view;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import background.MainBackground;
import button.ButtonScene;
import button.MainButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import button.ShipPicker;
import button.SHIP;
import score.Score;

public class ViewManager {
	
	public static final int HEIGHT =600;
	public static final int WIDHT = 800;
	
	private AnchorPane root;
	private Scene scene;
	private Stage mainStage;
	
	private MainBackground mainBackground;
	
	private Label gameName;
	
	private MainButton choosenShip;
	private MainButton scoreButton;
	private MainButton helpButton;
	private MainButton exitButton;
	
	private MainButton startMainButton;
	
	public static ButtonScene temp;
	public static ButtonScene preTemp;
	
	private List<ShipPicker> ships;
	
	private SHIP choosedShip;
	
	private GameViewManager gameViewManager;
	
	private final String START_AUDIO = "/music/file1.mp3";
	private Media media;
	
	public static MediaPlayer startMediaPlayer;
	private Score score;

	public ViewManager() throws URISyntaxException {

		score = new Score();
		root = new AnchorPane();
		scene = new Scene(root, WIDHT, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(scene);
		
		mainBackground = new MainBackground();
		
		gameName = new Label("COOLER-SPACESHIP");
		gameName.setFont(Font.font("Snap ITC", 60));
		gameName.setTextFill(Color.YELLOW);
		gameName.setLayoutX(25);
		gameName.setLayoutY(20);
		
		choosenShip = new MainButton("MENU");
		scoreButton = new MainButton("SCORE");
		helpButton = new MainButton("HELP");
		exitButton = new MainButton("EXIT");
		
		temp = new ButtonScene();
		preTemp = new ButtonScene();

		media = new Media(getClass().getResource(START_AUDIO).toExternalForm());
		startMediaPlayer = new MediaPlayer(media);
		startMediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startMediaPlayer.seek(Duration.ZERO);
			}
		});
		startMediaPlayer.play();
		
		setButtonPosition();
		setSubSceneContent() ;
		setHighestScoreContent();
		addToStage();

	}

	private void setHighestScoreContent() {

		Label nameLabel = new Label();
		nameLabel.setTextFill(Color.BLACK);
		nameLabel.setText("HIGHEST SCORE");
		nameLabel.setFont(Font.font("Showcard Gothic", 20 ));
		nameLabel.setLayoutX(120);
		nameLabel.setLayoutY(20);

		Label scoreLabel = new Label();
		scoreLabel.setFont(Font.font("Times New Romans", 50 ));
		try{
			scoreLabel.setText(score.getScore());
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		scoreLabel.setTextFill(Color.BLACK);
		scoreLabel.setLayoutX(180);
		scoreLabel.setLayoutY(100);

		scoreButton.getButtonScene().getPane().getChildren().addAll(nameLabel, scoreLabel);
	}

	private void addToStage() {
		root.setBackground(new Background(mainBackground.getBackgroundImage()));;
		root.getChildren().addAll(choosenShip, scoreButton, helpButton, exitButton);
		root.getChildren().add(gameName);
		root.getChildren().addAll(choosenShip.getButtonScene(), scoreButton.getButtonScene(), helpButton.getButtonScene());
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();
			}
		});
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void setButtonPosition() {
		choosenShip.setLayoutX(100);
		choosenShip.setLayoutY(160);
		
		scoreButton.setLayoutX(100);
		scoreButton.setLayoutY(260);
		
		helpButton.setLayoutX(100);
		helpButton.setLayoutY(360);
		
		exitButton.setLayoutX(100);
		exitButton.setLayoutY(460);
	}
	
	private void setSubSceneContent() {
		
		HBox box = new HBox();
		box.setSpacing(20);
		
		ships = new ArrayList<>();
		for (SHIP ship : SHIP.values()) {
			ShipPicker shiptoPick = new ShipPicker(ship);
			box.getChildren().add(shiptoPick);
			ships.add(shiptoPick);
			shiptoPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					for(ShipPicker shipPicker : ships) {
						shipPicker.setIsCircleChoosen(false);
					}
					shiptoPick.setIsCircleChoosen(true);
					choosedShip = shiptoPick.getShip();
				}
				
			});
		}
		
		box.setLayoutX(20);
		box.setLayoutY(70);
		
		Label label = new Label("CHOOSE YOUR SPACESHIP");
		label.setFont(Font.font("Showcard Gothic", 20));
		label.setLayoutX(70);
		label.setLayoutY(20);
		
		startMainButton = new MainButton("START");
		startMainButton.setLayoutX(100);
		startMainButton.setLayoutY(230);
		startMainButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameViewManager = new GameViewManager();
				gameViewManager.createNewGame(mainStage, choosedShip);
				startMediaPlayer.stop();
			}
		});
		
		choosenShip.getButtonScene().getPane().getChildren().addAll(box, label, startMainButton);
	}
}
