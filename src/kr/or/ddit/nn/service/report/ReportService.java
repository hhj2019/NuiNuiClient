package kr.or.ddit.nn.service.report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.report.ReportVO;

public interface ReportService extends Remote{

	/**
	 * 신고 전체 조회
	 * @return
	 */
	public List<ReportVO> selectAllReport() throws RemoteException;
	
	/**
	 * 신고 선택 조회
	 * @param report_id
	 * @return
	 */
	public ReportVO selectReportdetail(int report_id) throws RemoteException;

	/**
	 * 최근 신고 조회
	 * @return
	 */
	public List<ReportVO> selectNewReport() throws RemoteException;
	
	/**
	 * 신고하기 추가
	 * @param rv
	 * @return
	 */
	public int insertReport(ReportVO rv) throws RemoteException;
	
	/**
	 * 신고하기 관리자 수정
	 * @param rv
	 * @return
	 * @throws RemoteException
	 */
	public int updateReport(ReportVO rv) throws RemoteException;
	
	/**
	 * 자신이 작성한 신고 출력
	 * @param mem_id
	 * @return
	 * @throws RemoteException
	 */
	public List<ReportVO> selectMyReport(String mem_id) throws RemoteException;
}
