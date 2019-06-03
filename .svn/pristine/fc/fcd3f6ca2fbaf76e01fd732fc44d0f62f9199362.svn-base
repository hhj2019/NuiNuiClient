package kr.or.ddit.nn.view.manager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import kr.or.ddit.nn.main.ClientMain;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.service.report.ReportService;
import kr.or.ddit.nn.view.manager.notice.Notice_detail_controller;
import kr.or.ddit.nn.view.manager.report.Report_detail_controller;
import kr.or.ddit.nn.vo.notice.NoticeVO;
import kr.or.ddit.nn.vo.report.ReportVO;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;

public class Manager_Controller implements Initializable {

	private Registry reg;
	private NoticeService notice;
	private ReportService report;

	@FXML
	private BorderPane Main;
	@FXML
	private Pane manager_top;
	@FXML
	private ImageView main_logo;
	@FXML
	private ImageView main_title;
	@FXML
	private JFXButton login_btn;
	@FXML
	private Circle login_img;
	@FXML
	private Pane manager_main;

	@FXML
	private JFXButton Main_notice_btn;
	@FXML
	private TableView<NoticeVO> notice_table;
	@FXML
	private TableColumn<NoticeVO, Integer> notice_id;
	@FXML
	private TableColumn<NoticeVO, String> notice_title;
	@FXML
	private TableColumn<NoticeVO, Date> notice_date;

	@FXML
	private JFXButton Main_report_btn;
	@FXML
	private TableView<ReportVO> report_table;
	@FXML
	private TableColumn<ReportVO, Integer> report_id;
	@FXML
	private TableColumn<ReportVO, String> report_title;
	@FXML
	private TableColumn<ReportVO, Date> report_date;

	@FXML
	private JFXButton music_btn;
	@FXML
	private JFXButton ticket_btn;
	@FXML
	private JFXButton FAQ_btn;
	@FXML
	private JFXButton QnA_btn;
	@FXML
	private JFXButton notice_btn;
	@FXML
	private JFXButton report_btn;
	@FXML
	private JFXButton event_btn;

	@FXML
	private ImageView music_img;
	@FXML
	private ImageView ticket_img;
	@FXML
	private ImageView FAQ_img;
	@FXML
	private ImageView QnA_img;
	@FXML
	private ImageView notice_img;
	@FXML
	private ImageView report_img;
	@FXML
	private ImageView event_img;
	@FXML 
	private Button logout_btn;

	Manager_main mn_Main = new Manager_main();

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main.setCenter(manager_main);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.setCenter(parent);
		return loader;
	}

	/**
	 * 메인 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeMain(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.getChildren().setAll(parent);
		return loader;
	}
	
	public void changepage(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMain.pStage.getScene().setRoot(parent);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("img/profile-manager.png");
		Image img = new Image(file.toURI().toString());
		login_img.setFill(new ImagePattern(img));
		
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
		notice_id.setStyle("-fx-alignment: CENTER;");
		notice_date.setStyle("-fx-alignment: CENTER;");

		notice_id.setCellValueFactory(new PropertyValueFactory<>("notice_id"));
		notice_title.setCellValueFactory(new PropertyValueFactory<>("notice_title"));
		notice_date.setCellValueFactory(new PropertyValueFactory<>("notice_date"));

		notice_table.setItems(Noticedata);

		/**
		 * 메인 신고하기 출력
		 */
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			report = (ReportService) reg.lookup("reportService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		ObservableList<ReportVO> reportdata = FXCollections.observableArrayList();
		try {
			reportdata.addAll(report.selectNewReport());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		report_id.setStyle("-fx-alignment: CENTER;");
		report_date.setStyle("-fx-alignment: CENTER;");
		
		report_id.setCellValueFactory(new PropertyValueFactory<>("report_id"));
		report_title.setCellValueFactory(new PropertyValueFactory<>("report_title"));
		report_date.setCellValueFactory(new PropertyValueFactory<>("report_date"));

		report_table.setItems(reportdata);

		/**
		 * 메인 로고, 이미지 클릭시 메인으로 변경
		 */
		main_title.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				changeMain("Manager_MAIN.fxml");
			}
		});
		main_logo.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				changeMain("Manager_MAIN.fxml");
			}
		});

		/**
		 * 메인 공지사항, 신고하기 버튼 클릭
		 */
		Main_notice_btn.setOnAction(e -> {
			changeScene("notice/Manager_notice.fxml");
		});
		Main_report_btn.setOnAction(e -> {
			changeScene("report/Manager_Report.fxml");
		});

		/**
		 * 공지사항 상세보기
		 */
		notice_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (notice_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						NoticeVO nv = notice_table.getSelectionModel().getSelectedItem();
						Notice_detail_controller.notice_id = nv.getNotice_id();
						changeScene("notice/notice_detail.fxml");
					}
				}
			}
		});

		/**
		 * 신고하기 상세보기
		 */
		report_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (report_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						ReportVO rv = report_table.getSelectionModel().getSelectedItem();
						Report_detail_controller.report_id = rv.getReport_id();
						changeScene("report/report_detail.fxml");
					}
				}
			}
		});
		
		/**
		 * 로그아웃 버튼 클릭 시
		 */
		logout_btn.setOnAction(e -> {
			Session.setUser(null);
			changepage("/kr/or/ddit/nn/main/Main.fxml");
		});		

		// 버튼 액션으로 페이지 이동
		music_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			music_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("music/music/Manager_music.fxml");
		});

		ticket_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			ticket_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("ticket/Manager_Ticket.fxml");
		});

		FAQ_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			FAQ_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("faq/Manager_FAQ.fxml");
		});

		QnA_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			QnA_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("qna/qna_main.fxml");
		});

		notice_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			notice_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("notice/Manager_notice.fxml");
		});

		report_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			report_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("report/Manager_Report.fxml");
		});

		event_btn.setOnAction(e -> {
			// 자기 버튼 색 변경
			event_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			music_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			FAQ_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			QnA_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			notice_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			report_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			changeScene("event/Manager_event.fxml");
		});
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
