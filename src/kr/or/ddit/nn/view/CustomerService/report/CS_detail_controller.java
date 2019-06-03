package kr.or.ddit.nn.view.CustomerService.report;

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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.report.ReportService;
import kr.or.ddit.nn.vo.report.ReportVO;

public class CS_detail_controller implements Initializable {
	public static int report_id;

	private Registry reg;
	private ReportService report;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton report_ok_btn;
	@FXML
	private JFXTextField report_title;
	@FXML
	private JFXTextField report_date;
	@FXML
	private JFXTextField report_ing;
	@FXML
	private JFXTextField report_category;
	@FXML
	private JFXTextField report_memid;
	@FXML
	private JFXTextArea report_content;

	ReportVO report_vo;

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
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			report = (ReportService) reg.lookup("reportService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		// textfield 비활성화
		report_title.setEditable(false);
		report_date.setEditable(false);
		report_content.setEditable(false);
		report_memid.setEditable(false);
		report_category.setEditable(false);
		report_ing.setEditable(false);

		/**
		 * 신고하기 상세조회
		 */
		try {
			report_vo = report.selectReportdetail(report_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		report_title.setText(report_vo.getReport_title());
		report_date.setText(report_vo.getReport_date());
		report_content.setText(report_vo.getReport_content());
		report_memid.setText(report_vo.getMem_id());
		report_category.setText(report_vo.getReport_category());
		report_ing.setText(report_vo.getReport_ing());
	}

	@FXML
	public void report_ok() {
		changeScene("CS_report_main.fxml");
	}
}
