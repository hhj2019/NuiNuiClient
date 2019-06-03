package game.logic;

/**
 * Aufzaehlung der Typen, in die die Antworten eingeteilt werden koennen.
 * 
 * <p>
 * Fuer jeden Typ laesst sich der ausgeschriebene Name
 * <code>String getLabel()</code> abfragen.
 * 
 * @author Letychevskyy
 * @version 0.5
 */
public enum AnswerType {

	/**
	 * Typ-Konstante fuer Title
	 */
	TITLE("Titel"),
	/**
	 * Typ-Konstante fuer Image
	 */
	IMAGE("Image"),
	/**
	 * Typ-Konstante fuer Artist
	 */
	ARTIST("Artist"),
	/**
	 * Typ-Konstante fuer Year
	 */
	YEAR("Year"),
	/**
	 * Typ-Konstante fuer Album
	 */
	ALBUM("Album");

	private String label;

	/**
	 * Konstruktor zur Uebergabe des ausgeschriebenen Namens eines Typs
	 * 
	 * @param label
	 *            Name der Gruppe
	 */
	private AnswerType(String label) {
		this.label = label;
	}

	/**
	 * Gibt den ausgeschriebenen Namen des Typs zurueck
	 * 
	 * @return Name
	 */
	public String getLabel() {
		return this.label;
	}
}
