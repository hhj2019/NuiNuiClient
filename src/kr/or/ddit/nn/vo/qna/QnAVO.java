package kr.or.ddit.nn.vo.qna;

import java.io.Serializable;

/**
 * 질문 답변 vo
 * @author pc11
 *
 */
public class QnAVO implements Serializable{

	private int question_id;
	private String q_info;
	private String question_title;
	private String question_content;
	private String question_date;
	private String question_category;
	
	public String getQuestion_category() {
		return question_category;
	}
	public void setQuestion_category(String question_category) {
		this.question_category = question_category;
	}
	private int answer_id;
	private String a_info;
	private String answer_title;
	private String answer_content;
	private String answer_date;
	
	private String mem_id;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQ_info() {
		return q_info;
	}
	public void setQ_info(String q_info) {
		this.q_info = q_info;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public String getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(String question_date) {
		this.question_date = question_date;
	}
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
		this.question_id = answer_id;
	}
	public String getA_info() {
		return a_info;
	}
	public void setA_info(String a_info) {
		this.a_info = a_info;
		this.q_info = a_info;
	}
	public String getAnswer_title() {
		return answer_title;
	}
	public void setAnswer_title(String answer_title) {
		this.answer_title = answer_title;
		this.question_title = answer_title;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
		this.question_content = answer_content;
	}
	public String getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(String answer_date) {
		this.answer_date = answer_date;
		this.question_date = answer_date;
	}
}
