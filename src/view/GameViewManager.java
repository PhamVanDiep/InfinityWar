package view;

import attack.BulletLabel;
import background.GameAudio;
import background.GameBackground;
import enemy.Enemy_1;
import enemy.Enemy_2;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import button.SHIP;
import button.SmallLabel;
import entity.Ship;
import entity.Star;
import entity.items.Energy;
import entity.items.LifeItem;
import entity.meteor.BrownMeteor;
import entity.meteor.GreyMeteor;
import entity.planet.*;
import score.Score;

import java.io.FileNotFoundException;

public class GameViewManager {
	private AnchorPane root;
	private Scene scene;
	private Stage stage;
	
	private static final int GAME_WIDTH = 1366;
	private static final int GAME_HEIGHT = 700;
	
	private Stage gameStage;
	private Ship ship;
	
	private GameBackground gameBackground;
	
	private AnimationTimer gameTimer;
	
	private final int numOfBrownMeteors = 7;
	private final int numOfGreyMeteors = 7;
	
	private BrownMeteor[] brownMeteors;
	private GreyMeteor[] greyMeteors;

	private Star[] stars;
	private final int numberOfStars = 10;
	private SmallLabel pointsLabel;
	public static ImageView [] playerLifes;
	
	private LifeItem lifeItem;
	
	private Planet [] planets;
	
	private Energy energy;
	
	private GameAudio gameAudio;
	
	private BulletLabel bulletLabel;

	private Enemy_1 enemy_1;

	private Enemy_2 enemy_2;

	private Score score;

	public GameViewManager() {
		initStage();
	}

	private  Label highestLabel;

	private Level level;

	private void initStage() {
		root = new AnchorPane();
		scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("COOLER-EMPIRE");
	}
	
	public void createNewGame(Stage menuStage, SHIP choosenShip) {
		this.gameStage = menuStage;
		this.gameStage.hide();
		initBackground();	
		initShip(choosenShip);
		initMeteor();
		ship.createKeyListener();
		createPoint();
		createGameLoop();
		createLife();
		createLifeItem();
		createPlanets();
		
		gameAudio = new GameAudio(root);
		energy = new Energy(root, ship);
		
		createBulletLabel();

		createEnemy_1();
		createEnemy_2();

		score = new Score();
		createHighestLabel();

		level = new Level(root);
		stage.show();
	}

	private void createHighestLabel() {
		highestLabel = new Label("HIGHEST");
		highestLabel.setFont(Font.font("Times New Romans", 25));
		highestLabel.setTextFill(Color.TRANSPARENT);
		highestLabel.setLayoutX(1100);
		highestLabel.setLayoutY(15);
		root.getChildren().add(highestLabel);
	}

