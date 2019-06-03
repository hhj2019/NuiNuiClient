package kr.or.ddit.nn.view.manager.report;

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

import api.MailingAPI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.report.ReportService;
import kr.or.ddit.nn.vo.report.ReportVO;

public class Report_detail_controller implements Initializable{
	public static int report_id;
	
	private Registry reg;
	private ReportService report;
	
	 @FXML
	 private BorderPane Main_pane;
	 @FXML
	 private Pane center_pane;
	 @FXML
	 private JFXTextField report_title;
	 @FXML
	 private JFXTextField report_date;
	 @FXML
	 private JFXTextArea report_content;
	 @FXML
	 private JFXTextField report_memid;
	 @FXML
	 private JFXTextField report_category;
	 @FXML
	 private JFXTextField report_ing;
	 @FXML
	 private ComboBox<String> combo_box;
	 @FXML
	 private JFXButton ing_btn;
	 
	 ReportVO report_vo;
	/**
	 * 메인 화면 변경
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
			report = (ReportService) reg.lookup("reportService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		//textfield 비활성화
		report_title.setEditable(false);
		report_date.setEditable(false);
		report_content.setEditable(false);
		report_memid.setEditable(false);
		report_category.setEditable(false);
		
		JFXButton back_btn = new JFXButton();
		back_btn.setText("목록 보기");
		back_btn.setLayoutX(618);
		back_btn.setLayoutY(455);
		back_btn.setStyle("-fx-border-color: rgb(140,0,140); -fx-border-radius: 3;");
		back_btn.setPrefWidth(100);
		back_btn.setPrefHeight(30);
		center_pane.getChildren().add(back_btn);
		
		back_btn.setOnAction(e->{
			changeScene("Manager_Report.fxml");											
		});
		
		/**
		 * 신고하기 상세조회
		 */
		try {
			report_vo = report.selectReportdetail(report_id);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		report_title.setText(report_vo.getReport_title());
		report_date.setText(report_vo.getReport_date());
		report_content.setText(report_vo.getReport_content());
		report_memid.setText(report_vo.getMem_id());
		report_category.setText(report_vo.getReport_category());
		report_ing.setText(report_vo.getReport_ing());

		combo_box.getItems().addAll("미확인", "확인중", "확인완료");
		combo_box.setValue(report_vo.getReport_ing());

		/**
		 * 진행상황 변경 버튼 클릭 시 
		 */
		ing_btn.setOnAction(e->{
			if(combo_box.getValue() == "확인중") {
				report_vo.setReport_id(report_id);
				report_vo.setReport_ing(combo_box.getValue());
				try {
					report.updateReport(report_vo);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				OKMsg("진행상황 변경 성공", "신고 진행상황을 수정했습니다.");
				changeScene("report_detail.fxml");
			}else if(combo_box.getValue() == "확인완료"){
				report_vo.setReport_id(report_id);
				report_vo.setReport_ing(combo_box.getValue());
				try {
					report.updateReport(report_vo);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				OKMsg("진행상황 변경 성공", "신고 진행상황을 수정했습니다." + "\n\n회원에게 메일을 전송합니다.");
				changeScene("report_detail.fxml");
				MailingAPI.sendMail(report_vo.getMem_email(), report_vo.getReport_title(), "Report");
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
