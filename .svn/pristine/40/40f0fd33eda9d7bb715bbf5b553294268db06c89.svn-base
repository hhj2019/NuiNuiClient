package kr.or.ddit.nn.view.join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXRadioButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.nn.service.join.JoinService;
import kr.or.ddit.nn.vo.member.MemberVO;

public class Join_Controller implements Initializable {

	@FXML
	AnchorPane goodcom_anchor;
	@FXML
	ImageView img_auto;
	@FXML
	TextField txtf_id;
	@FXML
	Button btn_idchk;
	@FXML
	PasswordField txtf_pw;
	@FXML
	PasswordField txtf_pw2;
	@FXML
	TextField txtf_name;
	@FXML
	TextField txtf_birth;
	@FXML
	TextField txtf_phone;
	@FXML
	TextField txtf_nick;
	@FXML
	Button btn_nickchk;
	@FXML
	TextField txtf_code;
	@FXML
	Button btn_codechk; // 캡차 코드 확인
	@FXML
	Button btn_submit; // 회원가입 제출
	@FXML
	Button btn_cancel; // 회원가입 취소
	@FXML
	Button btn_refresh; // 이미지 새로고침
	@FXML
	TextField txtf_email; // 회원가입시 입력하는 email(정규식 처리)

	@FXML
	JFXRadioButton radio_man;
	@FXML
	JFXRadioButton radio_woman;
	@FXML
	Button btn_emailchk;

	private Registry reg;
	private JoinService join;

	List<MemberVO> list = new ArrayList<MemberVO>();
	private MemberVO mvo = new MemberVO();

	int chk_cnt = 0;
	int pattern_cnt = 0;
	int no_cnt = 0;
	boolean result = false;
	private boolean captchaFlag = false;
	private String captchaKey = "";
	File f = null; // captchaimg파일
	String captchaImg = ""; // captcha img 파일이름.

	AES256Util aes;

	String mem_nick = "";
	String mem_id = "";
	String mem_sex = "";
	String mem_email = "";

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

		captchaKey = captchaKey();
		captchaImage(captchaKey);

		/**
		 * 2. 아이디 체크(시작은 영문으로만, '_' 를 제외한 특수문자는 불가능. 영문,숫자,'_'로 이루어진 5~12자 이하
		 */
		btn_idchk.setOnAction(e -> {
			mem_id = txtf_id.getText().trim();
			Pattern p2 = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
			Matcher m2 = p2.matcher(mem_id);
			result = m2.matches();

			if (txtf_id.getText().equals("") || txtf_id.getText() == null) {
				errMsg("아이디 입력에러!", "아이디를 입력해주세요.");
				txtf_id.requestFocus();
				return;
			} else if (result == false) {
				errMsg("아이디 입력에러!", "아이디를 형식에 맞게 입력해주세요.");
				txtf_id.requestFocus();
				return;
			}

			ArrayList<MemberVO> mList = new ArrayList<>();
			MemberVO mvo = new MemberVO();
			mvo.setMem_id(mem_id);

			try {
				mList = (ArrayList<MemberVO>) join.selectId(mvo);
			} catch (RemoteException e4) {
				e4.printStackTrace();
			}

			System.out.println(mList.size());
			if (mList.size() == 0) {
				infoMsg("중복체크", "사용하실 수 있는 아이디 입니다.");
				chk_cnt++; // chk_cnt++
				pattern_cnt++; // pattern 2
				System.out.println("아이디 중복체크" + chk_cnt);
			} else {
				errMsg("중복체크", "이미 사용하고 있는 아이디 입니다.");
				txtf_id.requestFocus();
				return;
			}

		});

