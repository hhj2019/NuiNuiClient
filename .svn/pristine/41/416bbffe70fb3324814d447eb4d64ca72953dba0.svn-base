package kr.or.ddit.nn.service.faq;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.nn.vo.faq.FAQVO;
import kr.or.ddit.nn.vo.faq.FAQ_OSearchVO;
import kr.or.ddit.nn.vo.faq.FAQ_OSearchVO2;
import kr.or.ddit.nn.vo.faq.FAQ_SearchVO;

public interface FAQService extends Remote{

	/**
	 * 자주 묻는 질문 전체 조회
	 * @return
	 */
	public List<FAQVO> selectAllFAQ() throws RemoteException;
	
	/**
	 * 자주 묻는 질문 선택 조회
	 * @param FAQ_id
	 * @return
	 */
	public FAQVO selectFAQdetail(int FAQ_id) throws RemoteException;
	
	/**
	 * 최근 자주 묻는 질문 조회
	 * @return
	 * @throws RemoteException
	 */
	public List<FAQVO> selectNewFAQ() throws RemoteException;
	
	/**
	 * 자주 묻는 질문 추가
	 * @param fv
	 * @return
	 */
	public int insertFAQ(FAQVO fv) throws RemoteException;
	
	/**
	 * 자주묻는질문 수정
	 * @param fv
	 * @return
	 */
	public int updateFAQ(FAQVO fv) throws RemoteException;
	
	/**
	 * 자주 묻는 질문 삭제
	 * @param FAQ_id
	 * @return
	 */
	public int deleteFAQ(int FAQ_id) throws RemoteException;
	
	/**
	 * 자주묻는질문 제목 + 내용 검색
	 * @param fsv
	 * @return
	 */
	public List<FAQVO> allSearchFaq(FAQ_SearchVO fsv) throws RemoteException;
	
	/**
	 * 자주묻는질문 제목 검색
	 * @param fosv
	 * @return
	 */
	public List<FAQVO> titleSearchFaq(FAQ_OSearchVO fosv) throws RemoteException;
	
	/**
	 * 자주묻는질문 내용 검색
	 * @param fosv2
	 * @return
	 */
	public List<FAQVO> contentSearchFaq(FAQ_OSearchVO2 fosv2) throws RemoteException;
}
