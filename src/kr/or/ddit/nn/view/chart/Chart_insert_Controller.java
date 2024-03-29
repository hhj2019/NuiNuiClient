package kr.or.ddit.nn.view.chart;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.playlist.PlaylistService;
import kr.or.ddit.nn.vo.playlist.PlaylistVO;
import javafx.scene.layout.BorderPane;

public class Chart_insert_Controller implements Initializable{
	
	@FXML
	private Pane mainPane;
	@FXML
	private JFXTextField playlist_Name;
	@FXML
	private JFXButton close_btn;
	@FXML
	private JFXButton btn_insert_Playlist;
	
	private Registry reg;
	private PlaylistService pls;
	
	private void changeScene(String url) {
		Parent upParent = null;
		try {
			upParent = FXMLLoader.load(getClass().getResource(url));
		} catch (IOException e1) {
			e1.printStackTrace();
		}			
		mainPane.getChildren().setAll(upParent);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		close_btn.setOnAction(e->{
	 		changeScene("chart_main.fxml");
		});
		
		
		btn_insert_Playlist.setOnAction(e-> {
			try {
				reg = LocateRegistry.getRegistry("localhost", 8888);
				pls = (PlaylistService) reg.lookup("PlaylistService");
			}catch (RemoteException e2) {
				e2.printStackTrace();
			}catch (NotBoundException e2) {
				e2.printStackTrace();
			}
			
			PlaylistVO vo = new PlaylistVO();
			
			vo.setPlaylist_name(playlist_Name.getText());
			// userId
			vo.setMem_id(Session.getUser().getMem_id());
			int cnt = 0;
			
			try {
				cnt++;
				pls.insertPlaylist(vo);
				if(cnt > 0) {
					infoMsg("성공!", "플레이리스트가 추가되었습니다");
					changeScene("Chart_playlist.fxml");
				}
				if(cnt < 0) {
					errMsg("실패!", "오류를 찾아주세요.");
					changeScene("Chart_playlist.fxml");
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}
	
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
	
