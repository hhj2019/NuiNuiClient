package kr.or.ddit.nn.vo.music;

import java.io.Serializable;
import java.sql.Timestamp;

import javafx.scene.image.ImageView;

public class viewMusicVO implements Serializable{
	private int music_id;
	private String music_name;
	private String music_lyrics;
	private String music_playtime;
	private String music_genre;
	private String album_name;
	private String album_img;
	private int music_count;
	private String artist_name;
	private String music_file;
	private String album_date;
	private int music_rank;

	public int getMusic_rank() {
		return music_rank;
	}
	public void setMusic_rank(int music_rank) {
		this.music_rank = music_rank;
	}
	public int getMusic_id() {
		return music_id;
	}
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getMusic_lyrics() {
		return music_lyrics;
	}
	public void setMusic_lyrics(String music_lyrics) {
		this.music_lyrics = music_lyrics;
	}
	public String getMusic_playtime() {
		return music_playtime;
	}
	public void setMusic_playtime(String music_playtime) {
		this.music_playtime = music_playtime;
	}
	public String getMusic_genre() {
		return music_genre;
	}
	public void setMusic_genre(String music_genre) {
		this.music_genre = music_genre;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getAlbum_img() {
		return album_img;
	}
	public void setAlbum_img(String album_img) {
		this.album_img = album_img;
	}
	public int getMusic_count() {
		return music_count;
	}
	public void setMusic_count(int music_count) {
		this.music_count = music_count;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getMusic_file() {
		return music_file;
	}
	public void setMusic_file(String music_file) {
		this.music_file = music_file;
	}
	public String getAlbum_date() {
		return album_date;
	}
	public void setAlbum_date(String album_date) {
		this.album_date = album_date;
	}
	
}
