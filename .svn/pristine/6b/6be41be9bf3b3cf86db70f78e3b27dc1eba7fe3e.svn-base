package kr.or.ddit.nn.view.manager.faq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.vo.faq.FAQVO;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class FAQ_insert_Controller implements Initializable {

	private Registry reg;
	private FAQService faq;
	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField FAQ_title;
	@FXML
	private JFXTextArea Q_content;
	@FXML
	private JFXTextArea A_content;
	@FXML
	private JFXButton insert_btn;
	@FXML
	private JFXButton close_btn;

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		main_pane.setCenter(center_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		main_pane.setCenter(parent);
		return loader;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 추가 버튼
	 */
	@FXML
	public void insert_btn() {

		FAQVO fv = new FAQVO();

		fv.setFaq_title(FAQ_title.getText());
		fv.setQ_content(Q_content.getText());
		fv.setA_content(A_content.getText());

		if (FAQ_title.getText().isEmpty() || Q_content.getText().isEmpty() || A_content.getText().isEmpty()) {
			errMsg("추가실패", "빈 항목이 존재합니다.");
			return;
		} else {
			try {
				faq.insertFAQ(fv);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			OKMsg("추가성공", "자주묻는질문을 추가했습니다.");
			changeScene("Manager_FAQ.fxml");
		}
	}

	/**
	 * 닫기 버튼
	 */
	@FXML
	public void close_btn() {
		changeScene("Manager_FAQ.fxml");
	}

	/**
	 * 에러메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	/**
	 * 완료메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void OKMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("성공");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
}
