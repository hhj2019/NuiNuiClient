package kr.or.ddit.nn.service.mvHistory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.mvHistory.MvHistoryVO;

public interface MvHistoryService extends Remote {
	/**
	 * 1. mvHistory 조회
	 */

	public List<MvHistoryVO> selectMvHistory(String mem_id) throws RemoteException;

}
