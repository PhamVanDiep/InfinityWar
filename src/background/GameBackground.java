package background;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBackground {
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	
	private final String BACK_GROUNDIMAGE = "res/backgrounds/deep_blue.png" ;

	public static int distance;
	public static int distanceForLevel;

	public GameBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for(int i = 0; i < 30; i++) {
			ImageView imageView = new ImageView(BACK_GROUNDIMAGE);
			ImageView imageView2 = new ImageView(BACK_GROUNDIMAGE);
			GridPane.setConstraints(imageView, i%5, i/5);
			GridPane.setConstraints(imageView2, i%5, i/5);
			gridPane1.getChildren().add(imageView);
			gridPane2.getChildren().add(imageView2);
		}
		
		gridPane2.setLayoutY(-700);

		distance = 0;
		distanceForLevel = 0;
	}
	
	public GridPane getGridPane1() {
		return gridPane1;
	}
	
	public GridPane getGridPane2() {
		return gridPane2;
	}
	
	public void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
		
		if (gridPane1.getLayoutY() >= 700) {
			gridPane1.setLayoutY(-700);
		}
		
		if (gridPane2.getLayoutY() >= 700) {
			gridPane2.setLayoutY(-700);
		}
		distance += 1;
		if(distance > 600) {
			distance = 1;
		}

		distanceForLevel++;
		if(distanceForLevel > 1000){
			distanceForLevel = 1;
		}
	}
}
