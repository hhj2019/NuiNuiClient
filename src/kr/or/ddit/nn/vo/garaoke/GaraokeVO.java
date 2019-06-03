package kr.or.ddit.nn.vo.garaoke;

import java.io.Serializable;

/**
 * 노래방 번호 알려주는 VO
 * @author pc15
 *
 */
public class GaraokeVO implements Serializable{

	private int music_id;
	private int garaoke_id;
	private int garaoke_number;
	
	public int getMusic_id() {
		return music_id;
	}
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
	public int getGaraoke_id() {
		return garaoke_id;
	}
	public void setGaraoke_id(int garaoke_id) {
		this.garaoke_id = garaoke_id;
	}
	public int getGaraoke_number() {
		return garaoke_number;
	}
	public void setGaraoke_number(int garaoke_number) {
		this.garaoke_number = garaoke_number;
	}
}
