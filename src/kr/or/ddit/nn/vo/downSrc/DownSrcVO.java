package kr.or.ddit.nn.vo.downSrc;

import java.io.Serializable;

public class DownSrcVO implements Serializable {
	
	private int music_id;
	private String music_file;
	
	public int getMusic_id() {
		return music_id;
	}
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
	public String getMusic_file() {
		return music_file;
	}
	public void setMusic_file(String music_file) {
		this.music_file = music_file;
	}
	
}
