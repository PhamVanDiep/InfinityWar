package enemy;

import attack.Enemy2Bullet;
import background.GameBackground;
import entity.Ship;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class Enemy_2 extends Enemy{

    private final String ENEMY_2_IMAGE = "res/enemy/Spaceship2.png";

    List<Enemy2Bullet> enemyBullets;

    private Ship ship;

    private int energyOfEnemy_2;

    private Rectangle energyOfEnemy_2Rect;

    private AnchorPane root;

    public Enemy_2(Ship ship, AnchorPane root){
        setImageView(new ImageView(ENEMY_2_IMAGE));
        setNewPosition();
        enemyBullets = new ArrayList<>();
        this.ship = ship;
        energyOfEnemy_2 = 100;
        energyOfEnemy_2Rect = new Rectangle();
        energyOfEnemy_2Rect.setFill(Color.RED);
        energyOfEnemy_2Rect.setWidth(energyOfEnemy_2);
        energyOfEnemy_2Rect.setHeight(15);
        setRoot(root);
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    @Override
    public void move() {
        if (getImageView().getLayoutY() < 50){
            getImageView().setLayoutY(getImageView().getLayoutY() + 1 );
            energyOfEnemy_2Rect.setLayoutX(getImageView().getLayoutX());
            energyOfEnemy_2Rect.setLayoutY(getImageView().getLayoutY() - 20 + 1);
            energyOfEnemy_2Rect.setWidth(energyOfEnemy_2);
        }
        if (getImageView().getLayoutY() > 0){
            attack();
        }
        moveBulletOfEnemy();
        if (isCollisionWithShip(ship)){
            ship.subLife();
            root.getChildren().remove(GameViewManager.playerLifes[ship.getLife()]);
            setNewPosition();
            setEnergyOfEnemy_2RectPosition();
        }

        for (int i = 0; i < ship.getBullets().size(); i++){
            if (isCollisionWithBullet(ship.getBullets().get(i))){
                subEnergyOfEnemy_2();
                ship.getBullets().get(i).getBullet().setLayoutY(-20);
            }
        }
    }

    private void setEnergyOfEnemy_2RectPosition() {
        energyOfEnemy_2Rect.setLayoutX(getImageView().getLayoutX());
        energyOfEnemy_2Rect.setLayoutY(getImageView().getLayoutY() - 20 + 1);
        energyOfEnemy_2Rect.setWidth(energyOfEnemy_2);
    }

    @Override
    public void attack() {
        if (GameBackground.distance % 28  == 1 ){
            Enemy2Bullet enemyBullet = new Enemy2Bullet();
            enemyBullet.setEnemyShip(this);
            enemyBullet.setLayoutOfBullet();
            enemyBullet.setDeltaY(5);
            enemyBullet.setShip(ship);
            enemyBullet.setEnemy(this);
            root.getChildren().add(enemyBullet.getBullet());
            enemyBullets.add(enemyBullet);
        }
    }

    private void moveBulletOfEnemy() {
        List<Enemy2Bullet> bullets = enemyBullets;

        for (int i = 0; i < bullets.size(); i++) {
            Enemy2Bullet bullet = bullets.get(i);
            if (bullet.getBullet().getLayoutY() > 700 ) {
                bullets.remove(i);
                bullet.getBullet().setLayoutX(0);
                bullet.getBullet().setLayoutY(750);
            } else {
                if (energyOfEnemy_2 <= 0){
                    bullet.getBullet().setLayoutX(0);
                    bullet.getBullet().setLayoutY(750);
                }else
                bullet.moveBulletToShip();
            }
            if (bullet.isCollisionWithShip()){
                if (ship.getEnergyPercent() > 20 )
                    ship.setEnergyPercent(ship.getEnergyPercent() - 20);
                else{
                    ship.setEnergyPercent(0);
                    ship.subLife();
                    root.getChildren().remove(GameViewManager.playerLifes[ship.getLife()]);
                }
                bullet.getBullet().setLayoutX(0);
                bullet.getBullet().setLayoutY(750);
            }
            for (int j = 0; j < ship.getBullets().size(); j++) {
                if (bullet.isCollisionWithBullet(ship.getBullets().get(j))){
                    bullet.getBullet().setLayoutX(0);
                    bullet.getBullet().setLayoutY(750);
                    ship.getBullets().get(j).getBullet().setLayoutX(0);
                    ship.getBullets().get(j).getBullet().setLayoutY(-50);
                }
            }
            if (energyOfEnemy_2 <= 0){
                bullet.getBullet().setLayoutX(0);
                bullet.getBullet().setLayoutY(750);
            }
        }
    }

    public void subEnergyOfEnemy_2() {
        this.energyOfEnemy_2 -= 10;
        if (energyOfEnemy_2 <= 0){
            setNewPosition();
            energyOfEnemy_2 = 100;
            setEnergyOfEnemy_2RectPosition();
        }
        energyOfEnemy_2Rect.setWidth(energyOfEnemy_2);
    }

    public void setNewPosition(){
        getImageView().setLayoutX(800);
        getImageView().setLayoutY(-8000);
    }

    public Rectangle getEnergyOfEnemy_2Rect() {
        return energyOfEnemy_2Rect;
    }
}
