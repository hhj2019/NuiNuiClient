package kr.or.ddit.nn.view.manager.notice;

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

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.vo.notice.NoticeVO;

public class Notice_insert_controller implements Initializable {
	private Registry reg;
	private NoticeService notice;
	
	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField notice_title;
	@FXML
	private JFXTextArea notice_content;
	@FXML
	private JFXButton insert_btn;
	@FXML
	private JFXButton cancel_btn;

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
			notice = (NoticeService) reg.lookup("noticeService");
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
		NoticeVO nv = new NoticeVO();
		
		nv.setNotice_title(notice_title.getText());
		nv.setNotice_content(notice_content.getText());
		
		if(notice_title.getText().isEmpty() || notice_content.getText().isEmpty()) {
			errMsg("추가실패", "빈 항목이 존재합니다.");
			return;
		}else {
			try {
				notice.insertNotice(nv);
			}catch (IOException e) {
				e.printStackTrace();
			}
			OKMsg("추가성공", "공지사항을 추가했습니다.");
			changeScene("Manager_Notice.fxml");
		}
	}
	
	/**
	 * 닫기 버튼
	 */
	@FXML
	public void close_btn() {
		changeScene("Manager_Notice.fxml");
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
