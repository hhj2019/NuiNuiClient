package kr.or.ddit.nn.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.nn.main.ClientMain;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.join.JoinService;
import kr.or.ddit.nn.vo.member.MemberVO;
import util.AlertUtil;

public class LoginController implements Initializable {

	public static Stage loginStaticStage;

	List<MemberVO> mList = new ArrayList<MemberVO>();
	private MemberVO mvo = new MemberVO();
	String mem_id = "";
	String mem_pass = "";

	private double xOffset = 0;
	private double yOffset = 0;

	private Registry reg;
	private JoinService join;

	@FXML
	Label btn_login;
	@FXML
	Label btn_signup;
	@FXML
	Circle close;
	@FXML
	Circle min;
	@FXML
	Label btn_restore;
	@FXML
	JFXTextField input_id;
	@FXML
	JFXPasswordField input_pass;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			join = (JoinService) reg.lookup("JoinService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void signup(MouseEvent event) throws IOException {

		System.out.println("회원가입 클릭!");
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // 로그인 화면

		Stage signupStage = new Stage(StageStyle.UTILITY); // 새로 띄워줄 화면
		signupStage.initModality(Modality.APPLICATION_MODAL);
		signupStage.initOwner(primaryStage);

		Parent newParent = null;
		newParent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/view/join/member_join_form.fxml")); // 에러뜨는

		Scene scene = new Scene(newParent);
		signupStage.setScene(scene);
		signupStage.show();

	}

	///////////////////////////////////////////////// 잠시 주석
	// /**
	// * 로그인 버튼 클릭 시 실행(로그인창 -> 메인화면)
	// *
	// * @param event
	// * @throws IOException
	// */
	@FXML
	public void login(MouseEvent event) throws IOException {
		System.out.println("로그인 버튼 클릭");
		mem_id = input_id.getText().trim();
		mem_pass = input_pass.getText().trim();
		if (input_id.getText().equals("") || input_id.getText() == null) {
			AlertUtil.errMsg("에러!", "아이디를 입력하세요");
			input_id.requestFocus();
			return;
		} else if (input_pass.getText().equals("") || input_pass.getText() == null) {
			AlertUtil.errMsg("에러!", "비밀번호 입력하세요");
			input_pass.requestFocus();
			return;
		}

		ArrayList<MemberVO> mList = new ArrayList<>();
		MemberVO mvo = new MemberVO();
		mvo.setMem_id(mem_id);

		mList = (ArrayList<MemberVO>) join.selectId(mvo);

		if (!(mem_id.equals(mList.get(0).getMem_id()) && mem_pass.equals(mList.get(0).getMem_pass()))) {
			AlertUtil.errMsg("회원정보 오류", "아이디/패스워드를 확인하세요");
			return;
		}
		
		if (join.selectMemLevel(mvo) == 1) {

			System.out.println("가입된 사용자 아이디는 : " + mList.size() + "명입니다."); // 0이면 가입실패, 1이면 가입성공

			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.close();

			Session.setUser(mList.get(0)); // 로그인된 사용자로 세션 지정
			System.out.println("일반 로그인 : " + Session.getUser().getMem_name());
			ClientMain.pStage.getScene()
					.setRoot(FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/main/Main.fxml")));

		} else if (join.selectMemLevel(mvo) == 9) {
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.close();

			Session.setUser(mList.get(0)); // 로그인된 사용자로 세션 지정
			System.out.println("일반 로그인 : " + Session.getUser().getMem_name());
			ClientMain.pStage.getScene()
					.setRoot(FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/view/manager/Manager_MAIN.fxml")));
		}
	}

	@FXML
	public void kakaoLogin(MouseEvent event) throws InterruptedException {
		KakaoLogin kakao = new KakaoLogin();
		kakao.openKaKao();
		loginStaticStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Stage primaryStage = (Stage) ((Node)
		// event.getSource()).getScene().getWindow();
		// Thread sleep 이용해서 처리...

		////////////////////////////////////////////////////////////

		// if (kakao.openKakao()) {
		// Thread.sleep(5000);
		// ArrayList<MemberVO> mList = new ArrayList<>();
		//
		// MemberVO mvo = new MemberVO();
		// System.out.println("사용자 이름 : " + kakao.userName);
		// mvo.setMem_name(kakao.userName);
		//
		// try {
		// mList = (ArrayList<MemberVO>) join.selectName(mvo);
		// } catch (RemoteException e) {
		// e.printStackTrace();
		// }
		//
		// if (mList.size() > 0) {
		// Session.setUser(mvo);
		// } else {
		// errMsg("카카오톡 로그인 에러!", "가입된 사용자가 없습니다2");
		// return;
		// }
		//
		// Stage primaryStage = (Stage) ((Node)
		// event.getSource()).getScene().getWindow();
		// primaryStage.close();
		//
		// } else {
		// System.out.println(kakao.userFlag);
		// System.out.println("사요나라");
		// return;
		// }

	}

	/**
	 * 로그인 창에서 비밀번호 찾기 클릭시 비밀번호찾기 모달창 띄워주기
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void searchPass(MouseEvent event) throws IOException {
		System.out.println("비밀번호찾기 모달로 이동!");

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // 로그인 화면

		Stage searchStage = new Stage(StageStyle.UTILITY); // 새로 띄워줄 화면
		searchStage.initModality(Modality.APPLICATION_MODAL);

		searchStage.initOwner(primaryStage);

		Parent newParent = null;

		newParent = FXMLLoader
				.load(getClass().getResource("/kr/or/ddit/nn/view/searchPass/member_searchPass_form.fxml"));

		Scene scene = new Scene(newParent);
		searchStage.setScene(scene);
		searchStage.show();

		Button btn_searchCancel = (Button) newParent.lookup("#btn_searchCancel");

		btn_searchCancel.setOnMouseClicked(e -> {
			searchStage.close();
		});
	}

	/**** close screen ****/
	@FXML
	public void closeclick(MouseEvent event) throws IOException {

		// System.exit(0);
		System.out.println("뭐고");

	}

	/**** minimize screen ****/
	@FXML
	public void minclick(MouseEvent event) throws IOException {

		((Stage) ((Circle) event.getSource()).getScene().getWindow()).setIconified(true);

	}

	// private void errMsg(String headerText, String msg) {
	// Alert errAlert = new Alert(AlertType.ERROR);
	// errAlert.setTitle("오류");
	// errAlert.setHeaderText(headerText);
	// errAlert.setContentText(msg);
	// errAlert.showAndWait();
	// }
	//
	// private void infoMsg(String headerText, String msg) {
	// Alert errAlert = new Alert(AlertType.INFORMATION);
	// errAlert.setTitle("NuiNui Id 찾기");
	// errAlert.setHeaderText(headerText);
	// errAlert.setContentText(msg);
	// errAlert.showAndWait();
	// }

}
