package kr.or.ddit.nn.service.like;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.like.LikeVO;

public interface LikeService extends Remote{
	
	/**
	 * 1. Like 조회
	 */
	public List<LikeVO> selectLike(String mem_id) throws RemoteException;
	/**
	 * 2. Like 추가
	 */
	public int insertLike(LikeVO lvo) throws RemoteException;
	/**
	 * 3. Like 삭제
	 */
	public int deleteLike(int music_id) throws RemoteException;
	
	
}
