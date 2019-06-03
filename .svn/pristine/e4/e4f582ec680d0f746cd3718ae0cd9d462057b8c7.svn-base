package kr.or.ddit.nn.view.manager.faq;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.vo.faq.FAQVO;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class FAQController implements Initializable {

	private Registry reg;
	private FAQService faq;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane in_pane;
	@FXML
	private JFXButton insert_FAQ;
	@FXML
	private Pagination FAQ_pagination;
	@FXML
	private TableView<FAQVO> FAQ_table;
	@FXML
	private TableColumn<FAQVO, Integer> FAQ_id;
	@FXML
	private TableColumn<FAQVO, String> FAQ_title;
	@FXML
	private TableColumn<FAQVO, String> FAQ_date;

	private int from, to, itemsForPage;
	private ObservableList<FAQVO> currentPageDate;
	private ObservableList<FAQVO> faqdata;

	// SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");

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
		FAQ_id.setStyle("-fx-alignment: CENTER;");
		FAQ_date.setStyle("-fx-alignment: CENTER;");
		

		faqdata = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		/**
		 * 자주묻는질문 목록 조회
		 */
		try {
			faqdata.setAll(faq.selectAllFAQ());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		FAQ_id.setCellValueFactory(new PropertyValueFactory<>("faq_id"));
		FAQ_title.setCellValueFactory(new PropertyValueFactory<>("faq_title"));
		FAQ_date.setCellValueFactory(new PropertyValueFactory<>("faq_date"));
		FAQ_table.setItems(faqdata);

		/**
		 * 페이지네이션 설정
		 */
		itemsForPage = 10;
		int totItemCnt = faqdata.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		FAQ_pagination.setPageCount(totPageCnt);

		FAQ_pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				FAQ_table.setItems(getTableViewDate(from, to));

				return FAQ_table;
			}
		});

		/**
		 * 자주묻는질문 상세 보기
		 */
		FAQ_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if(FAQ_table.getSelectionModel().isEmpty()){
						return;
					}else {
						FAQVO fv = FAQ_table.getSelectionModel().getSelectedItem();
						FAQ_detail_controller.faq_id = fv.getFaq_id();
						changeScene("FAQ_detail.fxml");
					}
				}
			}
		});
	}

	/**
	 * 자주묻는질문 추가
	 */
	@FXML
	public void insert_FAQ() {
		changeScene("insert_FAQ.fxml");
	}

	/**
	 * TableView에 채워줄 데이터를 가져오는 메서드
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<FAQVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = faqdata.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(faqdata.get(i));
		}
		return currentPageDate;
	}
}