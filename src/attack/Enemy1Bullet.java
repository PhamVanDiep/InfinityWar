package attack;

import enemy.Enemy;
import javafx.scene.image.ImageView;

public class Enemy1Bullet extends Bullet{

    private final String ENEMY_BULLET_1_IMAGE = "res/bullet/enemy_bullet.png";
    private Enemy enemy;

    public Enemy1Bullet(){
        setBulletImageView(new ImageView(ENEMY_BULLET_1_IMAGE));
    }

    public void setEnemyShip(Enemy enemy){
        this.enemy = enemy;
    }

    public void setLayoutOfBullet(){
        getBullet().setLayoutX(enemy.getImageView().getLayoutX() + 45);
        getBullet().setLayoutY(enemy.getImageView().getLayoutY() + 70);
    }

}
