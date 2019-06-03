package game.logic;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import game.data.LoadData;
import game.data.SaveData;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * @author 유형욱
 * @version 0.5 Beta
 */

public class GameLogic implements IGameLogic{

	private DifficultyType difficultyType = DifficultyType.NORMAL;
	private int random;
	private boolean checkIsSameSongMetaTwiceInAnswer;
	private boolean gameOver;
	private int score;
	private int lives;
	private MediaPlayer player;
	private ArrayList<String> fileList = new ArrayList<>();
	private Song song;
	private Answer answer;
	private Image im = new Image("/game/img/temp.jpg");
	private LoadData loader = new LoadData();
	private SaveData saver = new SaveData();
	private User user = new User("", score);
	private ArrayList<AnswerType> categories = new ArrayList<>();
	private ArrayList<Song> roundSongs = new ArrayList<>();
	private ArrayList<Song> songs = new ArrayList<>();
	private ArrayList<Answer> answers = new ArrayList<>();
	private TestMetadata testMetadata = new TestMetadata();

	/**
	 * Nimmt die Pfaden der Lieder aus der fileList1, um damit die relevante
	 * Metadaten aus denen zu extrahieren und in den Songobjekte für das Spiel
	 * einzupacken
	 *
	 * @param fileList1
	 *            fileList1 Liste mit den Musicfilesabsolutpfaden
	 */
	public void initGameWithChosenDirecory(ArrayList<String> fileList1) {
		if (!categories.isEmpty()) {
			categories.clear();
		}
		categories.addAll(Arrays.asList(AnswerType.values()));
		setCheckIsSameSongMetaTwiceInAnswer(false);
		difficultyType = DifficultyType.NORMAL;
		songs.clear();
		fileList = fileList1;
		for (int i = 0; i < fileList.size(); i++) {
			File file = new File(fileList.get(i));
			URI uri = file.toURI();
			String mediaUrl = uri.toASCIIString();
			Media initMedia = new Media(mediaUrl.toString());
			MediaPlayer initPlayer = new MediaPlayer(initMedia);
			initPlayer.setOnReady(new Runnable() {
				public void run() {
					ObservableMap<String, Object> metadata = initMedia.getMetadata();
					song = new Song("", im, "", 0, "");
					for (String key : metadata.keySet()) {
						if (key == "title") {
							song.setTitle(metadata.get(key).toString());
						}
						if (key == "image") {
							song.setImage((Image) metadata.get(key));
						}
						if (key == "artist") {
							song.setArtist(metadata.get(key).toString());
						}
						if (key == "year") {
							song.setYear((int) metadata.get(key));
						}
						if (key == "album") {
							song.setAlbum(metadata.get(key).toString());
						}
					}
					songs.add(song);
				}
			});
		}
	}

	/**
	 * <p>
	 * Starten des Spiels
	 * 
	 * Die Lsiten mit Songs, mit den Pfadnamen von Songs und mit den
	 * Categorienamen werden an die Testklasse uebergeben, um dort geprueft zu
	 * werden, ob die .mp3 Daten die im Benutzerordner gesammelt wudren, fuer
	 * das Spiel geeignet sind. Falls ja, werden die 4 ausgewaelte Songs weiter
	 * gegeben und wenn nicht, wird fuer die GUI benachritigt, dass das spiel
	 * mit den Daten unmoeglich ist.
	 */
	
