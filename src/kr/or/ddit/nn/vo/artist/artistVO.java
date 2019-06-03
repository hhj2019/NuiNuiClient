package kr.or.ddit.nn.vo.artist;

import java.io.Serializable;
import java.sql.Date;

public class artistVO implements Serializable{
	private int artist_id;
	private String artist_name;
	private String artist_enter;
	private int artist_like;
	private String artist_type;
	private String artist_debut;
	private String artist_country;

	public String getArtist_type() {
		return artist_type;
	}
	public void setArtist_type(String artist_type) {
		this.artist_type = artist_type;
	}
	public String getArtist_debut() {
		return artist_debut;
	}
	public void setArtist_debut(String artist_debut) {
		this.artist_debut = artist_debut;
	}
	public String getArtist_country() {
		return artist_country;
	}
	public void setArtist_country(String artist_country) {
		this.artist_country = artist_country;
	}
	public int getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getArtist_enter() {
		return artist_enter;
	}
	public void setArtist_enter(String artist_enter) {
		this.artist_enter = artist_enter;
	}
	public int getArtist_like() {
		return artist_like;
	}
	public void setArtist_like(int artist_like) {
		this.artist_like = artist_like;
	}
	

}
