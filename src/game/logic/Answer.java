package game.logic;

import javafx.scene.image.Image;

/**
 * @author 유형욱
 * @version 0.5 Beta
 */

public class Answer {

	private String title;
	private Image image;
	private String artist;
	private int year;
	private String album;
	private int rightAnswer;
	private AnswerType answerType;

	public Answer(String title, Image image, String artist, int year, String album, int rightAnswer,
			AnswerType answerType) {
		this.title = title;
		this.image = image;
		this.artist = artist;
		this.year = year;
		this.album = album;
		this.rightAnswer = rightAnswer;
		this.answerType = answerType;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the rightAnswer
	 */
	public int getRightAnswer() {
		return rightAnswer;
	}

	/**
	 * @param rightAnswer
	 *            the rightAnswer to set
	 */
	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	/**
	 * @return the answerType
	 */
	public AnswerType getAnswerType() {
		return answerType;
	}

	/**
	 * @param answerType
	 *            the answerType to set
	 */
	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}

}
