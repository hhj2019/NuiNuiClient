package game.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

public class TestMetadata implements ITestMetadaten {

	/**
	 * Die Anzahl von den Versuchen für die Überprüfung ob eine von der 4
	 * zufällig gewählten Songs in NormalDifficultType und HardDifficultType den
	 * anderen Song gleich ist
	 **/
	public static final int NORMAL_HARD_TEST_COUNTER = 2;
	/**
	 * Die Anzahl von den Versuchen für die Überprüfung ob eine von der 4
	 * zufällig gewählten Songs in EasyDifficultType den anderen Song gleich ist
	 **/
	public static final int EASY_TEST_COUNTER = 10;

	private int easyTestCounter = EASY_TEST_COUNTER;
	private int titleTestCounter = NORMAL_HARD_TEST_COUNTER;
	private int artistTestCounter = NORMAL_HARD_TEST_COUNTER;
	private int albumTestCounter = NORMAL_HARD_TEST_COUNTER;
	private int yearTestCounter = NORMAL_HARD_TEST_COUNTER;
	private int imageTestCounter = NORMAL_HARD_TEST_COUNTER;

	private Image im = new Image("/game/img/temp.jpg");
	private boolean check;
	private int resRandom;
	private ArrayList<Integer> resRand = new ArrayList<>();
	private ArrayList<AnswerType> categories = new ArrayList<>();
	private ArrayList<Song> songs = new ArrayList<>();
	private ArrayList<String> fileList = new ArrayList<>();
	private ArrayList<Song> roundSongs = new ArrayList<>();

	/**
	 * Methode, die die Liste mit Pfade von .mp3 Files und die Liste mit Songs
	 * von GameLogic erhaelt und gibt zur ueberprufung weiter
	 * 
	 * @param fileList1
	 *            die Liste mit Pfade von .mp3 Files
	 * @param songs1
	 *            die Liste mit Songs
	 */

	@Override
	public void testEasy(ArrayList<String> fileList1, ArrayList<Song> songs1) {
		fileList = fileList1;
		songs = songs1;
		easyTestCounter = EASY_TEST_COUNTER;
		testEasyInit();
	}

	/**
	 * Methode waehlt 4 zufaellige Zahlen aus. Jede Zahl representiert einen
	 * Song. Schiekt die 4 Zahlen an die ueberpuefung weiter. Macht das so
	 * lange, bis eine passende fuer das Spiel Kombination von Songs gefunden
	 * ist, oder Counter aka der Zaeler, der die Anzahl der Versuche darstellt,
	 * nicht den Wert 0 erreicht
	 */

