package kr.or.ddit.nn.view.ticket;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import kr.or.ddit.nn.service.ticket.TicketService;
import kr.or.ddit.nn.vo.ticket.TicketVO;
import util.Server;

public class Buy_Ticket_MainController implements Initializable {
	
    @FXML
    private ScrollPane mainPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TicketService service = null;
		try {
			service = (TicketService) Server.reg.lookup("ticketService");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		List<TicketVO> list = null;
		
		try {
			list = service.viewAllTicketToUser();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Pane pane = new Pane();
		VBox v = new VBox();
		
		for(TicketVO item : list) {		
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("buy_ticket_list.fxml"));
				Buy_Ticket_ListController listCon = new Buy_Ticket_ListController(item);
				loader.setController(listCon);
				
				HBox h = loader.load();
				
				v.getChildren().add(h);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		}
		
		pane.getChildren().addAll(v);
		mainPane.setContent(pane);
		
	}

}
