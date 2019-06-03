package kr.or.ddit.nn.view.manager.ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.service.ticket.TicketService;
import kr.or.ddit.nn.vo.faq.FAQVO;
import kr.or.ddit.nn.vo.ticket.TicketVO;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class Manager_Insert_TicketController implements Initializable {

	private Registry reg;
	private TicketService service;
	
	@FXML
    private BorderPane mainPane;

    @FXML
    private Pane center_pane;

    @FXML
    private JFXTextField ticket_name;

    @FXML
    private JFXTextArea ticket_content;

    @FXML
    private JFXTextField ticket_day;

    @FXML
    private JFXTextField ticket_download;

    @FXML
    private JFXTextField ticket_price;

    @FXML
    void close_btn(ActionEvent event) {
    	
    	changeScene("Manager_Ticket.fxml");

    }

    @FXML
    void insert_btn(ActionEvent event) {
    	
    	if (ticket_name.getText().isEmpty()
				|| ticket_content.getText().isEmpty()
				|| ticket_day.getText().isEmpty()
				|| ticket_price.getText().isEmpty()
				|| ticket_download.getText().isEmpty()){
			errMsg("입력 실패!", "빈 항목이 존재합니다");
			return;
		}else {
			
			TicketVO ticketVO = new TicketVO();

			ticketVO.setTicket_activation(0);
			ticketVO.setTicket_name(ticket_name.getText());
			ticketVO.setTicket_content(ticket_content.getText());
			ticketVO.setTicket_day(Integer.parseInt(ticket_day.getText()));
			ticketVO.setTicket_price(Integer.parseInt(ticket_price.getText()));
			ticketVO.setTicket_download(Integer.parseInt(ticket_download.getText()));
			
			try {
				service.insertTicket(ticketVO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			OKMsg("추가 성공!", "자주묻는질문을 추가했습니다");
			changeScene("Manager_Ticket.fxml");
		}
    	
    }

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().setAll(parent);
		return loader;
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
	 *//*
	public void OKMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("성공");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}*/
}
