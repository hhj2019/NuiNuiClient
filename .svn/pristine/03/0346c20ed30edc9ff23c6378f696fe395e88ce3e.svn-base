package kr.or.ddit.nn.service.playlistitem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.music.viewMusicVO;
import kr.or.ddit.nn.vo.playlistitem.PlaylistItemVO;


public interface PlaylistItemService extends Remote{
	/**
	 * 1-1. Playlistitem 조회
	 */
	public List<viewMusicVO> selectPlayListItem(int playlist_id) throws RemoteException;
	
	public int insertPlayListItem(PlaylistItemVO piv) throws RemoteException;
}
