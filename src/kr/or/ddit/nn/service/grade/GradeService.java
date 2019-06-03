package kr.or.ddit.nn.service.grade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.grade.GradeVO;

public interface GradeService extends Remote {
	/**
	 * 특정 등급에 대한 정보 반환
	 * 
	 * @param id
	 * @return
	 */
	public GradeVO selectGrade(int id) throws RemoteException;

	/**
	 * 전체 등급에 대한 정보 반환
	 * 
	 * @return
	 */
	public List<GradeVO> viewAllGrade() throws RemoteException;
}
