package kr.or.ddit.nn.service.album;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.album.albumVO;
import kr.or.ddit.nn.vo.music.viewMusicVO;

public interface albumService extends Remote{
	
	/**
	 * 앨범 추가 메서드
	 * @param av
	 * @return
	 */
	public int insertAlbum(albumVO av) throws RemoteException;
	/**
	 * 앨범 삭제 메서드
	 * @param album_id
	 * @return
	 */
	public int deleteAlbum(albumVO av) throws RemoteException;
	/**
	 * 앨범 수정 메서드
	 * @param av
	 * @return
	 */
	public int updateAlbum(albumVO av) throws RemoteException;
	/**
	 * 앨범 전체 출력 메서드
	 * @param album_id
	 * @return
	 */
	public albumVO selectAlbum(albumVO av) throws RemoteException;
	/**
	 * 앨범 단일 출력 메서드
	 * @param album_id
	 * @return
	 */
	public List<albumVO> selectAllAlbum() throws RemoteException;
	/**
	 * 최근 앨범 출력
	 * @return
	 * @throws RemoteException
	 */
	public List<albumVO> selectNewAlbum() throws RemoteException;
	
	public List<viewMusicVO> selectNewAlbumDetail(int album_id) throws RemoteException;
}
