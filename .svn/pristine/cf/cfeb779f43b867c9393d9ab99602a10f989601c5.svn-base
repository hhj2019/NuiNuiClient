package kr.or.ddit.nn.view.event;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.event.eventService;
import kr.or.ddit.nn.vo.event.EventVO;

public class event_datail_controller implements Initializable {

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
	private JFXButton btn_ok;

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

		event_title.setEditable(false);
		event_date.setEditable(false);
		event_content.setEditable(false);
		event_eventdate.setEditable(false);

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

		/**
		 * 확인버튼 클릭 시
		 */
		btn_ok.setOnAction(e -> {
			changeScene("event_main.fxml");
		});
	}
}
