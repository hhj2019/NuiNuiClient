package kr.or.ddit.nn.vo.ticket;

import java.io.Serializable;
import java.util.Date;

public class MyTicketVO implements Serializable{

	private int my_ticket_id;
	private int ticket_download_count;
	private Date ticket_end_date;
	private int ticket_id;
	private String mem_id;
	
	public int getMy_ticket_id() {
		return my_ticket_id;
	}
	public void setMy_ticket_id(int my_ticket_id) {
		this.my_ticket_id = my_ticket_id;
	}
	public int getTicket_download_count() {
		return ticket_download_count;
	}
	public void setTicket_download_count(int ticket_download_count) {
		this.ticket_download_count = ticket_download_count;
	}
	public Date getTicket_end_date() {
		return ticket_end_date;
	}
	public void setTicket_end_date(Date ticket_end_date) {
		this.ticket_end_date = ticket_end_date;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
