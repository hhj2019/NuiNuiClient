package kr.or.ddit.nn.vo.ticket;

import java.io.Serializable;
import java.util.Date;

public class TicketHistoryVO implements Serializable{
	
	private int ticket_history_id;
	private Date buy_date;
	private String mem_id;
	private int ticket_id;

	public int getTicket_history_id() {
		return ticket_history_id;
	}
	public void setTicket_history_id(int ticket_history_id) {
		this.ticket_history_id = ticket_history_id;
	}
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
}
