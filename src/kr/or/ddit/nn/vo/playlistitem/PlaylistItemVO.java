package kr.or.ddit.nn.vo.playlistitem;

import java.io.Serializable;

public class PlaylistItemVO implements Serializable{
	private int playlist_id;
	private int music_id;

	public int getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(int playlist_id) {
		this.playlist_id = playlist_id;
	}

	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

}

// 엔티티 타입명 PLAYLIST_ITEM 작성일 2019-05-14
// 테이블명 PLAYLIST_ITEM 작성자 유형욱
// 테이블 설명 플레이리스트에 속한 음악
// 번호 속성명 컬럼명 도메인 데이터 타입 Not Null 기본값 KEY
// 1 ID(FK) playlist_id N/A NUMBER Y pk
// 2 음악ID(FK) music_id N/A NUMBER Y PK
