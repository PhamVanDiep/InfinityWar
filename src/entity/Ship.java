package entity;

import java.util.ArrayList;
import java.util.List;

import attack.Bullet;
import attack.CartridgeBelt;
import button.SHIP;
import entity.meteor.Meteor;
import entity.planet.Planet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import entity.items.Energy;

public class Ship {

	private int points;
	
	private boolean isLeftKeyPressed;
	private boolean isRigthKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	
	private int angle;
	
	private ImageView shipView;
	private Scene scene;
	
	private final int SHIP_RADIUS = 27;
	
	private int life;
	
	public final int LIFES =  5; 
	
	private SHIP ship;
	
	private double energyPercent;
	
	private double deltaX;
	private double deltaY;
	
	List<Bullet> bullets;
	
	private AnchorPane root;
	
	public static final double MAX_ENERGY_PERCENT = 100.0;
	
	private int numOfBullets;
	
	private CartridgeBelt cartridgeBelt;

	public Ship(SHIP choosenShip, AnchorPane root) {
		points = 0;
		life = LIFES;
		shipView = new ImageView(choosenShip.getURLShip());
		ship = choosenShip;
		setDeltaX(7);
		setDeltaY(5);
		setPosition();
		energyPercent = 100;
		numOfBullets = 200;
		bullets = new ArrayList<>();
		cartridgeBelt = new CartridgeBelt();
		setRoot(root);
		setStaticCartridgeBelt();
	}

	private void setStaticCartridgeBelt() {
		// TODO Auto-generated method stub
		root.getChildren().addAll(cartridgeBelt.getCartridgeBeltLabel(), cartridgeBelt.getNumberOfCartLabel(), cartridgeBelt.getCartridgeBeltImageView());
	}

	private void setPosition() {
		shipView.setLayoutX(570);
		shipView.setLayoutY(500);
	}
	
	public void moveShip() {
		if (isLeftKeyPressed && !isRigthKeyPressed) {
			if (angle > -30) {
				angle -= 5;
			}
			shipView.setRotate(angle);
			if (shipView.getLayoutX() > 0) {
				shipView.setLayoutX(shipView.getLayoutX() - deltaX);
			}
		}
		
		if (!isLeftKeyPressed && isRigthKeyPressed) {
			if (angle < 30) {
				angle += 5;
			}
			shipView.setRotate(angle);
			if (shipView.getLayoutX() < 1288) {
				shipView.setLayoutX(shipView.getLayoutX() + deltaX);
			}
		}
		
		if (!isLeftKeyPressed && !isRigthKeyPressed) {
			if (angle < 0) {
				angle +=5;
			}else if (angle > 0) {
				angle -=5;
			}
			
			shipView.setRotate(angle);
		}
		
		if (isLeftKeyPressed && isRigthKeyPressed) {
			if (angle < 0) {
				angle +=5;
			}else if (angle > 0) {
				angle -=5;
			}
			
			shipView.setRotate(angle);
		}
		
		if (isDownKeyPressed) {
			if (shipView.getLayoutY() < 600) {
				shipView.setLayoutY(shipView.getLayoutY() + deltaY);
			}
		}
		
		if (isUpKeyPressed) {
			if (shipView.getLayoutY() > 50 ) {
				shipView.setLayoutY(shipView.getLayoutY() - deltaY);
			}
		}
		
		moveBullets();
	}
	
