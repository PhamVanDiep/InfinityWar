package enemy;

import attack.Enemy1Bullet;
import background.GameBackground;
import entity.Ship;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class Enemy_1 extends Enemy{

    private final String ENEMY_1_IMAGE = "res/enemy/Spaceship1.png";

    List <Enemy1Bullet> enemyBullets;

    private Ship ship;

    private int energyOfEnemy_1;

    private Rectangle energyOfEnemy_1Rect;

    private AnchorPane root;

    public static int deltaAttack = 15;

    public Enemy_1(Ship ship, AnchorPane root){
        setImageView(new ImageView(ENEMY_1_IMAGE));
        setNewPosition();
        enemyBullets = new ArrayList<>();
        this.ship = ship;
        energyOfEnemy_1 = 100;
        energyOfEnemy_1Rect = new Rectangle();
        energyOfEnemy_1Rect.setFill(Color.RED);
        energyOfEnemy_1Rect.setWidth(energyOfEnemy_1);
        energyOfEnemy_1Rect.setHeight(15);
        setRoot(root);
    }

    @Override
    public void move() {
        if (getImageView().getLayoutY() < 150){
            getImageView().setLayoutY(getImageView().getLayoutY() + 1 );
            if (deltaAttack <= 10)
            getImageView().setLayoutX(ship.getShipView().getLayoutX());
            energyOfEnemy_1Rect.setLayoutX(getImageView().getLayoutX());
            energyOfEnemy_1Rect.setLayoutY(getImageView().getLayoutY() - 20 + 1);
            energyOfEnemy_1Rect.setWidth(energyOfEnemy_1);
        }
        if (getImageView().getLayoutY() > 0){
            attack();
        }
        moveBulletOfEnemy();

        if (isCollisionWithShip(ship)){
            ship.subLife();
            root.getChildren().remove(GameViewManager.playerLifes[ship.getLife()]);
            setNewPosition();
            setEnergyOfEnemy_1RectPosition();
        }

        for (int i = 0; i < ship.getBullets().size(); i++){
            if (isCollisionWithBullet(ship.getBullets().get(i))){
                subEnergyOfEnemy_1();
                ship.getBullets().get(i).getBullet().setLayoutY(-20);
            }
        }
    }

    private void moveBulletOfEnemy() {
        List<Enemy1Bullet> bullets = enemyBullets;

        for (int i = 0; i < bullets.size(); i++) {
            Enemy1Bullet bullet = bullets.get(i);
            if (bullet.getBullet().getLayoutY() > 700 ) {
                bullets.remove(i);
                bullet.getBullet().setLayoutX(0);
                bullet.getBullet().setLayoutY(750);
            } else {
                if (energyOfEnemy_1 <= 0){
                    bullet.getBullet().setLayoutX(0);
                    bullet.getBullet().setLayoutY(750);
                }else
                bullet.moveBullet();
            }
            if (bullet.isCollisionWithShip()){
                if (ship.getEnergyPercent() > 10 )
                ship.setEnergyPercent(ship.getEnergyPercent() - 10);
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

        }
    }

    @Override
    public void attack() {
        if (deltaAttack >= 2){
            if (GameBackground.distance % deltaAttack == 1 ){
                Enemy1Bullet enemyBullet = new Enemy1Bullet();
                enemyBullet.setEnemyShip(this);
                enemyBullet.setLayoutOfBullet();
                enemyBullet.setDeltaY(5);
                enemyBullet.setShip(ship);
                root.getChildren().add(enemyBullet.getBullet());
                enemyBullets.add(enemyBullet);
            }
        }
    }

    public void subEnergyOfEnemy_1() {
        this.energyOfEnemy_1 -= 20;
        if (energyOfEnemy_1 <= 0){
            setNewPosition();
            energyOfEnemy_1 = 100;
            setEnergyOfEnemy_1RectPosition();
        }
        energyOfEnemy_1Rect.setWidth(energyOfEnemy_1);
    }

    public void setNewPosition(){
        getImageView().setLayoutX(500);
        getImageView().setLayoutY(-2000);
    }

    public void setEnergyOfEnemy_1RectPosition(){
        energyOfEnemy_1Rect.setLayoutX(getImageView().getLayoutX());
        energyOfEnemy_1Rect.setLayoutY(getImageView().getLayoutY() - 20 + 1);
        energyOfEnemy_1Rect.setWidth(energyOfEnemy_1);
    }
    public Rectangle getEnergyOfEnemy_1Rect() {
        return energyOfEnemy_1Rect;
    }

    public void setRoot(AnchorPane root){
        this.root = root;
    }
}
