package kr.or.ddit.nn.view.manager.ticket;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import kr.or.ddit.nn.service.ticket.TicketService;
import kr.or.ddit.nn.vo.ticket.TicketVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class Manager_Ticket_DetailController implements Initializable {

	public static int ticket_id;

	private Registry reg;
	private TicketService service;

	@FXML
	private Pane mainPane;

	@FXML
	private JFXButton ticket_update_btn;
	
	@FXML
    private JFXButton ticket_update_ok_btn;

	@FXML
	private JFXButton ticket_delete_btn;

	@FXML
	private JFXTextField ticket_name;

	@FXML
	private JFXTextField ticket_activation;

	@FXML
	private JFXTextArea ticket_content;

	@FXML
	private JFXTextField ticket_day;

	@FXML
	private JFXTextField ticket_download;

	@FXML
	private JFXTextField ticket_price;

	TicketVO ticketVO;
	
	public void OKMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("성공");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public void changeScene(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().setAll(parent);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			service = (TicketService) reg.lookup("ticketService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		ticket_name.setEditable(false);
		ticket_activation.setEditable(false);
		ticket_content.setEditable(false);
		ticket_day.setEditable(false);
		ticket_price.setEditable(false);
		ticket_download.setEditable(false);
		ticket_update_ok_btn.setDisable(true);
		
		JFXButton back_btn = new JFXButton();
		back_btn.setText("목록 보기");
		back_btn.setLayoutX(32);
		back_btn.setLayoutY(29);
		back_btn.setStyle("-fx-border-color: rgb(140,0,140); -fx-border-radius: 3;");
		back_btn.setPrefWidth(100);
		back_btn.setPrefHeight(30);
		mainPane.getChildren().add(back_btn);
		
		back_btn.setOnAction(e->{
			changeScene("Manager_Ticket.fxml");											
		});
		
		/**
		 * 이용권 상세조회
		 */
		try {
			ticketVO = service.selectTicekt(ticket_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		ticket_name.setText(ticketVO.getTicket_name());
		String active;
		if(ticketVO.getTicket_activation() == 1)
			active = "1";
		else
			active = "0";
		ticket_activation.setText(active);
		ticket_name.setText(ticketVO.getTicket_name());
		ticket_content.setText(ticketVO.getTicket_content());
		ticket_day.setText(Integer.toString(ticketVO.getTicket_day()));
		ticket_price.setText(Integer.toString(ticketVO.getTicket_price()));
		ticket_download.setText(Integer.toString(ticketVO.getTicket_download()));

		/**
		 * 수정하기 클릭 시 textFeild 활성화
		 */
		ticket_update_btn.setOnAction(e -> {
			ticket_name.setEditable(true);
			ticket_activation.setEditable(true);
			ticket_content.setEditable(true);
			ticket_day.setEditable(true);
			ticket_price.setEditable(true);
			ticket_download.setEditable(true);
			
			ticket_name.setFocusColor(Paint.valueOf("purple"));
			ticket_activation.setFocusColor(Paint.valueOf("purple"));
			ticket_content.setFocusColor(Paint.valueOf("purple"));
			ticket_day.setFocusColor(Paint.valueOf("purple"));
			ticket_price.setFocusColor(Paint.valueOf("purple"));
			ticket_download.setFocusColor(Paint.valueOf("purple"));
			
			ticket_update_ok_btn.setDisable(false);
			ticket_update_btn.setDisable(true);
			ticket_delete_btn.setDisable(true);
		});

		/**
		 * 수정 -> 확인
		 */
		ticket_update_ok_btn.setOnAction(e -> {
			
			if (ticket_name.getText().isEmpty()
					|| ticket_activation.getText().isEmpty()
					|| ticket_content.getText().isEmpty()
					|| ticket_day.getText().isEmpty()
					|| ticket_price.getText().isEmpty()
					|| ticket_download.getText().isEmpty()){
				errMsg("수정 실패!", "빈 항목이 존재합니다");
				return;
			} else {
				int actResult = ticket_activation.getText().equals("1") ? 1 : 0;
				
				ticketVO.setTicekt_id(ticket_id);
				ticketVO.setTicket_activation(actResult);
				ticketVO.setTicket_name(ticket_name.getText());
				ticketVO.setTicket_content(ticket_content.getText());
				ticketVO.setTicket_day(Integer.parseInt(ticket_day.getText()));
				ticketVO.setTicket_price(Integer.parseInt(ticket_price.getText()));
				ticketVO.setTicket_download(Integer.parseInt(ticket_download.getText()));
				
				try {
					service.updateTicket(ticketVO);
					OKMsg("수정 완료!", "이용권을 수정했습니다");
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				changeScene("Manager_Ticket.fxml");

			}
		});

		/**
		 * 이용권 삭제
		 */
		ticket_delete_btn.setOnAction(e -> {
			try {
				service.removeTicket(ticket_id);
				OKMsg("삭제 완료!", "이용권을 삭제했습니다");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			changeScene("Manager_Ticket.fxml");

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
