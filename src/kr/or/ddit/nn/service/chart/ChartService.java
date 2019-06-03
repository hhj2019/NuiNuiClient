package kr.or.ddit.nn.service.chart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.chart.ChartVO;
import kr.or.ddit.nn.vo.chartItem.Chart_ItemVO;

public interface ChartService extends Remote{

	/**
	 * 차트 목록 출력
	 * @return
	 */
	public ChartVO selectAllChart(ChartVO cv) throws RemoteException;

	/**
	 * 차트 아이디에 맞는 노래 전체 출력
	 * @return
	 * @throws RemoteException
	 */
	public List<Chart_ItemVO> selectChartMusic() throws RemoteException;

	/**
	 * 차트 추가
	 * @param cv
	 * @return
	 * @throws RemoteException
	 */
	public int insertChart(ChartVO cv) throws RemoteException;
}
