package kr.or.ddit.nn.view.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import com.sun.javafx.application.LauncherImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.nn.main.ClientMain;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.join.JoinService;
import kr.or.ddit.nn.vo.member.MemberVO;

public class KakaoLogin {
	int cnt = 0;

	String accessToken = "";
	String userName = "";
	boolean userFlag = false;

	private Registry reg;
	private JoinService join;

	public void openKaKao() {
		// public boolean openKakao() {
		Stage kakaoStage = new Stage();
		kakaoStage.setTitle("카카오 로그인 테스트...");

		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();

		// String url = "http://chosam2.c11.kr/kakao_login.php";
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=f75903fe108dceb93484f19124269676&redirect_uri=http://chosam2.c11.kr/kakao_login_callback.php&response_type=code";
		webEngine.load(url);

		// String str = webView.getEngine().getDocument().
		/**
		 * webview가 화면 전환 할 때 마다 cnt++해서 몇번째 페이지 인지 확인 후 cnt == 2 되었을 때 메인페이지로 이동
		 */

		// asdf6243@naver.com

		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					// primaryStage.setTitle(webEngine.getLocation());
					System.out.println(newState);

					// NodeList nd = webEngine.getDocument().getElementsByTagName("body");
					// System.out.println(nd.item(0).getNodeValue());
					// System.out.println(webEngine.toString());

					System.out.println(webEngine.getDocument().getChildNodes().item(0).getTextContent());
					System.out.println("---------------");

					if (++cnt == 2) {

						// System.out.println(webEngine.getDocument().getChildNodes().item(0).getTextContent().substring(300,354));

						accessToken = webEngine.getDocument().getChildNodes().item(0).getTextContent().substring(270);
						System.out.println("accessToken : " + accessToken);

						try {
							sendGet();
						} catch (Exception e) {
							e.printStackTrace();
						}

						/**
						 * 사용자 정보가 제대로 반환됬는지 확인하는 부분.
						 */
						if (!(userName.equals("") || userName == null)) {
							userFlag = true;
							/////////////////////
							try {
								reg = LocateRegistry.getRegistry("localhost", 8888);
								join = (JoinService) reg.lookup("JoinService");
							} catch (RemoteException e) {
								e.printStackTrace();
							} catch (NotBoundException e) {
								e.printStackTrace();
							}

							ArrayList<MemberVO> mList = new ArrayList<>();

							MemberVO mvo = new MemberVO();
							System.out.println("사용자 이름 : " + userName);
							mvo.setMem_name(userName);

							try {
								mList = (ArrayList<MemberVO>) join.selectName(mvo);
							} catch (RemoteException e) {
								e.printStackTrace();
							}

							if (mList.size() > 0) {
								Session.setUser(mList.get(0));
								try {
									ClientMain.pStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/main/Main.fxml")));
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								kakaoStage.close(); // 카카오 로그인창 닫기
								LoginController.loginStaticStage.close(); // 로그인창 닫기
								
								// Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
								// primaryStage.close();
							} else {
								errMsg("카카오톡 로그인 에러!", "가입된 사용자가 없습니다2");
								return;
							}

							/////////////////////
						} else {
							errMsg("카카오 로그인 에러!", "가입된 사용자 정보가 없습니다!");
							return;
						}

						// System.out.println("성공");
						// 닫는 메서드 실행

					}

				}

			}
		});

		VBox vBox = new VBox(webView);
		Scene scene = new Scene(vBox, 960, 600);

		kakaoStage.setScene(scene);
		kakaoStage.show();

		// return true;
	}

	private void sendGet() throws Exception {
		String url = "https://kapi.kakao.com/v2/user/me";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// 전송방식
		con.setRequestMethod("GET");
		// Request Header 정의
		con.setRequestProperty("Authorization", "Bearer " + accessToken);
		con.setConnectTimeout(10000); // 컨텍션타임아웃 10초
		con.setReadTimeout(5000); // 컨텐츠조회 타임아웃 5총

		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println("조회결과 : " + response.toString().substring(43, 46));
		userName = response.toString().substring(43, 46);

		System.out.println(userName);

	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
}