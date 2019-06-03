package kr.or.ddit.nn.service.garaoke;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.nn.vo.garaoke.GaraokeVO;

public interface garaokeService extends Remote{
	/**
	 * 노래방 번호 추가
	 * @param gv
	 * @return
	 */
	public int insertGaraoke(GaraokeVO gv) throws RemoteException;
	/**
	 * 노래방 번호 수정
	 * @param gv
	 * @return
	 */
	public int updateGaraoke(GaraokeVO gv) throws RemoteException;
	/**
	 * 노래방 번호 삭제
	 * @param gv
	 * @return
	 */
	public int deleteGaraoke(GaraokeVO gv) throws RemoteException;
	/**
	 * 노래방 번호 조회
	 * @param artist_id
	 * @return
	 */
	public GaraokeVO selectGaraoke(int music_id) throws RemoteException;
}
