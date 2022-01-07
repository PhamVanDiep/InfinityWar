package button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallLabel extends Label {
	
	public SmallLabel(String text) {
		setPrefWidth(130);
		setPrefHeight(50);
		
		BackgroundImage background = new BackgroundImage(new Image("res/red_small_panel.png", 130, 50, false, true), 
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(background));
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		setText(text);
		setFont(Font.font("Times New Romans", 20));
	}
}
