package kr.or.ddit.nn.view.CustomerService.qna;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.question.QuestionService;
import kr.or.ddit.nn.vo.qna.QuestionVO;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class CS_insert_controller implements Initializable {
	private Registry reg;
	private QuestionService question;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton question_ok_btn;
	@FXML
	private JFXButton question_cancel_btn;
	@FXML
	private JFXRadioButton login_question;
	@FXML
	private JFXRadioButton down_question;
	@FXML
	private JFXRadioButton ticket_question;
	@FXML
	private JFXRadioButton event_question;
	@FXML
	private JFXRadioButton use_question;
	@FXML
	private JFXRadioButton others_question;
	@FXML
	private JFXTextField question_title;
	@FXML
	private JFXTextArea question_content;

	String question_category = "";

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(center_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main_pane.setCenter(parent);
		return loader;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			question = (QuestionService) reg.lookup("questionService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		ToggleGroup group = new ToggleGroup();
		login_question.setToggleGroup(group);
		login_question.setUserData("회원/로그인");
		down_question.setToggleGroup(group);
		down_question.setUserData("다운로드");
		ticket_question.setToggleGroup(group);
		ticket_question.setUserData("이용권");
		event_question.setToggleGroup(group);
		event_question.setUserData("이벤트");
		use_question.setToggleGroup(group);
		use_question.setUserData("이용방법");
		others_question.setToggleGroup(group);
		others_question.setUserData("기타질문");

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (group.getSelectedToggle() != null) {
					question_category = group.getSelectedToggle().getUserData().toString();
				}
			}
		});
	}

	@FXML
	public void insert_report() {
		QuestionVO qv = new QuestionVO();

		qv.setQuestion_title(question_title.getText());
		qv.setQuestion_category(question_category);
		qv.setQuestion_content(question_content.getText());
		qv.setMem_id(Session.getUser().getMem_id());

		if (question_title.getText().isEmpty() || question_content.getText().isEmpty() || question_category.isEmpty()) {
			errMsg("등록실패", "빈 항목이 존재합니다.");
		} else {
			try {
				question.insertQuestion(qv);
			} catch (IOException e) {
				e.printStackTrace();
			}
			OKMsg("등록성공", "질문이 등록되었습니다.");
			changeScene("CS_qna_main.fxml");
		}
	}

	@FXML
	public void cancel_report() {
		changeScene("CS_qna_main.fxml");
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
