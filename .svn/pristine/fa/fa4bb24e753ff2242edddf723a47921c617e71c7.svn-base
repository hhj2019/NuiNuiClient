package kr.or.ddit.nn.view.CustomerService.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.vo.notice.NoticeVO;
import com.jfoenix.controls.JFXButton;

public class CS_detail_controller implements Initializable {

	public static int notice_id;

	private Registry reg;
	private NoticeService notice;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXTextField notice_title;
	@FXML
	private JFXTextField notice_date;
	@FXML
	private JFXTextArea notice_content;
	@FXML
	private JFXButton notice_ok_btn;

	NoticeVO notice_vo;

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
			notice = (NoticeService) reg.lookup("noticeService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		// textfield 비활성화
		notice_title.setEditable(false);
		notice_date.setEditable(false);
		notice_content.setEditable(false);

		/**
		 * 공지사항 상세조회
		 */
		try {
			notice_vo = notice.selectNoticedetail(notice_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		notice_title.setText(notice_vo.getNotice_title());
		notice_date.setText(notice_vo.getNotice_date());
		notice_content.setText(notice_vo.getNotice_content());

		/**
		 * 확인버튼 클릭 시
		 */
		notice_ok_btn.setOnAction(e -> {
			changeScene("CS_notice.fxml");
		});
	}
}
