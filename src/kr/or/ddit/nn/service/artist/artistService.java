package kr.or.ddit.nn.service.artist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.artist.artistVO;

public interface artistService extends Remote{
	/**
	 * 아티스트 추가
	 * @param av
	 * @return
	 */
	public int insertArtist(artistVO av) throws RemoteException;
	/**
	 * 아티스트 수정
	 * @param av
	 * @return
	 */
	public int updateArtist(artistVO av) throws RemoteException;
	/**
	 * 아티스트 삭제
	 * @param av
	 * @return
	 */
	public int deleteArtist(artistVO av) throws RemoteException;
	/**
	 * 아티스트 전체조회
	 * @return
	 */
	public List<artistVO> selectAllArtist() throws RemoteException;
	/**
	 * 아티스트 조회
	 * @param artist_id
	 * @return
	 */
	public artistVO selectArtist(artistVO av) throws RemoteException;
	

	public artistVO selectArtistForName(String artist_name) throws RemoteException;
}
