package kr.or.ddit.nn.view.CustomerService.report;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.report.ReportService;
import kr.or.ddit.nn.vo.report.ReportVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import com.jfoenix.controls.JFXButton;

public class CS_report_controller implements Initializable {

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private Pagination report_pagination;
	@FXML
	private TableView<ReportVO> report_table;
	@FXML
	private TableColumn<ReportVO, Integer> report_id;
	@FXML
	private TableColumn<ReportVO, String> report_title;
	@FXML
	private TableColumn<ReportVO, String> report_ing;
	@FXML
	private TableColumn<ReportVO, String> report_date;
	@FXML
	private JFXButton insert_btn;

	private Registry reg;
	private ReportService report;

	private int from, to, itemsForPage;
	private ObservableList<ReportVO> currentPageDate;
	private ObservableList<ReportVO> reportData;

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
		reportData = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			report = (ReportService) reg.lookup("reportService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		try {
			reportData.setAll(report.selectMyReport(Session.getUser().getMem_id()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		report_id.setStyle("-fx-alignment: CENTER;");
		report_ing.setStyle("-fx-alignment: CENTER;");
		report_date.setStyle("-fx-alignment: CENTER;");

		report_id.setCellValueFactory(new PropertyValueFactory<>("report_id"));
		report_title.setCellValueFactory(new PropertyValueFactory<>("report_title"));
		report_ing.setCellValueFactory(new PropertyValueFactory<>("report_ing"));
		report_date.setCellValueFactory(new PropertyValueFactory<>("report_date"));
		report_table.setItems(reportData);

		/**
		 * 페이지네이션
		 */
		itemsForPage = 10;
		int totItemCnt = reportData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		report_pagination.setPageCount(totPageCnt);
		report_pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				report_table.setItems(getTableViewDate(from, to));

				return report_table;
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
						CS_detail_controller.report_id = rv.getReport_id();
						changeScene("CS_report_detail.fxml");
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
	private ObservableList<ReportVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = reportData.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(reportData.get(i));
		}
		return currentPageDate;
	}

	@FXML
	public void insert_btn() {
		changeScene("CS_insert_report.fxml");
	}
}
