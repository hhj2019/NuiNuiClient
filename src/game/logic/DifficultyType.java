package game.logic;

/**
 * Aufzaehlung der Typen, in die die Schwierigkeitsgrade eingeteilt werden
 * koennen.
 * 
 * <p>
 * Fuer jeden Typ laesst sich der ausgeschriebene Name
 * <code>String getLabel()</code> abfragen.ㅋ
 * 
 * @author 유형욱
 * @version 0.5
 */

public enum DifficultyType {

	/**
	 * EASY 모드
	 */
	EASY("Easy"),
	/**
	 * NORMAL 모드
	 */
	NORMAL("Normal"),
	/**
	 * HARD 모드
	 */
	HARD("Hard");

	private String label;

	/**
	 * 
	 * @param label
	 *            Name der Gruppe
	 */
	private DifficultyType(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @return Name
	 */
	public String getLabel() {
		return this.label;
	}
}
