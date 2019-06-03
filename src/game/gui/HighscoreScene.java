package game.gui;

import java.util.ArrayList;

import game.logic.GameLogic;
import game.logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Instanziert und initialisiert die GUI-Elemete der HighscoreSzene
 * 
 * @author Letychevskyy
 * @version 0.5 Beta
 */

public class HighscoreScene implements IScene{

	private GameLogic logic = new GameLogic();
	private String name;
	private SceneManager sceneManager;
	private Scene highscoreScene;
	private GridPane grid;
	private ObservableList<String> items = FXCollections.observableArrayList();

	public HighscoreScene(SceneManager sceneManager, String name) {
		this.sceneManager = sceneManager;
		this.name = name;
	}

	@Override
	public Scene init() {
		grid = new GridPane();
		highscoreScene = new Scene(grid);

		addElementsToGrid();
		return highscoreScene;
	}

	/**
	 * Die Instanzierung und -Initialisierung der GUI-Elemete der HighscoreSzene
	 */

	private void addElementsToGrid() {

		ArrayList<User> list = logic.loadData();
		for (User user : list) {
			addItem(user.getName() + "			" + user.getScore());
		}

		Button back = new Button("게임으로 돌아가기");
		back.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");

		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.goToGameScene(sceneManager, name);
			}
		});

		HBox root = new HBox();
		root.setSpacing(10.0);
		root.setAlignment(Pos.CENTER);

		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(100);
		grid.setPadding(new Insets(20));

		Image loginImage = new Image("/game/img/highscore3Image.gif");
		BackgroundImage backgroundloginImage = new BackgroundImage(loginImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		grid.setBackground(new Background(backgroundloginImage));

		ListView<String> lvList = new ListView<String>();
		lvList.setItems(items);

		root.getChildren().add(back);
		grid.add(root, 0, 0);
		grid.add(lvList, 0, 1);
	}

	/**
	 * Fuegt dem "Highscore"-List neue Werte hinzu
	 * 
	 * @param item
	 *            the item to add
	 */
	private void addItem(String item) {
		items.add(item);
	}

}
