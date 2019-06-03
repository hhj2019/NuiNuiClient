package kr.or.ddit.nn.view.manager.event;

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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.event.eventService;
import kr.or.ddit.nn.vo.event.EventVO;

public class Event_insert_controller implements Initializable {
	private Registry reg;
	private eventService event;

	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField event_title;
	@FXML
	private JFXTextArea event_content;
	@FXML
	private JFXButton insert_btn;
	@FXML
	private JFXButton cancel_btn;
	@FXML
	private JFXTextField event_eventdate;

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
			event = (eventService) reg.lookup("eventService");
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
		EventVO ev = new EventVO();

		ev.setEvent_title(event_title.getText());
		ev.setEvent_content(event_content.getText());
		ev.setEvent_eventdate(event_eventdate.getText());

		if (event_title.getText().isEmpty() || event_content.getText().isEmpty() || event_eventdate.getText().isEmpty()) {
			errMsg("추가실패", "빈 항목이 존재합니다.");
			return;
		} else {
			try {
				event.insertEvent(ev);
			} catch (IOException e) {
				e.printStackTrace();
			}
			OKMsg("추가성공", "이벤트를 추가했습니다.");
			changeScene("manager_event.fxml");
		}
	}

	/**
	 * 닫기 버튼
	 */
	@FXML
	public void close_btn() {
		changeScene("manager_event.fxml");
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
