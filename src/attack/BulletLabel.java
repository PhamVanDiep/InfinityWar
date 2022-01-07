package attack;

import entity.Ship;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class BulletLabel {
	
	private ImageView bulletLabel;
	
	private final String bulletItemImage = "res/bullet/bulletItem.png";
	
	private Ship ship;
	
	private Label label;
	
	public BulletLabel(Ship ship) {
		this.ship = ship;
		
		bulletLabel = new ImageView(bulletItemImage);
		bulletLabel.setLayoutX(300);
		bulletLabel.setLayoutY(0);
		
		label = new Label("" + ship.getNumberOfBullets());
		label.setTextFill(Color.WHITE);
		label.setLayoutY(bulletLabel.getImage().getHeight());
		label.setLayoutX(300);
	}
	
	public void changeNumberOfBullets() {
		label.setText("" + ship.getNumberOfBullets());
	}
	
	public Label getLabel() {
		return label;
	}
	
	public ImageView getBulletLabel() {
		return bulletLabel;
	}
}
