package kr.or.ddit.nn.view.event;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.service.event.eventService;
import kr.or.ddit.nn.vo.event.EventVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;

public class event_controller implements Initializable {
	private Registry reg;
	private eventService event;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane in_pane;
	@FXML
	private Pagination event_pagination;
	@FXML
	private TableView<EventVO> event_table;
	@FXML
	private TableColumn<EventVO, Integer> event_id;
	@FXML
	private TableColumn<EventVO, String> event_title;
	@FXML
	private TableColumn<EventVO, String> event_eventdate;

	private int from, to, itemsForPage;
	private ObservableList<EventVO> currentPageDate;
	private ObservableList<EventVO> eventdata;

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(in_pane);
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
		event_id.setStyle("-fx-alignment: CENTER;");
		event_eventdate.setStyle("-fx-alignment: CENTER;");

		eventdata = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			event = (eventService) reg.lookup("eventService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		/**
		 * 이벤트 목록 조회
		 */
		try {
			eventdata.setAll(event.selectEventDate());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		event_id.setCellValueFactory(new PropertyValueFactory<>("event_id"));
		event_title.setCellValueFactory(new PropertyValueFactory<>("event_title"));
		event_eventdate.setCellValueFactory(new PropertyValueFactory<>("event_eventdate"));
		event_table.setItems(eventdata);

		/**
		 * 페이지네이션
		 */
		itemsForPage = 10; // 한페이지에 보여줄 항목 수 설정
		int totItemCnt = eventdata.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		event_pagination.setPageCount(totPageCnt); // 전체 페이지 수 설정

		event_pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				event_table.setItems(getTableViewDate(from, to));
				return event_table;
			}
		});

		/**
		 * 이벤트 상세보기
		 */
		event_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (event_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						EventVO ev = event_table.getSelectionModel().getSelectedItem();
						event_datail_controller.event_id = ev.getEvent_id();
						changeScene("event_datail.fxml");
					}
				}
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
	private ObservableList<EventVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = eventdata.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(eventdata.get(i));
		}
		return currentPageDate;
	}
}
