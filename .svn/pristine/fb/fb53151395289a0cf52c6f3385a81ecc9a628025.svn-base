package kr.or.ddit.nn.service.event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.event.EventVO;

public interface eventService extends Remote{

	/**
	 * 이벤트 목록 조회
	 * @return
	 */
	public List<EventVO> selectAllEvent() throws RemoteException;
	
	/**
	 * 이벤트 상세 조회
	 * @param event_id
	 * @return
	 */
	public EventVO selectEventdetail(int event_id) throws RemoteException;
	
	/**
	 * 이벤트 기간 조회
	 * @return
	 * @throws RemoteException
	 */
	public List<EventVO> selectEventDate() throws RemoteException;
	
	/**
	 * 이벤트 추가
	 * @param ev
	 * @return
	 */
	public int insertEvent(EventVO ev) throws RemoteException;
	
	/**
	 * 이벤트 수정
	 * @param ev
	 * @return
	 */
	public int updateEvent(EventVO ev) throws RemoteException;
	
	/**
	 * 이벤트 삭제
	 * @param event_id
	 * @return
	 */
	public int deleteEvent(int event_id) throws RemoteException;
}
