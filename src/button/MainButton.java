package button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.ViewManager;

public class MainButton extends Button {
	
	private final String BUTTON_PRESS_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/res/red_button_pressed.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/res/red_button.png');";
	
	private ButtonScene buttonScene;
	
	public MainButton(String title) {
		setText(title);
		setFont(Font.font("Snap ITC", 22 ));
		setPrefHeight(49);
		setPrefWidth(190);
		setStyle(BUTTON_FREE_STYLE);
		setTextFill(Color.DARKBLUE);
		initButtonListener();
		
		buttonScene = new ButtonScene();
		
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//ViewManager.temp.removeSubScene();
				ViewManager.temp = getButtonScene();
				if (ViewManager.temp != ViewManager.preTemp){
					ViewManager.preTemp.removeSubScene();
				}
				ViewManager.temp.moveSubScene();
			}
		});
	}

	private void initButtonListener() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}	
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					setButtonFreeStyle();
				}
			}	
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setEffect(null);
			}
		});
		
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESS_STYLE);
		setPrefHeight(40);
		setLayoutY(getLayoutY() + 3);
	}
	
	private void setButtonFreeStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 3);
	}
	
	public ButtonScene getButtonScene() {
		return buttonScene;
	}

	
}