	private void createEnemy_2() {
		enemy_2 = new Enemy_2(ship, root);
		root.getChildren().add(enemy_2.getImageView());
		root.getChildren().add(enemy_2.getEnergyOfEnemy_2Rect());
	}


	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				gameBackground.moveBackground();
				ship.moveShip();
				moveStarAndCheckAddPoint();
				moveMeteorAndCheckSubLife();
				moveLifeItemAndCheckLife();
				movePlanets();
				moveFuel();
				percentOfEnergy();
				bulletLabel.changeNumberOfBullets();
				moveCartridgeBelt();
				enemy_1.move();
				enemy_2.move();
				changeHighestScoreColor();
				level.changeLevel();
			}
		};

		gameTimer.start();
	}

	private void createEnemy_1() {
		enemy_1 = new Enemy_1(ship, root);
		root.getChildren().add(enemy_1.getImageView());
		root.getChildren().add(enemy_1.getEnergyOfEnemy_1Rect());
	}

	private void createBulletLabel() {
		// TODO Auto-generated method stub
		bulletLabel = new BulletLabel(ship);
		root.getChildren().add(bulletLabel.getBulletLabel());
		root.getChildren().add(bulletLabel.getLabel());
	}

	private void createPlanets() {
		planets  = new Planet[9];
		
		planets[0] = new Planet_1();
		planets[1] = new Planet_2();
		planets[2] = new Planet_3();
		planets[3] = new Planet_4();	
		planets[4] = new Planet_5();
		planets[5] = new Planet_6();
		planets[6] = new Planet_7();
		planets[7] = new Planet_8();
		planets[8] = new Planet_9();
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].setNewElementPosition(planets[i].getMeteor());
			planets[i].setDeltaY(5);
			root.getChildren().add(planets[i].getMeteor());
		}
	}

	private void createLifeItem() {
		lifeItem = new LifeItem(ship.getChoosenShip());
		lifeItem.setNewElementPosition(lifeItem.getMeteor());
		lifeItem.setDeltaY(3);
		root.getChildren().add(lifeItem.getMeteor());
	}

	private void initShip(SHIP choosenShip) {
		ship = new Ship(choosenShip, root);	
		ship.setScene(scene);
		root.getChildren().add(ship.getShipView());
	}


	private void moveCartridgeBelt() {
		ship.getCartridgeBelt().moveCartridgeBeltImage();
		if (ship.getCartridgeBelt().checkCollisionWithShip(ship)) {
			ship.getCartridgeBelt().playAudio();
			ship.getCartridgeBelt().setNewElementPosition(ship.getCartridgeBelt().getCartridgeBeltImageView());
			ship.getCartridgeBelt().changeNumberOfCart(1);
		}
	}

	private void movePlanets() {
		// TODO Auto-generated method stub
		for (int i = 0; i < planets.length; i++) {
			planets[i].moveMeteor();
			if (ship.isCollisionRect(ship, planets[i])) {
				planets[i].setNewElementPosition(planets[i].getMeteor());
				subLife();
				planets[i].playAudio();
			}
			for (int j = 0; j < ship.getBullets().size(); j++) {
				if (ship.getBullets().get(j).isCollision(planets[i])) {
					planets[i].setNewElementPosition(planets[i].getMeteor());
					ship.getBullets().get(j).getBullet().setLayoutX(-5);
					ship.getBullets().get(j).getBullet().setLayoutY(-30);
				}
			}
			planets[i].checkPassedAndRelocated();
		}
	}
	
	private void initBackground(){
		gameBackground = new GameBackground();
		root.getChildren().addAll(gameBackground.getGridPane1(), gameBackground.getGridPane2());
	}
	
	private void initMeteor() {
		
		stars = new Star[numberOfStars];
		for (int i = 0; i < stars.length; i++) {
			stars[i] = new Star();
			stars[i].setNewElementPosition(stars[i].getMeteor());
			stars[i].setDeltaY(5);
			stars[i].setRotate(5);
			root.getChildren().add(stars[i].getMeteor());
		}
		
		
		brownMeteors = new BrownMeteor[numOfBrownMeteors];
		
		for (int i = 0; i < brownMeteors.length; i++) {
			brownMeteors[i] = new BrownMeteor();
			brownMeteors[i].setNewElementPosition(brownMeteors[i].getMeteor());
			brownMeteors[i].setDeltaY(3);
			brownMeteors[i].setRotate(4);
			root.getChildren().add(brownMeteors[i].getMeteor());
		}
		
		greyMeteors = new GreyMeteor[numOfGreyMeteors];
		
		for (int i = 0; i < greyMeteors.length; i++) {
			greyMeteors[i] = new GreyMeteor();
			greyMeteors[i].setNewElementPosition(greyMeteors[i].getMeteor());
			greyMeteors[i].setDeltaY(7);
			greyMeteors[i].setRotate(4);
			root.getChildren().add(greyMeteors[i].getMeteor());
		}
	}
	
	private void moveStarAndCheckAddPoint(){
		for (int i = 0; i < stars.length; i++) {
			stars[i].moveMeteor();
			stars[i].checkPassedAndRelocated();
			
			if (ship.isCollisionWithStar(ship, stars[i])) {
				ship.addPoints();
				stars[i].setNewElementPosition(stars[i].getMeteor());
				pointsLabel.setText("SCORE: " + ship.getPoints());
				stars[i].playAudio();
				try{
					if (ship.getPoints() > Integer.parseInt(score.getScore())){
						System.out.println(1);
						score.setHighestScore("" +ship.getPoints());
					}
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}
			}
		}
			
	}

	private void changeHighestScoreColor(){
		try{
			if (ship.getPoints() >= Integer.parseInt(score.getScore())){
				if (GameBackground.distance % 10 == 0){
					highestLabel.setTextFill(Color.AQUA);
				}else if (GameBackground.distance % 10 == 5)
					highestLabel.setTextFill(Color.RED);
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	private void moveMeteorAndCheckSubLife() {
		for (int i = 0; i < brownMeteors.length; i++) {
			brownMeteors[i].moveMeteor();
			brownMeteors[i].checkPassedAndRelocated();
			if (ship.isCollision(ship, brownMeteors[i])) {
				subLife();
				brownMeteors[i].playAudioMeteor();
				brownMeteors[i].setNewElementPosition(brownMeteors[i].getMeteor());
			}
			for (int j = 0; j < ship.getBullets().size(); j++) {
				if (ship.getBullets().get(j).isCollision(brownMeteors[i])) {
					brownMeteors[i].setNewElementPosition(brownMeteors[i].getMeteor());
					ship.getBullets().get(j).getBullet().setLayoutX(-5);
					ship.getBullets().get(j).getBullet().setLayoutY(-30);
				}
			}
		}
		
		for (int i = 0; i < greyMeteors.length; i++) {
			greyMeteors[i].moveMeteor();
			greyMeteors[i].checkPassedAndRelocated();
			if (ship.isCollision(ship, greyMeteors[i])) {
				subLife();
				greyMeteors[i].playAudioMeteor();
				greyMeteors[i].setNewElementPosition(greyMeteors[i].getMeteor());
			}
			for (int j = 0; j < ship.getBullets().size(); j++) {
				if (ship.getBullets().get(j).isCollision(greyMeteors[i])) {
					greyMeteors[i].setNewElementPosition(greyMeteors[i].getMeteor());
					ship.getBullets().get(j).getBullet().setLayoutX(-5);
					ship.getBullets().get(j).getBullet().setLayoutY(-30);
				}
			}
		}
	}
	
	private void createPoint() {
		pointsLabel = new SmallLabel("SCORE: " + ship.getPoints());
		pointsLabel.setLayoutX(1236);
		pointsLabel.setLayoutY(5);
		root.getChildren().add(pointsLabel);
	}
	
	private void createLife() {
		playerLifes = new ImageView[ship.LIFES];
		
		for (int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView("res/heart.png");
			playerLifes[i].setLayoutX(1336-35*i);
			playerLifes[i].setLayoutY(60);
			root.getChildren().add(playerLifes[i]);
		}
	}

	private void checkDied() {
		if (ship.getLife() < 0) {
			stage.close();
			gameTimer.stop();
			gameStage.show();
			ViewManager.startMediaPlayer.play();
			gameAudio.getMediaPlayer().stop();
		}
	}
	
	private void moveLifeItemAndCheckLife() {
		lifeItem.moveMeteor();
		lifeItem.checkPassedAndRelocated();
		
		if (ship.isCollision(ship, lifeItem)) {
			if(ship.getLife() < ship.LIFES) {
				playerLifes[ship.getLife()].setLayoutX(1336 - 35*(ship.getLife()));
				root.getChildren().add(playerLifes[ship.getLife()]);
				ship.addLife();
			}
			lifeItem.setNewElementPosition(lifeItem.getMeteor());
			lifeItem.playAudio();
		}
	}

	private void moveFuel() {
		energy.moveFuelImageView();

		if (ship.isCollisionWithMeteor(ship, energy.getFuel())) {
			energy.setNewElementPosition(energy.getFuel());
			energy.addFuelBin();
			energy.playAudio();
		}
	}
	
	private void percentOfEnergy() {
		energy.setPercentOfEnergyString();
		energy.getRectangle().setWidth(ship.getEnergyPercent());
		energy.getRectangle().setFill(Color.AQUA);

		if (ship.getEnergyPercent() < 30.0) {
			energy.getRectangle().setFill(Color.RED);
		}
		if (ship.getEnergyPercent() <= 0.0) {
			ship.setDeltaX(0);
			ship.setDeltaY(0);
		}else {
			ship.setDeltaX(7);
			ship.setDeltaY(5);
		}
	}

	private void subLife(){
		ship.subLife();
		checkDied();
		if (ship.getLife() >= 0)
		root.getChildren().remove(playerLifes[ship.getLife()]);
	}
}
