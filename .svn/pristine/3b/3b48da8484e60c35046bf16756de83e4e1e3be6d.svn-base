package kr.or.ddit.nn.service.music;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.chartItem.Chart_ItemVO;
import kr.or.ddit.nn.vo.music.Music_searchVO;
import kr.or.ddit.nn.vo.music.musicVO;
import kr.or.ddit.nn.vo.music.viewMusicVO;

/**
 * 뮤직 관리 인스턴스
 * @author pc15
 *
 */
public interface musicService extends Remote{
	
	/**
	 * 음악 추가
	 * @param mv
	 * @return
	 */
	public int insertMusic(musicVO mv) throws RemoteException;
	
	/**
	 * 음악 삭제
	 * @param music_id
	 * @return
	 */
	public int deleteMusic(int music_id) throws RemoteException;
	
	/**
	 * 음악 수정
	 * @param mv
	 * @return
	 */
	public int updateMusic(musicVO mv) throws RemoteException;
	
	/**
	 * 음악 출력
	 * @param music_id
	 * @return
	 */
	public musicVO selectMusic(int music_id) throws RemoteException;
	
	/**
	 * 음약 전체 출력
	 * @return
	 */
	public List<musicVO> selectAllMusic() throws RemoteException;
	
	/**
	 * 음약 전체 출력
	 * @return
	 */
	public List<viewMusicVO> selectViewMusic() throws RemoteException;
	
	/**
	 * 차트음악 전체 출력
	 * @return
	 */
	public List<viewMusicVO> selectMusicCount(List<Chart_ItemVO> chart_list) throws RemoteException;
	
	/**
	 * 음악 정보 출력
	 * @return
	 */
	public viewMusicVO selectMusicInfo(int music_id) throws RemoteException;
	
	
	public List<viewMusicVO> serch(Music_searchVO msh) throws RemoteException;
	
	public List<viewMusicVO> selectMainChartMusic() throws RemoteException;
	

	public List<viewMusicVO> selectNewMusic() throws RemoteException;
	
	public int selectMusicForName(musicVO mv) throws RemoteException;
}
