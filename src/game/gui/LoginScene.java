package game.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Instanziert und initialisiert die GUI-Elemete der LoginSzene
 * 
 * @author Letychevskyy
 * @version 0.5 Beta
 */

public class LoginScene implements IScene{

	private String name;
	private SceneManager sceneManager;
	private Scene loginScene;
	private GridPane grid;

	public LoginScene(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	@Override
	public Scene init() {
		grid = new GridPane();
		loginScene = new Scene(grid);
		addElementsToGrid();
		return loginScene;
	}

	/**
	 * 첫번째 메인화면 시작버튼, 닉네임 설정창 디자인
	 */
	private void addElementsToGrid() {

		Button enterTheGameButton = new Button("Enter");
		enterTheGameButton.setStyle("-fx-font-size: 13pt;-fx-background-color: slateblue; -fx-text-fill: white;");
		enterTheGameButton.setPrefSize(200, 50);
		TextField nickNameField = new TextField();
		nickNameField.setPromptText("Enter your Nickname");
		nickNameField.setPrefSize(300, 50);

		enterTheGameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (nickNameField.getText().isEmpty() && nickNameField != null) {
					nickNameField.setPromptText("Enter your Nickname!!!");
				} else {
					name = nickNameField.getText();
					sceneManager.goToGameScene(sceneManager, name);
				}
			}
		});

		nickNameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					if (nickNameField.getText().isEmpty() && nickNameField != null) {
						nickNameField.setPromptText("Enter your Nickname!!!");
					} else {
						name = nickNameField.getText();
						sceneManager.goToGameScene(sceneManager, name);
					}
				}
			}
		});

		Image loginImage = new Image("/game/img/Login.jpg");
		BackgroundImage backgroundloginImage = new BackgroundImage(loginImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		grid.setBackground(new Background(backgroundloginImage));

		VBox root = new VBox();
		root.setSpacing(10.0);
		root.setAlignment(Pos.CENTER);

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(12);
		root.getChildren().addAll(enterTheGameButton, nickNameField);
		grid.add(root, 0, 1);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
