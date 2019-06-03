package kr.or.ddit.nn.view.myInfo;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.ticket.TicketHistoryService;
import kr.or.ddit.nn.service.ticket.TicketService;
import kr.or.ddit.nn.vo.member.MemberVO;
import kr.or.ddit.nn.vo.ticket.TicketHistoryVO;
import kr.or.ddit.nn.vo.ticket.TicketVO;
import util.Server;
import javafx.scene.layout.BorderPane;

public class MyInfo_TicketHistoryController implements Initializable {

	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane cneter_pane;
	@FXML
	private TableView<TableVO> ticket_table;
	@FXML
	private TableColumn<TableVO, Integer> ticket_id;
	@FXML
	private TableColumn<TableVO, String> ticket_name;
	@FXML
	private TableColumn<TableVO, String> ticket_date;

	private MemberVO my = Session.getUser();
	private TicketHistoryService historyService;
	private TicketService infoService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		MenuConnector menuCon = new MenuConnector();
//		menuCon.setPane(mainPane);
//		menuCon.setBtn(myinfo_btn, mylevel_btn, buyhistory_btn, musichistory_btn, like_btn, playlist_btn);

		try {
			historyService = (TicketHistoryService) Server.reg.lookup("ticketHistoryService");
			infoService = (TicketService) Server.reg.lookup("ticketService");
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		List<TicketHistoryVO> list = null;
		
		try {
			list = historyService.viewHistory(my.getMem_id());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		
		ObservableList<TableVO> historyData = FXCollections.observableArrayList();
		for(TicketHistoryVO item : list) {
			TicketVO thisTicket = null;
			try {
				thisTicket = infoService.selectTicekt(item.getTicket_id());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			int id = item.getTicket_id();
			String name = thisTicket.getTicket_name();
			Date date = item.getBuy_date();
			
			historyData.add(new TableVO(id, name, date));
		}
		ticket_id.setCellValueFactory(cellData -> cellData.getValue().getTicketId().asObject());
		ticket_name.setCellValueFactory(cellData -> cellData.getValue().getTicketName());
		ticket_date.setCellValueFactory(cellData -> cellData.getValue().getBuyDate());
		
		ticket_table.setItems(historyData);
	}
	
	private class TableVO {
		private IntegerProperty ticketId;
		private StringProperty ticketName;
		private StringProperty buyDate;
		
		TableVO(){
			this(0, null, null);
		}
		
		TableVO(int ticketId, String ticketName, Date date){
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			this.ticketId = new SimpleIntegerProperty(ticketId);
			this.ticketName = new SimpleStringProperty(ticketName);
			this.buyDate = new SimpleStringProperty(f.format(date));
		}
		
		public IntegerProperty getTicketId() {
			return ticketId;
		}

		public void setTicketId(IntegerProperty ticketId) {
			this.ticketId = ticketId;
		}

		public StringProperty getTicketName() {
			return ticketName;
		}

		public void setTicketName(StringProperty ticketName) {
			this.ticketName = ticketName;
		}

		public StringProperty getBuyDate() {
			return buyDate;
		}

		public void setBuyDate(StringProperty buyDate) {
			this.buyDate = buyDate;
		}	
	}
}