	private void testEasyInit() {
		ArrayList<Integer> rand = new ArrayList<>();
		ThreadLocalRandom.current().ints(0, fileList.size()).distinct().limit(4).forEach(rand::add);
		check = easyTest(rand);
		if (check) {
			setCheck(check);
			setResRand(rand);
		} else if (check == false && easyTestCounter != 0) {
			testEasyInit();
		} else {
			setCheck(check);
		}
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean easyTest(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				if ((roundSongs.get(i).getTitle()).isEmpty() || (roundSongs.get(k).getTitle()).isEmpty()
						|| roundSongs.get(i).getTitle().equals((roundSongs.get(k).getTitle()))) {
					easyTestCounter--;
					check = false;
					roundSongs.clear();
					return check;
				}
			}
		}
		roundSongs.clear();
		return check;
	}

	/**
	 * Methode, die eine Liste mit Pfaden von .mp3 Files, eine Liste mit
	 * Kategorien von Filetypen, und eine Liste mit Songs von GameLogic erhaelt
	 * und gibt zur ueberprufung weiter
	 * 
	 * @param categories2
	 *            Liste mit Kategorien von Filetypen
	 * @param fileList1
	 *            Liste mit Pfade von .mp3 Files
	 * @param songs1
	 *            Liste mit Songs
	 */

	@Override
	public void test(ArrayList<String> fileList1, ArrayList<AnswerType> categories2, ArrayList<Song> songs1) {
		categories = categories2;
		fileList = fileList1;
		songs = songs1;
		titleTestCounter = NORMAL_HARD_TEST_COUNTER;
		artistTestCounter = NORMAL_HARD_TEST_COUNTER;
		albumTestCounter = NORMAL_HARD_TEST_COUNTER;
		yearTestCounter = NORMAL_HARD_TEST_COUNTER;
		imageTestCounter = NORMAL_HARD_TEST_COUNTER;
		testInit();
	}

	/**
	 * Methode waehlt 4 zufaellige Zahlen aus. Jede Zahl representiert einen
	 * Song. Schiekt die 4 Zahlen an die ueberpuefung weiter. Macht das so
	 * lange, bis eine passende fuer das Spiel Kombination von Songs gefunden
	 * ist, oder die Liste mit den Kategorien leer wird
	 */

	private void testInit() {
		ArrayList<Integer> rand = new ArrayList<>();
		ThreadLocalRandom.current().ints(0, fileList.size()).distinct().limit(4).forEach(rand::add);
		int random = ThreadLocalRandom.current().nextInt(0, categories.size());

		check = normalTest(rand, random);
		if (check) {
			setCheck(check);
			setResRand(rand);
			setResRandom(random);
		} else if (check == false && !categories.isEmpty()) {
			testInit();
		} else {
			setCheck(check);
		}
	}

	/**
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @param random
	 *            random zufaellig gewaelte Zahl, die eine zuffaellig gewaelte
	 *            Kategorie dastellt
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean normalTest(ArrayList<Integer> rand, int random) {
		boolean check = true;
		if (categories.isEmpty()) {
			check = false;
		} else {
			AnswerType type = categories.get(random);

			if (type == AnswerType.ALBUM) {
				check = testAlbum(rand);

			} else if (type == AnswerType.ARTIST) {
				check = testArtist(rand);

			} else if (type == AnswerType.TITLE) {
				check = testTitle(rand);

			} else if (type == AnswerType.IMAGE) {
				check = testImage(rand);

			} else if (type == AnswerType.YEAR) {
				check = testYera(rand);

			} else {
				throw new IllegalStateException("Expecting only Enum Types declared in AnswerType Class");
			}
		}
		roundSongs.clear();
		return check;
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean testTitle(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				if ((roundSongs.get(i).getTitle()).isEmpty() || (roundSongs.get(k).getTitle()).isEmpty()
						|| roundSongs.get(i).getTitle().equals((roundSongs.get(k).getTitle()))) {
					titleTestCounter--;
					check = false;
					if (titleTestCounter == 0) {
						categories.remove(AnswerType.TITLE);
					}
					return check;
				}
			}
		}
		return check;
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean testYera(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				if (roundSongs.get(i).getYear() == 0 || roundSongs.get(k).getYear() == 0
						|| roundSongs.get(i).getYear() == (roundSongs.get(k).getYear())) {
					yearTestCounter--;
					check = false;
					if (yearTestCounter == 0) {
						categories.remove(AnswerType.YEAR);
					}
					return check;
				}
			}
		}
		return check;
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean testImage(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				int n = 0;
				int m = 0;
				if ((roundSongs.get(i).getImage()) == im || (roundSongs.get(k).getImage()) == im
						|| roundSongs.get(i).getImage().getPixelReader().getArgb(n,
								m) == (roundSongs.get(k).getImage().getPixelReader().getArgb(n, m))) {
					imageTestCounter--;
					check = false;
					if (imageTestCounter == 0) {
						categories.remove(AnswerType.IMAGE);
					}
					return check;
				}
			}
		}
		return check;
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean testArtist(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				if ((roundSongs.get(i).getArtist()).isEmpty() || (roundSongs.get(k).getArtist()).isEmpty()
						|| roundSongs.get(i).getArtist().equals(roundSongs.get(k).getArtist())) {
					artistTestCounter--;
					check = false;
					if (artistTestCounter == 0) {
						categories.remove(AnswerType.ARTIST);
					}
					return check;
				}
			}
		}
		return check;
	}

	/**
	 * Methode, um metadata zu ueberpruefen
	 * 
	 * @param rand
	 *            rand Liste mit vier ausgewarlten Songs fuer das Quiz
	 * @return boolean Wert, der entscheidet, ob der Test erfolgreich
	 *         abgeschlossen ist oder nicht
	 */

	private boolean testAlbum(ArrayList<Integer> rand) {
		boolean check = true;
		for (Integer num : rand) {
			roundSongs.add(songs.get(num));
		}
		for (int i = 0; i < roundSongs.size(); i++) {
			for (int k = i + 1; k < roundSongs.size(); k++) {
				if ((roundSongs.get(i).getAlbum()).isEmpty() || (roundSongs.get(k).getAlbum()).isEmpty()
						|| roundSongs.get(i).getAlbum().equals(roundSongs.get(k).getAlbum())) {
					albumTestCounter--;
					check = false;
					if (albumTestCounter == 0) {
						categories.remove(AnswerType.ALBUM);
					}
					return check;
				}
			}
		}
		return check;
	}

	/**
	 * @return the check
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * @param check
	 *            the check to set
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * @return the resRandom
	 */
	public int getResRandom() {
		return resRandom;
	}

	/**
	 * @param resRandom
	 *            the resRandom to set
	 */
	public void setResRandom(int resRandom) {
		this.resRandom = resRandom;
	}

	/**
	 * @return the resRand
	 */
	public ArrayList<Integer> getResRand() {
		return resRand;
	}

	/**
	 * @param resRand
	 *            the resRand to set
	 */
	public void setResRand(ArrayList<Integer> resRand) {
		this.resRand = resRand;
	}
}