		/**
		 * 6. 닉네임 정규식 버튼
		 */
		btn_nickchk.setOnAction(ee -> {
			mem_nick = txtf_nick.getText();
			Pattern p6 = Pattern.compile("^[가-힣]*$");
			Matcher m6 = p6.matcher(mem_nick);
			result = m6.matches();

			if (mem_nick.equals("") || mem_nick == null) {
				errMsg("에러!", "닉네임을 입력하지 않으셨습니다!");
				no_cnt++;
				txtf_nick.requestFocus();
				return;
			} else if (result == false) {
				errMsg("에러!", "형식에 맞지 않는 닉네임 입니다!");
				txtf_nick.requestFocus();
				return;
			}

			ArrayList<MemberVO> mList = new ArrayList<>();
			MemberVO mvo = new MemberVO();
			mvo.setMem_nick(mem_nick);

			try {
				mList = (ArrayList<MemberVO>) join.selectNick(mvo);
			} catch (RemoteException e4) {
				e4.printStackTrace();
			} // 여기 에러

			if (mList.size() == 0) {
				infoMsg("중복체크", "사용하실 수 있는 닉네임 입니다.");
				chk_cnt++; // cnk_cnt 2
				pattern_cnt++; // nick 6
				System.out.println("닉네임 중복체크" + chk_cnt);
			} else {
				errMsg("중복체크", "이미 사용하고 있는 닉네임 입니다.");
				txtf_nick.requestFocus();
				return;
			}

		});

		/**
		 * 성별선택
		 */
		ToggleGroup group = new ToggleGroup();
		radio_man.setToggleGroup(group);
		radio_man.setUserData("남성");
		radio_woman.setToggleGroup(group);
		radio_woman.setUserData("여성");

