package kr.or.ddit.nn.view.myInfo;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MenuConnector {
	public static final String MENU1 = "MyInfo_Main.fxml";
	public static final String MENU2 = "MyInfo_grade.fxml";
	public static final String MENU3 = "MyInfo_ticketHistory.fxml";
	public static final String MENU4 = "MyInfo_downHistory.fxml";
	public static final String MENU5 = "";
	public static final String MENU6 = "My_Playlist_Main.fxml";
	public Pane mainPane;
	
	public void setPane(Pane pane) {
		mainPane = pane;
	}
	
	public void setBtn(JFXButton btn1, JFXButton btn2, JFXButton btn3, JFXButton btn4, JFXButton btn5, JFXButton btn6) {
		setLink(btn1, MENU1);
		setLink(btn2, MENU2);
		setLink(btn3, MENU3);
		setLink(btn4, MENU4);
		setLink(btn5, MENU5);
		setLink(btn6, MENU6);
	}

	private void setLink(JFXButton btn, String url) {
		btn.setOnAction(e->{
			Parent upParent = null;
			try {
				upParent = FXMLLoader.load(getClass().getResource(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
			mainPane.getChildren().setAll(upParent);
		});
	}
}
