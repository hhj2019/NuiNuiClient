package kr.or.ddit.nn.view.CustomerService.qna;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.answer.AnswerService;
import kr.or.ddit.nn.service.question.QuestionService;
import kr.or.ddit.nn.vo.qna.QnAVO;
import kr.or.ddit.nn.vo.qna.QuestionVO;

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

public class CS_detail_controller implements Initializable {

	public static int question_id;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton qna_ok_btn;
	@FXML
	private JFXButton delete_btn;
	@FXML
	private JFXTextField question_title;
	@FXML
	private JFXTextField question_date;
	@FXML
	private JFXTextField question_category;
	@FXML
	private JFXTextField question_memid;
	@FXML
	private JFXTextArea question_content;
	@FXML
	private JFXTextField answer_title;
	@FXML
	private JFXTextField answer_date;
	@FXML
	private JFXTextArea answer_content;

	private Registry reg;
	private QuestionService question;
	private AnswerService answer;

	QuestionVO qv;
	QuestionVO qvv;
	QnAVO qav;

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

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			answer = (AnswerService) reg.lookup("answerService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		question_title.setEditable(false);
		question_date.setEditable(false);
		question_memid.setEditable(false);
		question_category.setEditable(false);
		question_content.setEditable(false);
		answer_title.setEditable(false);
		answer_date.setEditable(false);
		answer_content.setEditable(false);

		qv = new QuestionVO();
		qvv = new QuestionVO();
		qav = new QnAVO();

		try {
			if (answer.SelectAnswerOK(question_id) == 0) {
				qv = question.selectQuestiondetail(question_id);

				question_title.setText(qv.getQuestion_title());
				question_date.setText(qv.getQuestion_date());
				question_memid.setText(qv.getMem_id());
				question_category.setText(qv.getQuestion_category());
				question_content.setText(qv.getQuestion_content());

				answer_content.setText("아직 답변이 없습니다");

				delete_btn.setOnAction(e->{
					try {
						question.deleteQuestion(qv);
						OKMsg("삭제 완료", "질문이 삭제되었습니다.");
						changeScene("CS_qna_main.fxml");
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				});
			} else if (answer.SelectAnswerOK(question_id) == 1) {
				qav = answer.selectAnswerdetail(question_id);

				question_title.setText(qav.getQuestion_title());
				question_date.setText(qav.getQuestion_date());
				question_memid.setText(qav.getMem_id());
				question_category.setText(qav.getQuestion_category());
				question_content.setText(qav.getQuestion_content());

				answer_title.setText(qav.getAnswer_title());
				answer_date.setText(qav.getAnswer_date());
				answer_content.setText(qav.getAnswer_content());
				
				delete_btn.setOnAction(e->{
					try {
						qvv.setMem_id(Session.getUser().getMem_id());
						qvv.setQuestion_id(question_id);
//						쿼리문 확인
						question.deleteQuestion(qvv);
						OKMsg("삭제 완료", "질문이 삭제되었습니다.");
						changeScene("CS_qna_main.fxml");
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				});
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void qna_ok() {
		changeScene("CS_qna_main.fxml");
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