		/**
		 * 성별선택버튼 토글그룹 지정
		 */
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (group.getSelectedToggle() != null) {
					mem_sex = group.getSelectedToggle().getUserData().toString();
					System.out.println(group.getSelectedToggle().getUserData().toString());
				}
			}
		});

		/**
		 * 7.이메일 체크 버튼
		 */
		btn_emailchk.setOnAction(e -> {
			mem_email = txtf_email.getText();
			Pattern p7 = Pattern.compile("\\w*@[a-z]*\\.[a-z]{3}");
			Matcher m7 = p7.matcher(mem_email);
			result = m7.matches();

			if (txtf_email.getText().equals("") || txtf_email.getText() == null) {
				errMsg("이메일 입력에러", "이메일을 입력해주십시오.");
				txtf_email.requestFocus();
				return;
			} else if (result == false) {
				errMsg("이메일 형식에러", "형식에 맞춰서 이메일을 입력해주세요.");
				txtf_email.requestFocus();
				return;
			}

			// email 체크를 위한 list
			ArrayList<MemberVO> mList = new ArrayList<>();
			MemberVO mvo = new MemberVO();
			mvo.setMem_email(mem_email);

			try {
				mList = (ArrayList<MemberVO>) join.selectEmail(mvo);
			} catch (RemoteException e4) {
				e4.printStackTrace();
			}
			System.out.println("이메일 계정 갯수 확인 " + mList.size());

			if (mList.size() == 0) {
				infoMsg("중복체크", "사용하실 수 있는 이메일입니다.");
				chk_cnt++; // chk_cnt 3
				pattern_cnt++;
				System.out.println("이메일 중복체크" + chk_cnt);
			} else {
				errMsg("중복체크", "이미 사용하고 있는 이메일 입니다.");
				txtf_email.requestFocus();
				return;
			}

		});

		btn_codechk.setOnAction(e1 -> {
			System.out.println("코드체크 클릭");
			captchaCheck();
		});

		btn_refresh.setOnAction(e2 -> {
			imgRefresh();
		});

		btn_submit.setOnAction(e3 -> {

			/**
			 * 1. 이름 정규식 한글만 가능 /^[가-힣]+$/
			 */

			String mem_name = txtf_name.getText().trim();
			Pattern p1 = Pattern.compile("^[가-힣]*$");
			Matcher m1 = p1.matcher(mem_name);
			// System.out.println(result);
			result = m1.matches();
			// System.out.println(result);

			System.out.println("제출클릭");
			if (result) {
				pattern_cnt++; // pattern 1
				System.out.println("이름 정규화" + pattern_cnt);
			} else if (mem_name.equals("") || mem_name == null) {
				errMsg("이름 입력에러!", "이름을 입력하지 않으셨습니다.");
				txtf_name.requestFocus();
				no_cnt++;
				return;
			} else if (result == false) {
				errMsg("에러!", "이름은 한글만 가능합니다.");
				txtf_name.requestFocus();
				return;
			}

			/**
			 * 3. 비밀번호 맞으면 pattern_cnt 증가
			 */
			// String mem_id = txtf_id.getText();
			String mem_pw = txtf_pw.getText();
			String mem_pw2 = txtf_pw2.getText();

			if (mem_pw.equals(mem_pw2)) {
				pattern_cnt++; // pattern 3
				System.out.println("비밀번호 일치" + pattern_cnt);

			} else {
				errMsg("비밀번호 오류", " 입력하신 비밀번호가 일치하지 않습니다!");
				txtf_pw.requestFocus();
				return;
			}

			/**
			 * 4. 생년월일 ex)941218 ==> '-' 쓰면 안됨 [0-9]{6}
			 */

			String mem_birth = txtf_birth.getText();
			Pattern p4 = Pattern.compile("[0-9]{6}");
			Matcher m4 = p4.matcher(mem_birth);
			result = m4.matches();

			if (result) {
				pattern_cnt++; // pattern 4
				System.out.println("생년월일 정규화" + pattern_cnt);
			} else if (mem_birth.equals("") || mem_birth == null) {
				errMsg("생년월일 입력에러!", "생년월일을 입력하지 않으셨습니다.");
				txtf_birth.requestFocus();
				no_cnt++;
				return;
			} else if (result == false) {
				errMsg("생년월일 입력에러!", "생년월일을 형식에 맞게 입력하여 주십시오");
				txtf_birth.requestFocus();
				return;
			}

			/**
			 * 5. 핸드폰 번호 정규식 [0-9]{11}
			 */
			String mem_hp = txtf_phone.getText();

			Pattern p5 = Pattern.compile("[0-9]{11}");
			Matcher m5 = p5.matcher(mem_hp);
			result = m5.matches();

			if (result) {
				pattern_cnt++; // pattern 5
				System.out.println("핸드폰 정규화" + pattern_cnt);
			} else if (mem_hp.equals("") || mem_hp == null) {
				errMsg("핸드폰번호 입력에러!", "핸드폰 번호를 입력하지 않으셨습니다!");
				txtf_phone.requestFocus();
				no_cnt++;
				return;
			} else if (result == false) {
				errMsg("핸드폰번호 입력에러!", "핸드폰 번호를 입렬할 땐 '-' 을 사용하지 말아주세요.");
				txtf_phone.requestFocus();
				return;
			}

			/**
			 * pattern_cnt == 7 인지 확인하고 맞으면 넘어감.
			 */
			if (chk_cnt == 4 && pattern_cnt == 7 && no_cnt == 0) {

				int cnt = 0;
				mvo.setMem_id(mem_id);
				mvo.setMem_pass(mem_pw);
				mvo.setMem_name(mem_name);
				mvo.setMem_email(mem_email);
				mvo.setMem_tel(mem_hp);
				mvo.setMem_sex(mem_sex);
				mvo.setMem_reg(mem_birth);
				mvo.setMem_nick(mem_nick);
				// }

				try {
					cnt = join.insertMember(mvo);
				} catch (RemoteException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				System.out.println("회원가입 결과 ? " + cnt);

				if (cnt > 0) {
					infoMsg("회원가입 성공", "어서오세요! 늬늬뮤직입니다 :) ");

					Stage stage = (Stage) btn_submit.getScene().getWindow();
					stage.close();

					/**
					 * 로그인창으로 돌아가기
					 */

				} else {
					errMsg("회원가입 실패 ㅠㅠ", "(쿼리에)회원가입에 문제가 있습니다. :( ");
				}

			} else {
				errMsg("회원가입 실패", "입력하신 정보 중 올바르지 않은 정보가 있습니다. 아이디, 닉네임, 캡차 확인 버튼을 다시 눌러주세요");
				chk_cnt = 0;
				pattern_cnt = 0;
				no_cnt = 0;
			}
			//
		});

		/**
		 * 로그인창에서 처리해주고 있어서 생략.
		 */
		btn_cancel.setOnAction(e5 -> {
			
			 Stage stage = (Stage) btn_cancel.getScene().getWindow();
			 stage.close();
		});
	}

	/**
	 * 로그인창으로 돌아가기
	 */

	/**
	 * naver에서 발급한 이미지에 있는 내용과 입력한 내용이 같은지 비교하는 메서드
	 */
	public void captchaCheck() {

		String result1 = captchaResult(captchaKey, txtf_code.getText());
		if (txtf_code.getText().equals("")) {
			captchaFlag = false;
			errMsg("에러!", "좌측에 보이는 이미지의 문자를 입력해주세요");
		} else if (result1.equals("false")) {
			imgRefresh();
			captchaFlag = false;
			errMsg("에러!", "좌측에 보이는 이미지의 문자와 입력하신 문자가 다릅니다!");
		} else if (result1.equals("true,")) {
			captchaFlag = true;
			infoMsg("성공!", "일치합니다!");
			chk_cnt++; // chk_cnt 4
			System.out.println("캡차 중복체크 : " + chk_cnt);
		}
	}

	/**
	 * 발급받은 Client Id와 key를 이용하여 이미지 생성
	 * 
	 * @param CaptchaKey
	 */
	public void captchaImage(String CaptchaKey) {
		// String clientId = "8ZR6IilZ2vS3zY0NRsvU";// 애플리케이션 클라이언트 아이디값"; 원본임.
		String clientId = "AS20GiwnCaGwfD9143ji";// 애플리케이션 클라이언트 아이디값"; 형욱이꺼
		// String clientSecret = "OAFp6DVRwg";// 애플리케이션 클라이언트 시크릿값";
		String clientSecret = "hT5Hcbm4G8";// 애플리케이션 클라이언트 시크릿값"; 형욱이꺼
		try {
			String key = CaptchaKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				// 랜덤한 이름으로 파일 생성
				captchaImg = Long.valueOf(new Date().getTime()).toString();
				// 파일 저장위치 변경해보자 (d:/D_Other/복사본_Tulips.jpg)
				// join패키지에 넣자 일단.
				f = new File("captchaImg.jpg");
				f.createNewFile();
				OutputStream outputStream = new FileOutputStream(f);
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				is.close();
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		Image captcha = new Image("file:" + f.getAbsolutePath());
		img_auto.setImage(captcha);
	}

	/**
	 * 이용하여 Captcha key 발급
	 * 
	 * @return result
	 */
	public String captchaKey() {
		// String clientId = "8ZR6IilZ2vS3zY0NRsvU";// 애플리케이션 클라이언트 아이디값";
		String clientId = "AS20GiwnCaGwfD9143ji";// 애플리케이션 클라이언트 아이디값";
		// String clientSecret = "OAFp6DVRwg";// 애플리케이션 클라이언트 시크릿값";
		String clientSecret = "hT5Hcbm4G8";// 애플리케이션 클라이언트 시크릿값";
		String result = "";
		try {
			String code = "0"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
			String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			result = response.toString().split("\"")[3];
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	/**
	 * 새로 생성된 정보를 HttpURLConnection으로 가져옴
	 * 
	 * @param CaptchaKey
	 * @param input
	 * @return
	 */
	public String captchaResult(String CaptchaKey, String input) {
		// String clientId = "8ZR6IilZ2vS3zY0NRsvU";// 애플리케이션 클라이언트 아이디값";
		String clientId = "AS20GiwnCaGwfD9143ji";// 애플리케이션 클라이언트 아이디값";
		// String clientSecret = "OAFp6DVRwg";// 애플리케이션 클라이언트 시크릿값";
		String clientSecret = "hT5Hcbm4G8";// 애플리케이션 클라이언트 시크릿값";
		String result = "";
		try {
			String code = "1"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
			String key = CaptchaKey; // 캡차 키 발급시 받은 키값
			String value = input; // 사용자가 입력한 캡차 이미지 글자값
			String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value="
					+ value;

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			result = response.toString().substring(10, 15);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public void imgRefresh() {
		captchaKey = captchaKey();
		captchaImage(captchaKey);
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	private void infoMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("MelonPlate Id 찾기");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
