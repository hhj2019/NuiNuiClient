package kr.or.ddit.nn.service.garaoke;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.garaoke.garaoke_infoVO;

public interface garaoke_infoService extends Remote{
	
	/**
	 * 노래방 추가
	 * @param giv
	 * @return
	 */
	public int insertGaraoke_info(garaoke_infoVO giv) throws RemoteException;
	/**
	 * 노래방 수정
	 * @param giv
	 * @return
	 */
	public int updateGaraoke_info(garaoke_infoVO giv) throws RemoteException;
	/**
	 * 노래방 삭제
	 * @param giv
	 * @return
	 */
	public int deleteGaraoke_info(garaoke_infoVO giv) throws RemoteException;
	/**
	 * 노래방 출력
	 * @param giv
	 * @return
	 */
	public garaoke_infoVO selectGaraoke_info(garaoke_infoVO giv) throws RemoteException;
	/**
	 * 노래방 전체 출력
	 * @return
	 */
	public List<garaoke_infoVO> selectAllGaraoke_info() throws RemoteException;
	
}
