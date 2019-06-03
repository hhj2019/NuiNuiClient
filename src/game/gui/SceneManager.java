package game.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Die Klasse dient zur Umschaltung der Szenen im Hauptfenster
 * 
 * @author 유형욱
 * @version 0.5 Beta
 */

public class SceneManager {

	private Stage stage;

	public SceneManager(Stage primaryStage) {
		this.stage = primaryStage;
		primaryStage.setFullScreen(true);
		stage.show();

	}

	public void goToLoginScene(SceneManager sceneManager) {
		LoginScene login = new LoginScene(sceneManager);
		Scene loginScene = login.init();
		stage.setScene(loginScene);
	}

	public void goToGameScene(SceneManager sceneManager, String name) {
		GameScene game = new GameScene(sceneManager, name);
		Scene gameScene = game.init();
		stage.setScene(gameScene);
	}

	public void goToHighscore(SceneManager sceneManager, String name) {
		HighscoreScene highscore = new HighscoreScene(sceneManager, name);
		Scene highscoreScene = highscore.init();
		stage.setScene(highscoreScene);
	}
}
