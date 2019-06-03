package kr.or.ddit.nn.view.searchPass;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import api.MailingAPI;
import api.RandomCodeGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.join.JoinService;
import kr.or.ddit.nn.service.searchPass.SearchPassService;
import kr.or.ddit.nn.vo.member.MemberVO;
import util.AlertUtil;

public class Search_Controller implements Initializable {

	private Registry reg;
	private JoinService join;
	private SearchPassService search;

	@FXML
	TextField search_inputId;
	@FXML
	Button btn_submitPass;
	@FXML
	Button btn_searchCancel;
	@FXML
	TextField search_inputName;
	@FXML
	TextField search_inputEmail;

	String random = null;
	
	ArrayList<MemberVO> mList;
	MemberVO mvo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			join = (JoinService) reg.lookup("JoinService");
			search = (SearchPassService) reg.lookup("SearchPassService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void passSubmit(MouseEvent event) throws RemoteException {

		if (search_inputId.getText().equals("") || search_inputId.getText() == null) {
			AlertUtil.errMsg("아이디 에러!", "아이디를 입력하세요");
			return;
		}

		if (search_inputName.getText().equals("") || search_inputName.getText() == null) {
			AlertUtil.errMsg("이름 에러!", "이름을 입력하세요");
			return;
		}

		if (search_inputEmail.getText().equals("") || search_inputEmail.getText() == null) {
			AlertUtil.errMsg("이메일 에러!", "이메일을 입력하세요");
			return;
		}
		
		
		mList = new ArrayList<>();
		mvo = new MemberVO();

		// 1. 아이디 비교
		mvo.setMem_id(search_inputId.getText()); // mvo객체에 입력한 아이디 입력
		mList = (ArrayList<MemberVO>) join.selectId(mvo);
		if(mList.size() > 0) {
			mvo = mList.get(0);
			System.out.println("찾고자하는 회원 아이디 : " + mList.get(0).getMem_id());
		} else {
			AlertUtil.errMsg("아이디 에러", "입력한 ID정보를 확인하세요.");
			return;
		}
		
		// 2. 이름 비교
		mvo.setMem_name(search_inputName.getText());
		mList = (ArrayList<MemberVO>) join.selectName(mvo);
		if(mList.size() > 0) {
			mvo = mList.get(0);
			System.out.println("찾고자하는 회원 이름 : " + mList.get(0).getMem_name());
		} else {
			AlertUtil.errMsg("이름 에러!", "입력한 이름을 확인하세요.");
			return;
		}
		
		// 3. 이메일 비교
		mvo.setMem_email(search_inputEmail.getText());
		mList = (ArrayList<MemberVO>) join.selectEmail(mvo);
		if(mList.size() > 0) {
			mvo = mList.get(0);
			System.out.println("찾고자하는 회원 이메일 : " + mList.get(0).getMem_email());
		} else {
			AlertUtil.errMsg("이메일 에러!", "입력한 이메일을 확인하세요.");
			return;
		}
		
		random = RandomCodeGenerator.generate(); // random 패스워드 생성
		MailingAPI.sendMail(mvo.getMem_email(), random, "register");
		AlertUtil.infoMsg("비밀번호 이메일 발송", "임시로 발급된 비밀번호가 발송되었습니다.");
		mvo.setMem_pass(random);

		int cnt = search.searchPass(mvo);

		if (cnt > 0) {
			AlertUtil.infoMsg("비밀번호 전송 완료!", "새로 생성된 비밀번호가 전송되었습니다.");
			Stage searchPassStage= (Stage) ((Node) event.getSource()).getScene().getWindow(); // 비밀번호 찾기 화면
			searchPassStage.close();
		} else {
			AlertUtil.errMsg("비밀번호 전송 필패!", "비밀번호 업데이트를 실패했습니다.");
			return;
		}
		
	}

	@FXML
	public void searchCancel(MouseEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.close();
	}

}
