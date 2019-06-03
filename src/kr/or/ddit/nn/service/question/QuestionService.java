package kr.or.ddit.nn.service.question;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.qna.QnAVO;
import kr.or.ddit.nn.vo.qna.QuestionVO;

public interface QuestionService extends Remote{

	/**
	 * 관리자 질문 전체 조회
	 * @return
	 */
	public List<QnAVO> SelectMQuestionList() throws RemoteException;
	
	/**
	 * 질문 상세 조회
	 * @param question_id
	 * @return
	 */
	public QuestionVO selectQuestiondetail(int question_id) throws RemoteException;
	
	/**
	 * 회원 질문 전체 조회
	 * @param question_id
	 * @return
	 */
	public List<QnAVO> SelectMyQuestionList(String mem_id) throws RemoteException;
	
	/**
	 * 질문 추가
	 * @param qv
	 * @return
	 */
	public int insertQuestion(QuestionVO qv) throws RemoteException;

	/**
	 * 질문 삭제
	 * @param qv
	 * @return
	 * @throws RemoteException
	 */
	public int deleteQuestion(QuestionVO qvv) throws RemoteException;
}
