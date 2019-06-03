package kr.or.ddit.nn.service.mv;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.mv.MVVO;

public interface mvService extends Remote{
	public int insertMV(MVVO mvv) throws RemoteException;
	public int updateMV(MVVO mvv) throws RemoteException;
	public int deleteMV(MVVO mvv) throws RemoteException;
	public List<MVVO> selectAllMV() throws RemoteException;
	public MVVO selectMV(MVVO mvv) throws RemoteException;
}
