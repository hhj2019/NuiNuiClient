package kr.or.ddit.nn.service.ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.ticket.TicketHistoryVO;

public interface TicketHistoryService extends Remote{

	/**
	 * 구입한 티켓을 구입내역에 추가한다.
	 * @param ticket
	 * @return
	 */
	public int insertHistory(TicketHistoryVO ticket) throws RemoteException;
	
	/**
	 * 구입한 티켓내역을 모두 조회한다.
	 * @param memId
	 * @return
	 */
	public List<TicketHistoryVO> viewHistory(String memId) throws RemoteException;
}
