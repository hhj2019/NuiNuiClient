package kr.or.ddit.nn.view.CustomerService.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.service.notice.NoticeService;
import kr.or.ddit.nn.vo.notice.NoticeVO;
import kr.or.ddit.nn.vo.notice.Notice_OsearchVO;
import kr.or.ddit.nn.vo.notice.Notice_searchVO;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;

public class CS_notice_controller implements Initializable {

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private Pagination notice_pagination;
	@FXML
	private TableView<NoticeVO> notice_table;
	@FXML
	private TableColumn<NoticeVO, Integer> notice_id;
	@FXML
	private TableColumn<NoticeVO, String> notice_title;
	@FXML
	private TableColumn<NoticeVO, String> notice_date;
	@FXML
	private ChoiceBox<String> search_choice;
	@FXML
	private JFXTextField search_text;
	@FXML
	private JFXButton search_btn;

	private Registry reg;
	private NoticeService notice;

	private int from, to, itemsForPage;
	private ObservableList<NoticeVO> currentPageDate;
	private ObservableList<NoticeVO> noticeData;
	private Notice_searchVO nsv;
	private Notice_OsearchVO nosv;

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
	public void initialize(URL location, ResourceBundle resources) {
		notice_id.setStyle("-fx-alignment: CENTER;");
		notice_date.setStyle("-fx-alignment: CENTER;");
		
		noticeData = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			notice = (NoticeService) reg.lookup("noticeService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		try {
			noticeData.setAll(notice.selectAllNotice());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		
		notice_id.setCellValueFactory(new PropertyValueFactory<>("notice_id"));
		notice_title.setCellValueFactory(new PropertyValueFactory<>("notice_title"));
		notice_date.setCellValueFactory(new PropertyValueFactory<>("notice_date"));
		notice_table.setItems(noticeData);
		/**
		 * 페이지네이션
		 */
		itemsForPage = 10;
		int totItemCnt = noticeData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		notice_pagination.setPageCount(totPageCnt);
		notice_pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				notice_table.setItems(getTableViewDate(from, to));

				return notice_table;
			}
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
						CS_detail_controller.notice_id = nv.getNotice_id();
						changeScene("CS_notice_detail.fxml");
					}
				}
			}
		});

		search_choice.getItems().addAll("제목 + 내용", "제목", "내용");
		search_choice.setValue("제목 + 내용");
		search_btn.setOnAction(e -> {
			/**
			 * 공지사항 제목 + 내용 검색
			 */
			if (search_choice.getValue() == "제목 + 내용") {
				try {
					nsv = new Notice_searchVO();
					nsv.setSearch_text(search_text.getText());
					nsv.setSearch_text1(search_text.getText());
					noticeData.setAll(notice.allSearchNotice(nsv));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				notice_table.setItems(noticeData);
			/**
			* 공지사항 제목 검색
			*/
			} else if (search_choice.getValue() == "제목") {
				try {
					nosv = new Notice_OsearchVO();
					nosv.setSearch_text(search_text.getText());
					noticeData.setAll(notice.titleSearchNotice(nosv));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				notice_table.setItems(noticeData);
			/**
			* 공지사항 내용 검색
			*/
			} else if (search_choice.getValue() == "내용") {
				try {
					nosv = new Notice_OsearchVO();
					nosv.setSearch_text(search_text.getText());
					noticeData.setAll(notice.contentSearchNotice(nosv));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				notice_table.setItems(noticeData);
			}
		});
	}

	/**
	 * TableView에 채워줄 데이터를 가져오는 메서드
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<NoticeVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = noticeData.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(noticeData.get(i));
		}
		return currentPageDate;
	}
}
