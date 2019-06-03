package kr.or.ddit.nn.vo.chartItem;

import java.io.Serializable;

/**
 * 차트 상세 정보
 * @author 방민주
 *
 */
public class Chart_ItemVO implements Serializable{

	private int chart_id;
	private int music_id;
	
	public int getChart_id() {
		return chart_id;
	}
	public void setChart_id(int chart_id) {
		this.chart_id = chart_id;
	}
	public int getMusic_id() {
		return music_id;
	}
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
}
