package kr.or.ddit.nn.service.music;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.music.BuyMusicHistoryVO;

public interface BuyMusicHistoryService extends Remote{

	/**
	 * 음원을 결제할때마다 내역에 추가한다.
	 * @param history
	 * @return
	 */
	public int insertHistory(BuyMusicHistoryVO history) throws RemoteException;
	
	/**
	 * 음원결제 내역을 조회한다.
	 * @param memId
	 * @return
	 */
	public List<BuyMusicHistoryVO> viewHistory(String memId) throws RemoteException;
	
	/**
	 * 음원결제 내역중 특정 음악의 다운로드여부를 조회한다.
	 * @param memId
	 * @return
	 */
	public int selectHistory(BuyMusicHistoryVO history) throws RemoteException;
}
