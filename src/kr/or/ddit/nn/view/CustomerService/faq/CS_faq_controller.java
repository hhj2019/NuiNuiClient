package kr.or.ddit.nn.view.CustomerService.faq;

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
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.vo.faq.FAQVO;
import kr.or.ddit.nn.vo.faq.FAQ_OSearchVO;
import kr.or.ddit.nn.vo.faq.FAQ_OSearchVO2;
import kr.or.ddit.nn.vo.faq.FAQ_SearchVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;

public class CS_faq_controller implements Initializable {

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private Pagination faq_pagination;
	@FXML
	private TableView<FAQVO> faq_table;
	@FXML
	private TableColumn<FAQVO, Integer> faq_id;
	@FXML
	private TableColumn<FAQVO, String> faq_title;
	@FXML
	TableColumn<FAQVO, String> faq_date;
	@FXML
	private ChoiceBox<String> search_choice;
	@FXML
	private JFXTextField search_text;
	@FXML
	private JFXButton search_btn;

	private Registry reg;
	private FAQService faq;

	private int from, to, itemsForPage;
	private ObservableList<FAQVO> currentPageDate;
	private ObservableList<FAQVO> faqData;
	private FAQ_SearchVO fsv;
	private FAQ_OSearchVO fosv;
	private FAQ_OSearchVO2 fosv2;

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
		faq_id.setStyle("-fx-alignment: CENTER;");
		faq_date.setStyle("-fx-alignment: CENTER;");

		faqData = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			faqData.addAll(faq.selectAllFAQ());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		faq_id.setCellValueFactory(new PropertyValueFactory<>("faq_id"));
		faq_title.setCellValueFactory(new PropertyValueFactory<>("faq_title"));
		faq_date.setCellValueFactory(new PropertyValueFactory<>("faq_date"));
		faq_table.setItems(faqData);

		/**
		 * 페이지네이션 설정
		 */
		itemsForPage = 10;
		int totItemCnt = faqData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		faq_pagination.setPageCount(totPageCnt);

		faq_pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				faq_table.setItems(getTableViewDate(from, to));

				return faq_table;
			}
		});

		/**
		 * 자주묻는질문 상세보기
		 */
		faq_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (faq_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						FAQVO fv = faq_table.getSelectionModel().getSelectedItem();
						CS_faq_detail_controller.faq_id = fv.getFaq_id();
						changeScene("CS_faq_datail.fxml");
					}
				}
			}
		});

		search_choice.getItems().addAll("제목 + 내용", "제목", "내용");
		search_choice.setValue("제목 + 내용");
		search_btn.setOnAction(e -> {
			/**
			 * 자주묻는질문 제목 + 내용 검색
			 */
			if (search_choice.getValue() == "제목 + 내용") {
				try {
					fsv = new FAQ_SearchVO();
					fsv.setSearch_text(search_text.getText());
					fsv.setSearch_text1(search_text.getText());
					fsv.setSearch_text2(search_text.getText());
					faqData.setAll(faq.allSearchFaq(fsv));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				faq_table.setItems(faqData);
				/**
				 * 자주묻는질문 제목 검색
				 */
			} else if (search_choice.getValue() == "제목") {
				try {
					fosv = new FAQ_OSearchVO();
					fosv.setSearch_text(search_text.getText());
					faqData.setAll(faq.titleSearchFaq(fosv));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				faq_table.setItems(faqData);
				/**
				 * 자주묻는질문 내용 검색
				 */
			} else if (search_choice.getValue() == "내용") {
				try {
					fosv2 = new FAQ_OSearchVO2();
					fosv2.setSearch_text(search_text.getText());
					fosv2.setSearch_text1(search_text.getText());
					faqData.setAll(faq.contentSearchFaq(fosv2));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				faq_table.setItems(faqData);
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
	private ObservableList<FAQVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = faqData.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(faqData.get(i));
		}
		return currentPageDate;
	}
}
