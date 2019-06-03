package kr.or.ddit.nn.service.answer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.qna.AnswerVO;
import kr.or.ddit.nn.vo.qna.QnAVO;
import kr.or.ddit.nn.vo.qna.QuestionVO;

public interface AnswerService extends Remote{

	/**
	 * 답변 상세 조회 메서드
	 * @param av
	 * @return
	 */
	public QnAVO selectAnswerdetail(int question_id) throws RemoteException;
	
	/**
	 * 답변 추가
	 * @param av
	 * @return
	 */
	public int insertAnswer(AnswerVO av) throws RemoteException;

	/**
	 * 관리자 답변 전체 출력
	 * @param question_id
	 * @return
	 */
	public List<QnAVO> SelectMAnswerList(int question_id) throws RemoteException;
	
	/**
	 * 자기 질문 전체 출력
	 * @param qav
	 * @return
	 */
	public List<QnAVO> SelectMyAnswerList(QuestionVO qv) throws RemoteException;

	/**
	 * 답변 여부 확인
	 * @param question
	 * @return
	 */
	public int SelectAnswerOK(int question_id) throws RemoteException; 

	/**
	 * 답변 삭제
	 * @param question_id
	 * @return
	 * @throws RemoteException
	 */
	public int deleteAnswer(int question_id) throws RemoteException;
}
