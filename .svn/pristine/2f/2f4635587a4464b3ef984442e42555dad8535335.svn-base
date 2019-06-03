package kr.or.ddit.nn.view.manager.faq;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.vo.faq.FAQVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class FAQ_detail_controller implements Initializable {

	public static int faq_id;

	private Registry reg;
	private FAQService faq;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField FAQ_title;
	@FXML
	private JFXTextField FAQ_date;
	@FXML
	private JFXTextArea Q_content;
	@FXML
	private JFXTextArea A_content;
	@FXML
	private JFXButton FAQ_update;
	@FXML
	private JFXButton FAQ_delete;
	@FXML
	private JFXButton update_ok;

	FAQVO faq_VO;

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(null);
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
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		//textfield 비활성화
		FAQ_title.setEditable(false);
		FAQ_date.setEditable(false);
		Q_content.setEditable(false);
		A_content.setEditable(false);
		//수정 버튼 비활성화
		update_ok.setDisable(true);

		JFXButton back_btn = new JFXButton();
		back_btn.setText("목록 보기");
		back_btn.setLayoutX(32);
		back_btn.setLayoutY(29);
		back_btn.setStyle("-fx-border-color: rgb(140,0,140); -fx-border-radius: 3;");
		back_btn.setPrefWidth(100);
		back_btn.setPrefHeight(30);
		center_pane.getChildren().add(back_btn);
		
		back_btn.setOnAction(e->{
			changeScene("Manager_FAQ.fxml");											
		});
		
		/**
		 * 자주묻는질문 상세 조회
		 */
		try {
			faq_VO = faq.selectFAQdetail(faq_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		FAQ_title.setText(faq_VO.getFaq_title());
		FAQ_date.setText(faq_VO.getFaq_date());
		Q_content.setText(faq_VO.getQ_content());
		A_content.setText(faq_VO.getA_content());

		/**
		 * 수정하기 클릭 시
		 */
		FAQ_update.setOnAction(e -> {
			//버튼 활성화 변경
			update_ok.setDisable(false);
			FAQ_update.setDisable(true);
			FAQ_delete.setDisable(true);
			// textfield 활성화
			FAQ_title.setEditable(true);
			Q_content.setEditable(true);
			A_content.setEditable(true);
			FAQ_title.setFocusColor(Paint.valueOf("purple"));
			Q_content.setFocusColor(Paint.valueOf("purple"));
			A_content.setFocusColor(Paint.valueOf("purple"));
		});

		/**
		 * 자주묻는질문 수정
		 */
		update_ok.setOnAction(e -> {
			faq_VO.setFaq_id(faq_id);
			faq_VO.setFaq_title(FAQ_title.getText());
			faq_VO.setQ_content(Q_content.getText());
			faq_VO.setA_content(A_content.getText());

			if (FAQ_title.getText().isEmpty() || Q_content.getText().isEmpty() || A_content.getText().isEmpty()) {
				errMsg("수정실패", "빈 항목이 존재합니다.");
				return;
			} else {
				Alert updateReal = new Alert(AlertType.CONFIRMATION);
				updateReal.setTitle("자주묻는질문 수정");
				updateReal.setHeaderText("수정");
				updateReal.setContentText("자주묻는질문을 수정하시겠습니까?");
				ButtonType updateResult = updateReal.showAndWait().get();
				//OK버튼 클릭시
				if(updateResult == ButtonType.OK) {
					try {
						faq.updateFAQ(faq_VO);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					OKMsg("수정완료", "자주묻는질문을 수정했습니다.");
					FAQ_update.setDisable(false);
					FAQ_delete.setDisable(false);
					changeScene("Manager_FAQ.fxml");
				// cancel버튼 클릭시
				}else if(updateResult == ButtonType.CANCEL){
					//버튼 활성화 변경
					update_ok.setDisable(true);
					FAQ_update.setDisable(false);
					FAQ_delete.setDisable(false);
					//textfield 비활성화
					FAQ_title.setEditable(false);
					Q_content.setEditable(false);
					A_content.setEditable(false);
				}
			}
		});

		/**
		 * 자주묻는질문 삭제
		 */
		FAQ_delete.setOnAction(e -> {
			Alert deleteReal = new Alert(AlertType.CONFIRMATION);
			deleteReal.setTitle("자주묻는질문 삭제");
			deleteReal.setHeaderText("삭제");
			deleteReal.setContentText("자주묻는질문을 삭제하시겠습니까?");
			ButtonType deleteResult = deleteReal.showAndWait().get();
			if(deleteResult == ButtonType.OK) {
				try {
					faq.deleteFAQ(faq_id);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				OKMsg("삭제완료", "자주묻는질문을 삭제했습니다.");
				changeScene("Manager_FAQ.fxml");
			}
		});
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
}
