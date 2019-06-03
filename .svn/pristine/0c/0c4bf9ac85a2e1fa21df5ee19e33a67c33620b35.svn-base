package kr.or.ddit.nn.view.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.view.CustomerService.faq.CS_faq_detail_controller;
import kr.or.ddit.nn.view.CustomerService.notice.CS_detail_controller;
import kr.or.ddit.nn.vo.faq.FAQVO;
import kr.or.ddit.nn.vo.notice.NoticeVO;

public class CS_main_controller implements Initializable {

	private Registry reg;
	private NoticeService notice;
	private FAQService faq;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane top_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton notice_btn;
	@FXML
	private JFXButton faq_btn;
	@FXML
	private JFXButton qna_btn;
	@FXML
	private JFXButton report_btn;
	@FXML
	private TableView<NoticeVO> main_notice_table;
	@FXML
	private TableColumn<NoticeVO, Integer> notice_id;
	@FXML
	private TableColumn<NoticeVO, String> notice_title;
	@FXML
	private TableColumn<NoticeVO, String> notice_date;
	@FXML
	private TableView<FAQVO> main_faq_table;
	@FXML
	private TableColumn<FAQVO, Integer> faq_id;
	@FXML
	private TableColumn<FAQVO, String> faq_title;
	@FXML
	private TableColumn<FAQVO, String> faq_date;

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		notice_id.setStyle("-fx-alignment: CENTER;");
		notice_date.setStyle("-fx-alignment: CENTER;");

		faq_id.setStyle("-fx-alignment: CENTER;");
		faq_date.setStyle("-fx-alignment: CENTER;");

		/**
		 * 메인 공지사항 출력
		 */
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			notice = (NoticeService) reg.lookup("noticeService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		ObservableList<NoticeVO> Noticedata = FXCollections.observableArrayList();
		try {
			Noticedata.addAll(notice.selectNewNotice());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		notice_id.setCellValueFactory(new PropertyValueFactory<>("notice_id"));
		notice_title.setCellValueFactory(new PropertyValueFactory<>("notice_title"));
		notice_date.setCellValueFactory(new PropertyValueFactory<>("notice_date"));

		main_notice_table.setItems(Noticedata);

		/**
		 * 자주묻는질문 출력
		 */
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		ObservableList<FAQVO> faqdata = FXCollections.observableArrayList();
		try {
			faqdata.addAll(faq.selectNewFAQ());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		faq_id.setCellValueFactory(new PropertyValueFactory<>("faq_id"));
		faq_title.setCellValueFactory(new PropertyValueFactory<>("faq_title"));
		faq_date.setCellValueFactory(new PropertyValueFactory<>("faq_date"));

		main_faq_table.setItems(faqdata);

		/**
		 * 공지사항 상세보기
		 */
		main_notice_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (main_notice_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						NoticeVO nv = main_notice_table.getSelectionModel().getSelectedItem();
						CS_detail_controller.notice_id = nv.getNotice_id();
						changeScene("notice/CS_notice_detail.fxml");
					}
				}
			}
		});

		/**
		 * 자주묻는질문상세보기
		 */
		main_faq_table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (main_faq_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						FAQVO fv = main_faq_table.getSelectionModel().getSelectedItem();
						CS_faq_detail_controller.faq_id = fv.getFaq_id();
						changeScene("faq/CS_faq_datail.fxml");
					}
				}
			}
		});
	}

	/**
	 * 공지사항 버튼 클릭시
	 */
	@FXML
	public void notice_btn() {
		changeScene("notice/CS_notice.fxml");
		// 자기 버튼 색 변경
		notice_btn.setStyle("-fx-background-color: rgb(230,230,230)");
		// 다른버튼 기본으로 변경
		faq_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		qna_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		report_btn.setStyle("-fx-border-color: rgb(140,0,140);");
	}

	/**
	 * 자주묻는질문 클릭 시
	 */
	@FXML
	public void faq_btn() {
		changeScene("faq/CS_faq_main.fxml");
		// 자기 버튼 색 변경
		faq_btn.setStyle("-fx-background-color: rgb(230,230,230)");
		// 다른버튼 기본으로 변경
		notice_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		qna_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		report_btn.setStyle("-fx-border-color: rgb(140,0,140);");
	}

	@FXML
	public void qna_btn() {
		if (Session.getUser() != null) {
			changeScene("qna/CS_qna_main.fxml");
			// 자기 버튼 색 변경
			qna_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			notice_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			faq_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			report_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		} else if (Session.getUser() == null) {
			errMsg("로그인", "로그인 후 이용가능합니다.");
		}
	}

	@FXML
	public void report_btn() {
		if (Session.getUser() != null) {
			changeScene("report/CS_report_main.fxml");
			// 자기 버튼 색 변경
			report_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			notice_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			faq_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			qna_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		} else if (Session.getUser() == null) {
			errMsg("로그인", "로그인 후 이용 가능합니다");
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
}
