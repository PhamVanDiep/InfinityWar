package view;


import background.GameBackground;
import enemy.Enemy_1;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Level {

    private Label levelLabel;
    private int level;

    public Level(AnchorPane root){
        level = 1;
        levelLabel = new Label();
        levelLabel.setText("Level " + level);
        levelLabel.setFont(Font.font("Times New Romans", 25));
        levelLabel.setTextFill(Color.AQUAMARINE);
        levelLabel.setLayoutX(600);
        levelLabel.setLayoutY(10);
        root.getChildren().add(levelLabel);
    }

    public void changeLevel(){
        if (GameBackground.distanceForLevel > 999 ){
            setLevel(getLevel() + 1);
            levelLabel.setText ("Level " + level);
            Enemy_1.deltaAttack --;
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
