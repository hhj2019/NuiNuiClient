package kr.or.ddit.nn.vo.notice;

import java.io.Serializable;

public class Notice_searchVO implements Serializable{

	/**
	 * 검색 VO
	 */
	private String search_text;
	private String search_text1;

	public String getSearch_text1() {
		return search_text1;
	}
	public void setSearch_text1(String search_text1) {
		this.search_text1 = search_text1;
	}
	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
}
