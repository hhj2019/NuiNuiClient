package kr.or.ddit.nn.service.playmusicitem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.playmusicitem.PlayMusicItemVO;

public interface PlayMusicItemService extends Remote {
	/**
	 * 1-1. Playmusicitem 조회
	 */
	public List<PlayMusicItemVO> selectPalyMusicItem() throws RemoteException;

	/**
	 * 2. Playmusicitem 삭제
	 */
	public int deletePalyMusicItem(PlayMusicItemVO vo) throws RemoteException;

	/**
	 * 3. Playmusicitem 추가
	 */
	public int insertPalyMusicItem(PlayMusicItemVO vo) throws RemoteException;

}
