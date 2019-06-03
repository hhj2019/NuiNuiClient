package kr.or.ddit.nn.view.myInfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.playlist.PlaylistService;
import kr.or.ddit.nn.vo.playlist.PlaylistVO;
import javafx.scene.layout.BorderPane;

public class Playlist_insert_Controller implements Initializable{
	
	@FXML 
	private BorderPane main_pane;
	@FXML 
	private Pane center_pane;
	@FXML
	private JFXTextField playlist_Name;
	@FXML
	private JFXButton close_btn;
	@FXML
	private JFXButton btn_insert_Playlist;
	
	private Registry reg;
	private PlaylistService pls;
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		main_pane.setCenter(center_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		main_pane.setCenter(parent);
		return loader;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		close_btn.setOnAction(e->{
	 		changeScene("My_Playlist_Main.fxml");
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
					changeScene("My_Playlist_Main.fxml");
				}
				if(cnt < 0) {
					errMsg("실패!", "오류를 찾아주세요.");
					changeScene("My_Playlist_Main.fxml");
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