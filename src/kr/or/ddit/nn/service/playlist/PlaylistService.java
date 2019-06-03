package kr.or.ddit.nn.service.playlist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.playlist.PlaylistVO;

public interface PlaylistService extends Remote{
	/**
	 * 1. Playlist 전체 조회
	 */
	public List<PlaylistVO> selectPlayListAll(String mem_id) throws RemoteException; 
	
	/**
	 * 1-1. Playlist 단일 조회
	 */
	public PlaylistVO selectPlayList(int playlist_id) throws RemoteException;
	

	/**
	 * 2. Playlist 삭제
	 */
	public int deletePlaylist(int playlist_id) throws RemoteException;

	
	/**
	 * 3. Playlist 수정
	 */
	public int updatePlaylist(PlaylistVO vo) throws RemoteException;
	
	/**
	 * 4. Playlist 추가
	 */
	public int insertPlaylist(PlaylistVO vo) throws RemoteException;
	
}
