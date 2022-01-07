package attack;

import enemy.Enemy;
import javafx.scene.image.ImageView;

public class Enemy2Bullet extends Bullet{
    private final String ENEMY_BULLET_2_IMAGE = "res/bullet/enemy2Bullet.png";
    private Enemy enemy;

    public Enemy2Bullet(){
        setBulletImageView(new ImageView(ENEMY_BULLET_2_IMAGE));
    }

    public void setEnemyShip(Enemy enemy){
        this.enemy = enemy;
    }

    public void setLayoutOfBullet(){
        getBullet().setLayoutX(enemy.getImageView().getLayoutX() + 45);
        getBullet().setLayoutY(enemy.getImageView().getLayoutY() + 70);
    }
}