	@Override
	public void gameStart() {
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getTitle().isEmpty() && songs.get(i).getImage() == im && songs.get(i).getAlbum().isEmpty()
					&& songs.get(i).getArtist().isEmpty() && songs.get(i).getYear() == 0) {
				songs.remove(i);
				fileList.remove(i);
			}
		}
		categories.clear();
		categories.addAll(Arrays.asList(AnswerType.values()));

		if (getDifficultyType() == DifficultyType.EASY) {
			testMetadata.testEasy(fileList, songs);
		} else {
			testMetadata.test(fileList, categories, songs);
		}
		if (getDifficultyType() == DifficultyType.EASY && testMetadata.isCheck()) {
			ArrayList<Integer> rand = new ArrayList<>();
			rand = testMetadata.getResRand();
			random = 0;
			setCheckIsSameSongMetaTwiceInAnswer(false);
			playerStart(rand, random);
		} else if (getDifficultyType() == DifficultyType.NORMAL && testMetadata.isCheck()) {
			setCheckIsSameSongMetaTwiceInAnswer(false);
			ArrayList<Integer> rand = new ArrayList<>();
			rand = testMetadata.getResRand();
			random = testMetadata.getResRandom();
			playerStart(rand, random);
		} else if (getDifficultyType() == DifficultyType.HARD && testMetadata.isCheck()) {
			setCheckIsSameSongMetaTwiceInAnswer(false);
			ArrayList<Integer> rand = new ArrayList<>();
			rand = testMetadata.getResRand();
			random = testMetadata.getResRandom();
			playerStart(rand, random);
		} else {
			if (testMetadata.isCheck() == false) {
				setCheckIsSameSongMetaTwiceInAnswer(true);
			}
		}

	}

	/**
	 * Initialisiert und startet den MediaPlayer / Löst die Methode aus, die
	 * Songsobjekte in die Antwortobjekte verpackt
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @param random
	 *            random eine Zufallszahl, die hilft beim verpacken von Songs in
	 *            die Anworten AnwortTyp zu ermiteln
	 */

	public void playerStart(ArrayList<Integer> rand, int random) {
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		File file = new File(fileList.get(rand.get(0)));
		URI uri = file.toURI();
		String mediaUrl = uri.toASCIIString();
		Media media = new Media(mediaUrl.toString());
		player = new MediaPlayer(media);
		setAnswersForGame(roundSongs, random);
		player.setRate(1.0);
		if (getDifficultyType() == DifficultyType.HARD) {
			player.setRate(2.0);
		}
		player.setAutoPlay(true);
	}

	/**
	 * Methode, die einzelne Songobjekte in der Antwortobjekte verpakt und eine
	 * Liste mit den Antworten bildet
	 * 
	 * @param roundSongs2
	 *            rounsongs Liste mit Songobjekten
	 * @param random
	 *            random eine Zufallszahl, die hilft nach bestimmten AnwortTyp
	 *            das Antortobjekt aus dem Songobjekt zu machen
	 */

	private void setAnswersForGame(ArrayList<Song> roundSongs2, int random) {
		if (getDifficultyType() == DifficultyType.EASY) {
			random = 0;
		}
		if (categories.get(random) == AnswerType.TITLE) {
			answer = new Answer(null, null, null, 0, null, 0, null);
			answer.setTitle(roundSongs2.get(0).getTitle());
			answer.setAnswerType(AnswerType.TITLE);
			answer.setRightAnswer(1);
			answers.add(answer);
			roundSongs2.remove(0);
			for (Song s : roundSongs2) {
				answer = new Answer(null, null, null, 0, null, 0, null);
				answer.setTitle(s.getTitle());
				answer.setAnswerType(AnswerType.TITLE);
				answers.add(answer);
			}
		} else if (categories.get(random) == AnswerType.IMAGE) {
			answer = new Answer(null, null, null, 0, null, 0, null);
			answer.setImage(roundSongs2.get(0).getImage());
			answer.setAnswerType(AnswerType.IMAGE);
			answer.setRightAnswer(1);
			answers.add(answer);
			roundSongs2.remove(0);
			for (Song s : roundSongs2) {
				answer = new Answer(null, null, null, 0, null, 0, null);
				answer.setImage(s.getImage());
				answer.setAnswerType(AnswerType.IMAGE);
				answers.add(answer);
			}
		} else if (categories.get(random) == AnswerType.ARTIST) {
			answer = new Answer(null, null, null, 0, null, 0, null);
			answer.setArtist(roundSongs2.get(0).getArtist());
			answer.setAnswerType(AnswerType.ARTIST);
			answer.setRightAnswer(1);
			answers.add(answer);
			roundSongs2.remove(0);
			for (Song s : roundSongs2) {
				answer = new Answer(null, null, null, 0, null, 0, null);
				answer.setArtist(s.getArtist());
				answer.setAnswerType(AnswerType.ARTIST);
				answers.add(answer);
			}
		} else if (categories.get(random) == AnswerType.YEAR) {
			answer = new Answer(null, null, null, 0, null, 0, null);
			answer.setYear(roundSongs2.get(0).getYear());
			answer.setAnswerType(AnswerType.YEAR);
			answer.setRightAnswer(1);
			answers.add(answer);
			roundSongs2.remove(0);
			for (Song s : roundSongs2) {
				answer = new Answer(null, null, null, 0, null, 0, null);
				answer.setYear(s.getYear());
				answer.setAnswerType(AnswerType.YEAR);
				answers.add(answer);
			}
		} else if (categories.get(random) == AnswerType.ALBUM) {
			answer = new Answer(null, null, null, 0, null, 0, null);
			answer.setAlbum(roundSongs2.get(0).getAlbum());
			answer.setAnswerType(AnswerType.ALBUM);
			answer.setRightAnswer(1);
			answers.add(answer);
			roundSongs2.remove(0);
			for (Song s : roundSongs2) {
				answer = new Answer(null, null, null, 0, null, 0, null);
				answer.setAlbum(s.getAlbum());
				answer.setAnswerType(AnswerType.ALBUM);
				answers.add(answer);
			}
		} else {
			throw new IllegalStateException("Expecting only Enum Types declared in AnswerType Class");
		}
	}

	/**
	 * <p>
	 * Stopt aktuelle Runde des Spieles
	 * 
	 * Stopt abspielen, macht die Listen, die fuer das naechste Runde beneotigt
	 * werden, leer. Ruft die Methode, wenn noetig ist, auf, die dem Benutzer
	 * "score" bzw. sein Spielerstand erhoert. Startet das Spiel neu.
	 * 
	 * @param win
	 *            win parameter um zu beurteilen, ob die Spielrunde gewonnen
	 *            oder verloren gegangen ist
	 */
	@Override
	public void gameStop(boolean win) {
		roundSongs.clear();
		answers.clear();
		if (player != null) {
			player.stop();
			// player.dispose();
		}
		if (win) {
			addScore();
		} else {
			gameStart();
		}
	}

	/**
	 * <p>
	 * Stopt das Spiel
	 * 
	 * Stopt abspielen, macht die Listen, die fuer das naechste Runde beneotigt
	 * werden, leer. Die Werte werden an default bzw. standart zurueckgesetzt.
	 * 
	 */
	public void gameEnd() {
		roundSongs.clear();
		answers.clear();
		if (player != null) {
			player.stop();
			// player.dispose();
		}
		setScore(0);
		setGameOver(false);
		difficultyType = DifficultyType.NORMAL;
	}

	/**
	 * Methode, die Anzahl von einem(-er) Benutzer(-innen) kontrolliert
	 */
	public void roundlost() {
		setLives(getLives() - 1);
		if (getLives() == 0) {
			setGameOver(true);
		}
		gameStop(false);
	}

	/**
	 * Methode, die in die Abhaengigkeit von der Schwierigkeitstuffe, den
	 * "score" erhoert
	 */

	public void addScore() {
		if (getDifficultyType() == DifficultyType.EASY) {
			setScore(score += 1);
		}
		if (getDifficultyType() == DifficultyType.NORMAL) {
			setScore(score += 2);
		}
		if (getDifficultyType() == DifficultyType.HARD) {
			setScore(score += 3);
		}
		gameStart();
	}

	/**
	 * Methode, die die vorhandene Benutzerdaten speichert und danach ruft die
	 * Methode auf, die das Spiel beendet
	 */
	public void saveData() {
		saver.saveScore(user.getName(), user.getScore());
		gameEnd();
	}

	/**
	 * Methode, die die Liste mit den vorher gespielten Benutzer abholt
	 * 
	 * @return the list Liste mit gespeicherten vorher gespielten Benutzer
	 */

	public ArrayList<User> loadData() {
		ArrayList<User> list = loader.load();
		return list;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setPlayerName(String name) {
		user.setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return user.getName();
	}

	/**
	 * @return the checkIsSameSongMetaTwiceInAnswer
	 */
	public boolean isCheckIsSameSongMetaTwiceInAnswer() {
		return checkIsSameSongMetaTwiceInAnswer;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return user.getScore();
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the gameOver
	 */
	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver
	 *            the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the type
	 */
	public DifficultyType getDifficultyType() {
		return difficultyType;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setDifficultyType(DifficultyType difficultyType) {
		this.difficultyType = difficultyType;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
		user.setScore(score);
	}

	/**
	 * @return the answers
	 */
	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @param checkIsSameSongMetaTwiceInAnswer
	 *            the checkIsSameSongMetaTwiceInAnswer to set
	 */
	public void setCheckIsSameSongMetaTwiceInAnswer(boolean checkIsSameSongMetaTwiceInAnswer) {
		this.checkIsSameSongMetaTwiceInAnswer = checkIsSameSongMetaTwiceInAnswer;
	}

}
