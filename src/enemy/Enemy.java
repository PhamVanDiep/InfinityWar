package enemy;

import attack.Bullet;
import entity.Ship;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Enemy {

    private ImageView imageView;

    public Enemy(){
        imageView = new ImageView();
    }
    public abstract void move();

    public abstract void attack();

    public boolean isCollisionWithShip(Ship ship){
        Rectangle rectangle = new Rectangle(ship.getShipView().getLayoutX(), ship.getShipView().getLayoutY(),
                ship.getShipView().getImage().getWidth(), ship.getShipView().getImage().getHeight());
        Rectangle rectangle1 = new Rectangle(imageView.getLayoutX(), imageView.getLayoutY(),
                imageView.getImage().getWidth(), imageView.getImage().getHeight());
        if (rectangle.intersects(rectangle1.getBoundsInLocal())){
            return true;
        }else return false;
    }

    public void setNewPosition(int a, int b){
        imageView.setLayoutX(a);
        imageView.setLayoutY(b);
    }

    public boolean isCollisionWithBullet(Bullet bullet){
        Rectangle rectangle = new Rectangle(imageView.getLayoutX(), imageView.getLayoutY(),
                imageView.getImage().getWidth(), imageView.getImage().getHeight());
        Rectangle rectangle1 = new Rectangle(bullet.getBullet().getLayoutX(), bullet.getBullet().getLayoutY(),
                bullet.getBullet().getImage().getWidth(), bullet.getBullet().getImage().getHeight());

        if (rectangle.intersects(rectangle1.getBoundsInLocal())){
            return  true;
        }else return false;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