	public void createKeyListener() {
		// TODO Auto-generated method stub
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}else if(arg0.getCode() == KeyCode.RIGHT) {
					isRigthKeyPressed = true;
				}else if (arg0.getCode() == KeyCode.DOWN) {
					isDownKeyPressed = true;
				}else if(arg0.getCode() == KeyCode.UP){
					isUpKeyPressed = true;
				}else if (arg0.getCode() == KeyCode.F) {
					if (Energy.numOfFuelBin > 0) {
						if(getEnergyPercent() >= 70) {
							setEnergyPercent(100);
						}else {
							addPercentEnergy();
						}					
						Energy.numOfFuelBin --;
					}
					
				}else if (arg0.getCode() == KeyCode.SPACE) {
					if(energyPercent >= 1 && numOfBullets > 0) {
						fire();
						numOfBullets --;
						subBulletEnergy();
					}
					
				}else if (arg0.getCode() == KeyCode.C) {
					if (cartridgeBelt.getNumberOfCart() > 0) {
						cartridgeBelt.changeNumberOfCart(-1);
						changeNumberOfBullets();
					}
				}			
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode() == KeyCode.LEFT) {
					subPercentEnergy();
					isLeftKeyPressed = false;
				}else if (arg0.getCode() == KeyCode.RIGHT) {
					subPercentEnergy();
					isRigthKeyPressed = false;
				} else if (arg0.getCode() == KeyCode.DOWN) {
					subPercentEnergy();
					isDownKeyPressed = false;
				}else if(arg0.getCode() == KeyCode.UP){
					subPercentEnergy();
					isUpKeyPressed = false;
				}
			}
		});
	}
	
	public void changeNumberOfBullets() {
		if (numOfBullets >= 50) {
			setNumberOfBullets(200);
		}else {
			setNumberOfBullets(getNumberOfBullets() + 150);
		}
	}

	protected void fire() {
		Bullet bullet = new Bullet();	
		bullet.setShip(this);
		bullet.setLayoutOfBullet();
		root.getChildren().add(bullet.getBullet());
		bullets.add(bullet);
	}

	private void moveBullets() {
		List<Bullet> bullets = getBullets();
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.getBullet().getLayoutY() < 0) {
				bullets.remove(i);
				bullet.getBullet().setLayoutX(-20);
				bullet.getBullet().setLayoutY(-50);
			} else {
				bullet.moveBullet();
			}
		}
	}
	
	public ImageView getShipView() {
		return shipView;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints() {
		this.points ++;
	}
	
	public boolean isCollision(Ship ship, Meteor meteor) {
		if (SHIP_RADIUS + meteor.METEOR_RADIUS > calDis(shipView.getLayoutX() + 49, 
				meteor.getMeteor().getLayoutX() + 20, shipView.getLayoutY() + 37, meteor.getMeteor().getLayoutY() + 20)) {
			return true;
		}
		return false;
	}
	
	private double calDis(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2)+ Math.pow(y1 - y2, 2));
	}
	
	public boolean isCollisionWithStar(Ship ship, Star meteor) {
		if (SHIP_RADIUS + meteor.STAR_RADIUS > calDis(shipView.getLayoutX() + 49, meteor.getMeteor().getLayoutX() + 15,
				shipView.getLayoutY() + 37, meteor.getMeteor().getLayoutY() + 15)) {
			return true;
		}else return false;
	}
	
	public boolean isCollisionRect(Ship ship, Planet planet) {
		Rectangle rectangle1 = new Rectangle(ship.getShipView().getLayoutX(), ship.getShipView().getLayoutY(), ship.getShipView().getImage().getWidth(), ship.getShipView().getImage().getHeight());
		Circle circle = new Circle(planet.getMeteor().getLayoutX() + 60, planet.getMeteor().getLayoutY() + 60, 60);
		
		if (rectangle1.intersects(circle.getBoundsInLocal())) {
			return true;
		}
		
		else return false;
	}
	
	public boolean isCollisionWithMeteor(Ship ship, ImageView meteor) {
		Rectangle rectangle1 = new Rectangle(ship.getShipView().getLayoutX(), ship.getShipView().getLayoutY(), ship.getShipView().getImage().getWidth(), ship.getShipView().getImage().getHeight());
		Rectangle rectangle2 = new Rectangle(meteor.getLayoutX(), meteor.getLayoutY(), meteor.getImage().getWidth(), meteor.getImage().getHeight());
		
		if (rectangle1.intersects(rectangle2.getBoundsInLocal())) {
			return true;
		}else {
			return false;
		}
	}
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void addLife() {
		this.life ++;
	}
	public void subLife() {
		if (life >= 0){
			this.life --;
			setEnergyPercent(100);
		}
	}
	
	public SHIP getChoosenShip() {
		return ship;
	}

	public double getEnergyPercent() {
		return Math.round(energyPercent*10)/ (10.0);
	}

	public void setEnergyPercent(double energyPercent) {
		this.energyPercent = energyPercent;
	}
	
	public void subPercentEnergy() {
		energyPercent -= 0.1;
	}
	
	public void addPercentEnergy() {
		energyPercent += 30.0;
	}
	
	public void subBulletEnergy() {
		energyPercent -= 1;
	}
	
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	public void setRoot(AnchorPane root) {
		this.root = root;
	}
	
	public void setNumberOfBullets(int numberOfBullets) {
		this.numOfBullets = numberOfBullets;
	}
	
	public int getNumberOfBullets() {
		return numOfBullets;
	}
	
	public CartridgeBelt getCartridgeBelt() {
		return cartridgeBelt;
	}
}
