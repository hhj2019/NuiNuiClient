package kr.or.ddit.nn.view.ticket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.view.payment.PaymentController;
import kr.or.ddit.nn.vo.ticket.TicketVO;

public class Buy_Ticket_ListController implements Initializable {

	@FXML
    private HBox ticket_item;

    @FXML
    private FontAwesomeIconView ticket_icon;

    @FXML
    private Label ticket_name;

    @FXML
    private Label ticket_context;

    @FXML
    private Label ticket_price;

    @FXML
    private JFXButton ticket_buy_btn;
    
    private final TicketVO ticket;
    
    public Buy_Ticket_ListController(TicketVO ticket) {
    	this.ticket = ticket;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ticket_name.setText(ticket.getTicket_name());
		ticket_context.setText(ticket.getTicket_content());
		ticket_price.setText(Integer.toString(ticket.getTicket_price()));
		
		if(ticket.getTicket_download() != 0)
			ticket_icon.setGlyphName("DOWNLOAD");
		
		ticket_buy_btn.setOnAction(e->{
			if(Session.getUser()!=null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/nn/view/payment/payment.fxml"));
				PaymentController payCon = new PaymentController(ticket);
				loader.setController(payCon);
				Parent p = null;
				try {
					p = loader.load();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Stage payStage = new Stage();
				Scene payScene = new Scene(p);
				payStage.setScene(payScene);
				payStage.show();
			}
			else {
				errMsg("로그인오류", "로그인 후 이용 가능합니다.");
			}
			
		});
		
	}
	
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
}
