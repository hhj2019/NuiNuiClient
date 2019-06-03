package kr.or.ddit.nn.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.member.MemberVO;

public interface JoinService extends Remote {

	public List<MemberVO> selectId(MemberVO mv) throws RemoteException;

	public List<MemberVO> selectEmail(MemberVO mv) throws RemoteException;

	public List<MemberVO> selectNick(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> selectName(MemberVO mv) throws RemoteException;

	public int insertMember(MemberVO mv) throws RemoteException;

	public int deleteMember(String memId) throws RemoteException;

	/**
	 * Level 조회
	 * @param mem_id
	 * @return
	 * @throws RemoteException
	 */
	public int selectMemLevel (MemberVO mv) throws RemoteException;
}
