package kr.or.ddit.nn.view.manager.event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import kr.or.ddit.nn.service.event.eventService;
import kr.or.ddit.nn.vo.event.EventVO;

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

public class Event_detail_controller implements Initializable {

	public static int event_id;

	private Registry reg;
	private eventService event;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField event_title;
	@FXML
	private JFXTextField event_date;
	@FXML
	private JFXTextArea event_content;
	@FXML
	private JFXTextField event_eventdate;
	@FXML
	private JFXButton event_update;
	@FXML
	private JFXButton event_delete;
	@FXML
	private JFXButton update_ok;

	EventVO event_vo;

	/**
	 * 메인 화면 변경
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
			event = (eventService) reg.lookup("eventService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		// textfield 비활성화
		event_title.setEditable(false);
		event_date.setEditable(false);
		event_content.setEditable(false);
		event_eventdate.setEditable(false);
		// 수정하기 버튼 비활성화
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
			changeScene("manager_event.fxml");											
		});
		
		/**
		 * 이벤트 상세 조회
		 */
		try {
			event_vo = event.selectEventdetail(event_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		event_title.setText(event_vo.getEvent_title());
		event_date.setText(event_vo.getEvent_date());
		event_content.setText(event_vo.getEvent_content());
		event_eventdate.setText(event_vo.getEvent_eventdate());
		
		event_update.setOnAction(e -> {
			//버튼 활성화 변경
			update_ok.setDisable(false);
			event_update.setDisable(true);
			event_delete.setDisable(true);
			// textfield 활성화
			event_title.setEditable(true);
			event_content.setEditable(true);
			event_eventdate.setEditable(true);
			event_title.setFocusColor(Paint.valueOf("purple"));
			event_content.setFocusColor(Paint.valueOf("purple"));
			event_eventdate.setFocusColor(Paint.valueOf("purple"));
		});

		/**
		 * 이벤트 수정
		 */
		update_ok.setOnAction(e -> {
			event_vo.setEvent_id(event_id);
			event_vo.setEvent_title(event_title.getText());
			event_vo.setEvent_content(event_content.getText());
			event_vo.setEvent_eventdate(event_eventdate.getText());
			
			if (event_title.getText().isEmpty() || event_content.getText().isEmpty() || event_eventdate.getText().isEmpty()) {
				errMsg("수정실패", "빈 항목이 존재합니다.");
				return;
			} else {
				Alert updateReal = new Alert(AlertType.CONFIRMATION);
				updateReal.setTitle("이벤트 수정");
				updateReal.setHeaderText("수정");
				updateReal.setContentText("이벤트를 수정하시겠습니까?");
				ButtonType updateResult = updateReal.showAndWait().get();
				// OK버튼 클릭시
				if(updateResult == ButtonType.OK) {
					try {
						event.updateEvent(event_vo);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					OKMsg("수정완료", "이벤트를 수정했습니다.");
					event_update.setDisable(false);
					event_delete.setDisable(false);
					changeScene("manager_event.fxml");
				// cancle버튼 클릭시
				}else if(updateResult == ButtonType.CANCEL){
					// 버튼 활성화 변경
					update_ok.setDisable(true);
					event_update.setDisable(false);
					event_delete.setDisable(false);
					//textfield 비활성화
					event_title.setEditable(false);
					event_content.setEditable(false);
					event_eventdate.setEditable(false);
				}
			}
		});

		/**
		 * 이벤트 삭제
		 */
		event_delete.setOnAction(e -> {
			Alert deleteReal = new Alert(AlertType.CONFIRMATION);
			deleteReal.setTitle("이벤트 삭세");
			deleteReal.setHeaderText("삭제");
			deleteReal.setContentText("이벤트를을 삭제하시겠습니까?");
			ButtonType deleteResult = deleteReal.showAndWait().get();
			if(deleteResult == ButtonType.OK) {
				try {
					event.deleteEvent(event_vo.getEvent_id());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				OKMsg("삭제완료", "이벤트를 삭제했습니다.");
				changeScene("manager_event.fxml");
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
