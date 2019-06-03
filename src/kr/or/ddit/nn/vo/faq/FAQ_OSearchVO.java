package kr.or.ddit.nn.vo.faq;

import java.io.Serializable;

public class FAQ_OSearchVO implements Serializable{
	
	private String search_text;

	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
}
