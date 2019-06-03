package game.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.logic.Answer;
import game.logic.AnswerType;
import game.logic.DifficultyType;
import game.logic.GameLogic;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;

/**
 * Instanziert und initialisiert die GUI-Elemete der LoginSzene Mit der Hilfe
 * von API Worker-Thread realisiert implementierung von wechseln von den
 * Quizfragen und im-spiel realtime Timer
 * 
 * @author 유형욱
 * @version 0.5 Beta
 */

public class GameScene implements IScene{

	private int time;
	private Task<Void> task;
	private List<Button> allButton = new ArrayList<>();
	private GameLogic logic = new GameLogic();
	private String name;
	private SceneManager sceneManager;
	private Scene gameScene;
	private GridPane grid;
	private ArrayList<Answer> answers1 = new ArrayList<>();

	public GameScene(SceneManager sceneManager, String name) {
		this.sceneManager = sceneManager;
		this.name = name;
	}

	@Override
	public Scene init() {
		grid = new GridPane();
		gameScene = new Scene(grid);
		logic.setPlayerName(name);
		addElementsToGrid();
		return gameScene;
	}

	private void addElementsToGrid() {

		Button answer1 = new Button();
		allButton.add(answer1);
		Button answer2 = new Button();
		allButton.add(answer2);
		Button answer3 = new Button();
		allButton.add(answer3);
		Button answer4 = new Button();
		allButton.add(answer4);

		Button startButton = new Button("Start");
		allButton.add(startButton);

		Button highscore = new Button("Highscore");
		allButton.add(highscore);

		Label lives = new Label("");
		lives.setStyle("-fx-font-size: 20pt; -fx-text-fill: blue;");
		lives.setVisible(false);
		Label gameInfo = new Label("Choose your music directory");
		gameInfo.setStyle("-fx-font-size: 18pt; -fx-text-fill: yellow;");

		TextField setLives = new TextField();
		setLives.setPromptText("How much tries do you want?");
		TextField setTime = new TextField();
		setTime.setPromptText("How much time for the round?");
		setLives.setVisible(false);
		setTime.setVisible(false);

		Button chooseFile = new Button("Choose music directory");
		allButton.add(chooseFile);
		
		Button easy = new Button("Easy");
		Button normal = new Button("Normal");
		Button hard = new Button("Hard");
		allButton.add(easy);
		allButton.add(normal);
		allButton.add(hard);

		allButton.stream().filter((button) -> !button.equals(chooseFile) && !button.equals(highscore))
				.forEach((button) -> {
					button.setVisible(false);
				});

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(15.0);
		dropShadow.setOffsetX(8.0);
		dropShadow.setOffsetY(8.0);
		dropShadow.setColor(Color.STEELBLUE);

		allButton.stream()
				.filter((button) -> button.equals(chooseFile) || button.equals(highscore) || button.equals(answer1)
						|| button.equals(answer2) || button.equals(answer3) || button.equals(answer4)
						|| button.equals(startButton))
				.forEach((button) -> {
					button.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
					button.setEffect(dropShadow);
					button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							button.setStyle(
									"-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white; -fx-border-style: solid inside; -fx-border-color: gold");
						}
					});
					button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							button.setStyle(
									"-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
						}
					});
				});

		highscore.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.goToHighscore(sceneManager, name);
			}
		});

		/**
		 * 음악파일 불러오기
		 */
		chooseFile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				DirectoryChooser directoryChooser = new DirectoryChooser();
				configuringDirectoryChooser(directoryChooser);
				ArrayList<String> fileList1 = new ArrayList<>();
				File dir = directoryChooser.showDialog(null);
				if (dir != null) {
					File[] fList = dir.listFiles();
					for (File file : fList) {
						if (file.isFile() && file.getName().endsWith(".mp3")) {
							fileList1.add(file.getAbsolutePath());
						}
					}
					
					if (fileList1.size() > 4) {
						logic.initGameWithChosenDirecory(fileList1);
						startButton.setText("Start");
						startButton.setVisible(true);
						setLives.setVisible(true);
						setTime.setVisible(true);
						allButton.stream().filter((button) -> button.equals(chooseFile) || button.equals(highscore))
								.forEach((button) -> {
									button.setVisible(false);
								});
						allButton.stream()
								.filter((button) -> button.equals(normal) || button.equals(hard) || button.equals(easy))
								.forEach((button) -> {
									button.setStyle(
											"-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
									button.setVisible(true);
								});
						normal.setStyle("-fx-font-size: 14pt;-fx-background-color: blue; -fx-text-fill: white;");
						gameInfo.setText("");
					} else {
						gameInfo.setText("Something went wrong. Try again");
					}
				}
			}

			private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
				directoryChooser.setTitle("Select directorie with music for the MusicQuizGame! :)");
				directoryChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
			}
		});

		/**
		 * Start 버튼
		 */
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logic.gameStart();
				if (logic.isCheckIsSameSongMetaTwiceInAnswer() == false) {
					setLives.setVisible(false);
					setTime.setVisible(false);
					highscore.setVisible(false);
					startButton.setVisible(false);
					lives.setVisible(true);
					allButton.stream().filter((button) -> button.equals(answer1) || button.equals(answer2)
							|| button.equals(answer3) || button.equals(answer4)).forEach((button) -> {
								button.setVisible(true);
							});
					allButton.stream()
							.filter((button) -> button.equals(normal) || button.equals(hard) || button.equals(easy)
									|| button.equals(startButton) || button.equals(chooseFile)
									|| button.equals(highscore))
							.forEach((button) -> {
								button.setVisible(false);
							});

					if (!setLives.getText().isEmpty() && !setTime.getText().isEmpty()) {
						logic.setLives(Integer.parseInt(setLives.getText()));
						time = Integer.parseInt(setTime.getText());
					}
					if (setLives.getText().isEmpty() && !setTime.getText().isEmpty()) {
						logic.setLives(5);
						time = Integer.parseInt(setTime.getText());
					}
					if (!setLives.getText().isEmpty() && setTime.getText().isEmpty()) {
						logic.setLives(Integer.parseInt(setLives.getText()));
						time = 30;
					}
					if (setLives.getText().isEmpty() && setTime.getText().isEmpty()) {
						logic.setLives(5);
						time = 30;
					}
					
					playingGame(); // 게임시작
					
				} else {
					allButton.stream().filter((button) -> button.equals(normal) || button.equals(hard)
							|| button.equals(easy) || button.equals(startButton)).forEach((button) -> {
								button.setVisible(false);
							});
					allButton.stream().filter((button) -> button.equals(chooseFile) || button.equals(highscore))
							.forEach((button) -> {
								button.setVisible(true);
							});

					setLives.setVisible(false);
					setTime.setVisible(false);
					gameInfo.setText("Sorry. Too few different songs for the game. Choose another directory!");
				}
			}

			/**
			 * 게임진행 메서드
			 */
			private void playingGame() {
				if (logic.isCheckIsSameSongMetaTwiceInAnswer() == true) {
					lives.setVisible(false);
					allButton.stream().filter((button) -> button.equals(chooseFile) || button.equals(highscore))
							.forEach((button) -> {
								button.setVisible(true);
							});
					allButton.stream()
							.filter((button) -> button.equals(answer1) || button.equals(answer2)
									|| button.equals(answer3) || button.equals(answer4) || button.equals(startButton))
							.forEach((button) -> {
								button.setVisible(false);
							});
					logic.gameEnd();
					gameInfo.setText("Sorry. Too few different songs for the game. Choose another directory.");
				} else {
					lives.setText("Lives: " + logic.getLives() + " 		Score: " + logic.getScore());

					List<Button> answers = new ArrayList<>();
					allButton.stream().filter((button) -> button.equals(answer1) || button.equals(answer2)
							|| button.equals(answer3) || button.equals(answer4)).forEach((button) -> {
								button.setText("");
								button.setGraphic(null);
								answers.add(button);
							});
					Collections.shuffle(answers);
					answers1 = logic.getAnswers();

					for (Answer a : answers1) {
						if (a.getAnswerType() == AnswerType.ALBUM) {
							answers.get(0).setText(a.getAlbum());
							answers.get(0).setId(a.getRightAnswer() + "");
							answers.remove(0);
						} else if (a.getAnswerType() == AnswerType.ARTIST) {
							answers.get(0).setText(a.getArtist());
							answers.get(0).setId(a.getRightAnswer() + "");
							answers.remove(0);
						} else if (a.getAnswerType() == AnswerType.TITLE) {
							answers.get(0).setText(a.getTitle());
							answers.get(0).setId(a.getRightAnswer() + "");
							answers.remove(0);
						} else if (a.getAnswerType() == AnswerType.YEAR) {
							answers.get(0).setText(a.getYear() + "");
							answers.get(0).setId(a.getRightAnswer() + "");
							answers.remove(0);
						} else if (a.getAnswerType() == AnswerType.IMAGE) {
							ImageView imageView = new ImageView((Image) a.getImage());
							imageView.setFitWidth(90);
							imageView.setPreserveRatio(true);
							answers.get(0).setGraphic(imageView);
							answers.get(0).setId(a.getRightAnswer() + "");
							answers.remove(0);
						} else {
							throw new IllegalStateException("Expecting only Enum Types declared in AnswerType Class");
						}
					}

					task = new Task<Void>() {
						@Override
						public Void call() throws Exception {

							for (int i = time; i >= 0; i--) {
								updateMessage("You have " + i + " seconds left");
								Thread.sleep(1000);
							}
							return null;
						}
					};

					new Thread(task).start();
					task.messageProperty().addListener((obs, oldMessage, newMessage) -> gameInfo.setText(newMessage));

					task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
						@Override
						public void handle(WorkerStateEvent t) {
							t.consume();
							task.cancel();
							logic.roundlost();
							if (logic.isGameOver()) {
								getGuiToStartPosition();
								answers.clear();
							} else {
								playingGame();
							}
						}
					});

					EventHandler<ActionEvent> mbh = new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {

							Button btn = (Button) event.getSource();
							task.cancel();
							if (btn.getId().equals(1 + "")) {
								logic.gameStop(true);
								playingGame();
							} else {
								logic.roundlost();
								if (logic.isGameOver()) {
									getGuiToStartPosition();
									answers.clear();
								} else {
									playingGame();
								}
							}
						}
					};

					allButton.stream().filter((button) -> button.equals(answer1) || button.equals(answer2)
							|| button.equals(answer3) || button.equals(answer4)).forEach((button) -> {
								button.setOnAction(mbh);
							});
				}
			}

			/**
			 * Benachritigt den GameLogic über die Ende des Spieles Formt die
			 * GUI-Elemente entsprechend um, um ein neues Spiel dem Benutzer
			 * anzubieten
			 */

			private void getGuiToStartPosition() {
				task.cancel();
				logic.saveData();

				normal.setStyle("-fx-font-size: 14pt;-fx-background-color: blue; -fx-text-fill: white;");
				easy.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
				hard.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");

				gameInfo.setText("Game over");
				lives.setVisible(false);
				setLives.setVisible(true);
				setTime.setVisible(true);
				allButton.stream()
						.filter((button) -> button.equals(startButton) || button.equals(highscore)
								|| button.equals(chooseFile) || button.equals(normal) || button.equals(hard)
								|| button.equals(easy))
						.forEach((button) -> {
							button.setVisible(true);
						});
				startButton.setText("Try again?");
				allButton.stream().filter((button) -> button.equals(answer1) || button.equals(answer2)
						|| button.equals(answer3) || button.equals(answer4)).forEach((button) -> {
							button.setVisible(false);
						});
			}
		});

		easy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logic.setDifficultyType(DifficultyType.EASY);
				easy.setStyle("-fx-font-size: 14pt;-fx-background-color: blue; -fx-text-fill: white;");
				normal.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
				hard.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
			}
		});

		normal.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logic.setDifficultyType(DifficultyType.NORMAL);
				normal.setStyle("-fx-font-size: 14pt;-fx-background-color: blue; -fx-text-fill: white;");
				easy.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
				hard.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
			}
		});

		hard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logic.setDifficultyType(DifficultyType.HARD);
				hard.setStyle("-fx-font-size: 14pt;-fx-background-color: blue; -fx-text-fill: white;");
				easy.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
				normal.setStyle("-fx-font-size: 14pt;-fx-background-color: slateblue; -fx-text-fill: white;");
			}
		});

		VBox dificult = new VBox();
		dificult.setAlignment(Pos.CENTER);
		dificult.setSpacing(10.0);

		VBox answers = new VBox();
		answers.setAlignment(Pos.CENTER);
		answers.setSpacing(10.0);

		HBox root2 = new HBox();
		root2.setSpacing(10.0);
		root2.setAlignment(Pos.TOP_CENTER);

		HBox root = new HBox();
		root.setSpacing(10.0);
		root.setAlignment(Pos.TOP_CENTER);

		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(100);
		grid.setPadding(new Insets(20));

		VBox labi = new VBox();
		labi.setAlignment(Pos.CENTER);
		labi.setSpacing(10.0);

		VBox root1 = new VBox();
		root1.setAlignment(Pos.CENTER);
		root1.setSpacing(10.0);

		VBox saveb = new VBox();
		saveb.setAlignment(Pos.CENTER);
		saveb.setSpacing(10.0);

		easy.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		normal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hard.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		normal.setMinWidth(Control.USE_PREF_SIZE);

		Image loginImage = new Image("/game/img/gameImage.gif");
//		BackgroundImage backgroundloginImage = new BackgroundImage(loginImage, BackgroundRepeat.NO_REPEAT,
//				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		// BackgroundImage 화면에 꽉 차게 하는 코드 추가
		BackgroundImage backgroundloginImage = new BackgroundImage(loginImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		grid.setBackground(new Background(backgroundloginImage));

		root.getChildren().add(highscore);
		root1.getChildren().addAll(setLives, setTime);
		root2.getChildren().add(lives);
		dificult.getChildren().addAll(easy, normal, hard);
		labi.getChildren().add(gameInfo);
		answers.getChildren().addAll(answer1, answer2, answer3, answer4);
		saveb.getChildren().addAll(chooseFile, startButton);
		grid.add(root2, 0, 0);
		grid.add(root1, 0, 4);
		grid.add(root, 0, 0);
		grid.add(labi, 0, 1);
		grid.add(saveb, 0, 2);
		grid.add(dificult, 1, 2);
		grid.add(answers, 0, 3);
	}

}
