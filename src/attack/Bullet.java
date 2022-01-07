package attack;

import enemy.Enemy;
import entity.Ship;
import entity.meteor.Meteor;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Bullet {
	
	private final String BULLET_IMAGE = "res/bullet/bullet.png";
	
	private ImageView bulletImageView;
	
	private double deltaY;

	private Ship ship;

	private double dx;
	private double dy;

	private Enemy enemy;

	public Bullet() {
		bulletImageView = new ImageView(BULLET_IMAGE);
		deltaY = -5;
	}
	
	public void setLayoutOfBullet() {
		bulletImageView.setLayoutX(ship.getShipView().getLayoutX() + 26);
		bulletImageView.setLayoutY(ship.getShipView().getLayoutY() + 10);
	}
	
	public void moveBullet() {
		bulletImageView.setLayoutX(bulletImageView.getLayoutX());
		bulletImageView.setLayoutY(bulletImageView.getLayoutY() + deltaY);
	}

	public void moveBulletToShip(){
		double cosAlpha = (ship.getShipView().getLayoutY() - enemy.getImageView().getLayoutY()) / getDistanceFromEnemyToShip();

		if (cosAlpha > 0 && ship.getShipView().getLayoutX() - enemy.getImageView().getLayoutX() > 0){
			setDxDy(Math.tan(Math.acos(cosAlpha))*5, 5);
		}else if (cosAlpha < 0 && ship.getShipView().getLayoutX() - enemy.getImageView().getLayoutX() > 0){
			setDxDy(Math.tan(Math.acos(cosAlpha))*5, -5);
		}else if (cosAlpha > 0 && ship.getShipView().getLayoutX() - enemy.getImageView().getLayoutX() < 0){
			setDxDy( -Math.tan(Math.acos(cosAlpha))*5, 5);
		}else if (cosAlpha < 0 && ship.getShipView().getLayoutX() - enemy.getImageView().getLayoutX() < 0){
			setDxDy(-Math.tan(Math.acos(cosAlpha))*5, -5);
		}
		bulletImageView.setLayoutX(getBullet().getLayoutX() + dx);
		bulletImageView.setLayoutY(getBullet().getLayoutY() + dy);
	}

	private double getDistanceFromEnemyToShip(){
		return Math.sqrt(Math.pow(enemy.getImageView().getLayoutX() - ship.getShipView().getLayoutX(), 2) +
				Math.pow(enemy.getImageView().getLayoutY() - ship.getShipView().getLayoutY(), 2));
	}
	public boolean isCollision(Meteor meteor) {
		Rectangle rectangle = new Rectangle(bulletImageView.getLayoutX(), bulletImageView.getLayoutY(), bulletImageView.getImage().getWidth(), bulletImageView.getImage().getHeight());
		Rectangle rectangle2 = new Rectangle(meteor.getMeteor().getLayoutX(), meteor.getMeteor().getLayoutY(), meteor.getMeteor().getImage().getWidth(), meteor.getMeteor().getImage().getHeight());
		
		if (rectangle.intersects(rectangle2.getBoundsInLocal())) {
			return true;
		}
		else return false;
	}

	public boolean isCollisionWithShip(){
		Rectangle rectangle = new Rectangle(getBullet().getLayoutX(), getBullet().getLayoutY(), getBullet().getImage().getWidth(), getBullet().getImage().getHeight());
		Rectangle rectangle1 = new Rectangle(ship.getShipView().getLayoutX(), ship.getShipView().getLayoutY(), ship.getShipView().getImage().getWidth(), ship.getShipView().getImage().getHeight());

		if (rectangle.intersects(rectangle1.getBoundsInLocal())){
			return  true;
		}else return false;
	}

	public boolean isCollisionWithBullet(Bullet bullet){
		Rectangle rectangle = new Rectangle(getBullet().getLayoutX(), getBullet().getLayoutY(), getBullet().getImage().getWidth(), getBullet().getImage().getHeight());
		Rectangle rectangle1 = new Rectangle(bullet.getBullet().getLayoutX(), bullet.getBullet().getLayoutY(), bullet.getBullet().getImage().getWidth(), bullet.getBullet().getImage().getHeight());

		if (rectangle.intersects(rectangle1.getBoundsInLocal())){
			return  true;
		}else return false;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	public ImageView getBullet() {
		return bulletImageView;
	}

	public void setBulletImageView(ImageView bulletImageView) {
		this.bulletImageView = bulletImageView;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public void setDxDy(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
}
