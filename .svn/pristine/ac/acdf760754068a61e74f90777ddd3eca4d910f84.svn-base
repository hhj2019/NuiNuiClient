package kr.or.ddit.nn.view.manager.qna;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import kr.or.ddit.nn.service.answer.AnswerService;
import kr.or.ddit.nn.service.question.QuestionService;
import kr.or.ddit.nn.vo.qna.AnswerVO;
import kr.or.ddit.nn.vo.qna.QnAVO;
import kr.or.ddit.nn.vo.qna.QuestionVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import api.MailingAPI;

public class qna_detail_controller implements Initializable {
	public static int question_id;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane in_pane;
	@FXML
	private JFXButton insert_answer;
	@FXML
	private JFXTextField question_title;
	@FXML
	private JFXTextField question_date;
	@FXML
	private JFXTextField question_memid;
	@FXML
	private JFXTextField question_category;
	@FXML
	private JFXTextArea question_content;
	@FXML
	private JFXTextField answer_title;
	@FXML
	private JFXTextField answer_date;
	@FXML
	private JFXTextArea answer_content;

	private Registry reg;
	private	QuestionService question;
	private AnswerService answer;
	
	QuestionVO qv;
	AnswerVO av;
	QnAVO qav;
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(in_pane);
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
			answer =  (AnswerService) reg.lookup("answerService");
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
		answer_date.setEditable(false);
		
		av = new AnswerVO();
		qv = new QuestionVO();
		qav = new QnAVO();

		try {
			//답변이 있는지 확인 -> 없을시 질문만 출력
			if(answer.SelectAnswerOK(question_id) == 0) {
				qv = question.selectQuestiondetail(question_id);
				
				question_title.setText(qv.getQuestion_title());
				question_date.setText(qv.getQuestion_date());
				question_memid.setText(qv.getMem_id());
				question_category.setText(qv.getQuestion_category());
				question_content.setText(qv.getQuestion_content());
				
				JFXButton back_btn = new JFXButton();
				back_btn.setText("목록 보기");
				back_btn.setLayoutX(383);
				back_btn.setLayoutY(450);
				back_btn.setStyle("-fx-border-color: rgb(140,0,140); -fx-border-radius: 3;");
				back_btn.setPrefWidth(100);
				back_btn.setPrefHeight(30);
				in_pane.getChildren().add(back_btn);
				
				back_btn.setOnAction(e->{
					changeScene("qna_main.fxml");											
				});
				
				//답변 추가 버튼 클릭 시
				insert_answer.setOnAction(e->{
					if(answer_title.getText().isEmpty() || answer_content.getText().isEmpty()) {
						errMsg("추가 실패", "빈 항목이 존재합니다.");
						
					}else {
						av.setAnswer_title(answer_title.getText());
						av.setAnswer_content(answer_content.getText());
						av.setQuestion_id(question_id);
						try {
							answer.insertAnswer(av);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						OKMsg("추가완료", "답변이 작성되었습니다." + "\n\n회원에게  메일을 전송합니다.");
						changeScene("Manager_qna_detil.fxml");											
						MailingAPI.sendMail(qv.getMem_email(), qv.getQuestion_title(), "QnA");
					}
				});
				
			//답변이 있을 시 -> 전체 출력 후 목록보기 버튼
			}else if (answer.SelectAnswerOK(question_id) == 1) {
				Label date = new Label();
				date.setText("작성일");
				date.setLayoutX(443);
				date.setLayoutY(231);
				in_pane.getChildren().add(date);
				insert_answer.setText("목록보기");
				
				qav = answer.selectAnswerdetail(question_id);
				
				question_title.setText(qav.getQuestion_title());
				question_date.setText(qav.getQuestion_date());
				question_memid.setText(qav.getMem_id());
				question_category.setText(qav.getQuestion_category());
				question_content.setText(qav.getQuestion_content());
				
				answer_title.setText(qav.getAnswer_title());
				answer_date.setText(qav.getAnswer_date());
				answer_content.setText(qav.getAnswer_content());
				
				answer_title.setEditable(false);
				answer_title.setFocusColor(Paint.valueOf("rgb(240,240,240)"));
				answer_content.setEditable(false);
				answer_content.setFocusColor(Paint.valueOf("rgb(240,240,240)"));
				
				insert_answer.setOnAction(e->{
					changeScene("qna_main.fxml");
				});
				//버튼 생성
				JFXButton delete_btn = new JFXButton();
				delete_btn.setText("답변 삭제");
				delete_btn.setLayoutX(383);
				delete_btn.setLayoutY(450);
				delete_btn.setStyle("-fx-border-color: rgb(140,0,140); -fx-border-radius: 3;");
				delete_btn.setPrefWidth(100);
				delete_btn.setPrefHeight(30);
				in_pane.getChildren().add(delete_btn);
				delete_btn.setOnAction(e->{
					try {
						answer.deleteAnswer(question_id);
						OKMsg("삭제완료", "질문이 삭제되었습니다.");
						changeScene("Manager_qna_detil.fxml");											
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				});
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
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
