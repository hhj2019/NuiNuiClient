package kr.or.ddit.nn.service.ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.ticket.TicketVO;

public interface TicketService extends Remote{

	/**
	 * 관리자가 새로운 이용권 상품을 등록한다.
	 * @param ticket
	 * @return
	 */
	public int insertTicket(TicketVO ticket) throws RemoteException;
	
	/**
	 * 관리자가 티켓정보를 수정한다.
	 * @param ticket
	 * @return
	 */
	public int updateTicket(TicketVO ticket) throws RemoteException;
	
	/**
	 * 관리자가 티켓을 삭제한다.
	 * @param id
	 * @return
	 */
	public int removeTicket(int id) throws RemoteException;
	
	/**
	 * 판매중인 티켓을 모두 보여준다.
	 * @return
	 */
	public List<TicketVO> viewAllTicketToUser() throws RemoteException;
	
	/**
	 * 관리중인 티켓을 모두 보여준다.
	 * @return
	 */
	public List<TicketVO> viewAllTicketToManager() throws RemoteException;
	
	/**
	 * 특정 티켓의 정보를 보여준다.
	 * @param id
	 * @return
	 */
	public TicketVO selectTicekt(int id) throws RemoteException;
}
