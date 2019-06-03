package kr.or.ddit.nn.view.manager.notice;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.vo.notice.NoticeVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class Notice_detail_controller implements Initializable {

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
	private JFXButton notice_update;
	@FXML
	private JFXButton notice_delete;
	@FXML
	private JFXButton update_ok;

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
		//textfield 비활성화
		notice_title.setEditable(false);
		notice_date.setEditable(false);
		notice_content.setEditable(false);
		//수정버튼 비활성화
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
			changeScene("Manager_Notice.fxml");											
		});
		
		/**
		 * 공지사항 상세조회
		 */
		try {
			notice_vo = notice.selectNoticedetail(notice_id);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		notice_title.setText(notice_vo.getNotice_title());
		notice_date.setText(notice_vo.getNotice_date());
		notice_content.setText(notice_vo.getNotice_content());
		
		/**
		 * 수정하기 클릭 시
		 */
		notice_update.setOnAction(e->{
			//버튼 활성화 변경
			update_ok.setDisable(false);
			notice_update.setDisable(true);
			notice_delete.setDisable(true);
			
			//textfield 활성화
			notice_title.setEditable(true);
			notice_content.setEditable(true);
			notice_title.setFocusColor(Paint.valueOf("purple"));
			notice_content.setFocusColor(Paint.valueOf("purple"));
		});
		
		/**
		 * 공지사항 수정
		 */
		update_ok.setOnAction(e->{
			notice_vo.setNotice_id(notice_id);
			notice_vo.setNotice_title(notice_title.getText());
			notice_vo.setNotice_content(notice_content.getText());
			
			if(notice_title.getText().isEmpty() || notice_content.getText().isEmpty()) {
				errMsg("수정실패", "빈 항목이 존재합니다.");
				return;
			}else {
				Alert updateReal = new Alert(AlertType.CONFIRMATION);
				updateReal.setTitle("공지사항 수정");
				updateReal.setHeaderText("수정");
				updateReal.setContentText("공지사항을 수정하시겠습니까?");
				ButtonType updateResult = updateReal.showAndWait().get();
				//OK버튼 클릭시
				if(updateResult == ButtonType.OK) {
					try {
						notice.updateNotice(notice_vo);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					OKMsg("수정완료", "공지사항을 수정했습니다.");
					notice_update.setDisable(false);
					notice_delete.setDisable(false);
					changeScene("Manager_Notice.fxml");
				// cancel버튼 클릭시
				}else if(updateResult == ButtonType.CANCEL){
					//버튼 활성화 변경
					update_ok.setDisable(true);
					notice_update.setDisable(false);
					notice_delete.setDisable(false);
					//textfield 비활성화
					notice_title.setEditable(false);
					notice_content.setEditable(false);
				}
			}
		});
		
		/**
		 * 공지사항 삭제
		 */
		notice_delete.setOnAction(e->{
			Alert deleteReal = new Alert(AlertType.CONFIRMATION);
			deleteReal.setTitle("공지사항 삭세");
			deleteReal.setHeaderText("삭제");
			deleteReal.setContentText("공지사항을 삭제하시겠습니까?");
			ButtonType deleteResult = deleteReal.showAndWait().get();
			if(deleteResult == ButtonType.OK) {
				try {
					notice.deleteNotice(notice_id);
				}catch (RemoteException e1) {
					e1.printStackTrace();
				}
				OKMsg("삭제완료", "공지사항을 삭제했습니다.");
				changeScene("Manager_Notice.fxml");
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
