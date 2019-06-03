package kr.or.ddit.nn.view.CustomerService.report;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.report.ReportService;
import kr.or.ddit.nn.vo.report.ReportVO;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class CS_insert_controller implements Initializable {
	private Registry reg;
	private ReportService report;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton report_ok_btn;
	@FXML
	private JFXButton report_cancel_btn;
	@FXML
	private JFXRadioButton noise_report;
	@FXML
	private JFXRadioButton silent_report;
	@FXML
	private JFXRadioButton lyrics_report;
	@FXML
	private JFXRadioButton artist_report;
	@FXML
	private JFXRadioButton album_report;
	@FXML
	private JFXRadioButton others_report;
	@FXML
	private JFXTextField report_title;
	@FXML
	private JFXTextArea report_content;

	String report_category = "";

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

		ToggleGroup group = new ToggleGroup();
		noise_report.setToggleGroup(group);
		noise_report.setUserData("음원 잡음 신고");
		silent_report.setToggleGroup(group);
		silent_report.setUserData("음원 무음 신고");
		lyrics_report.setToggleGroup(group);
		lyrics_report.setUserData("음원 가사 신고");
		artist_report.setToggleGroup(group);
		artist_report.setUserData("아티스트 정보 신고");
		album_report.setToggleGroup(group);
		album_report.setUserData("앨범 정보 신고");
		others_report.setToggleGroup(group);
		others_report.setUserData("기타 신고");

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (group.getSelectedToggle() != null) {
					report_category = group.getSelectedToggle().getUserData().toString();
				}
			}
		});
	}

	@FXML
	public void insert_report() {
		ReportVO rv = new ReportVO();

		rv.setReport_title(report_title.getText());
		rv.setReport_content(report_content.getText());
		rv.setMem_id(Session.getUser().getMem_id());
		rv.setReport_category(report_category);

		if (report_title.getText().isEmpty() || report_content.getText().isEmpty() || report_category.isEmpty()) {
			errMsg("등록실패", "빈 항목이 존재합니다.");
		} else {
			try {
				report.insertReport(rv);
			} catch (IOException e) {
				e.printStackTrace();
			}
			OKMsg("등록성공", "신고글이 등록되었습니다.");
			changeScene("CS_report_main.fxml");
		}
	}

	@FXML
	public void cancel_report() {
		changeScene("CS_report_main.fxml");
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
}
