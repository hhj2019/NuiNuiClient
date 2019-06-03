package kr.or.ddit.nn.view.manager.music.artist;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.artist.artistService;
import kr.or.ddit.nn.vo.artist.artistVO;

public class insert_artist_controller implements Initializable{

	@FXML BorderPane main_pane;
	@FXML Pane center_pane;
	@FXML JFXTextField artist_name;
	@FXML JFXTextField artist_enter;
	@FXML JFXTextField artist_type;
	@FXML JFXTextField artist_country;
	@FXML JFXTextField artist_debut;
	@FXML JFXButton insert_btn;
	@FXML JFXButton cencel_btn;

	private Registry reg;
	private artistService as;
	private artistVO av;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			as = (artistService) reg.lookup("artistService");
		}catch (RemoteException e2) {
			e2.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		artist_debut.setPromptText("YYYY-MM-DD");
		
		insert_btn.setOnAction(e->{
			av = new artistVO();
			av.setArtist_name(artist_name.getText());
			av.setArtist_country(artist_country.getText());
			av.setArtist_debut(artist_debut.getText());
			av.setArtist_enter(artist_enter.getText());
			av.setArtist_type(artist_type.getText());
			try {
				if(artist_name.getText().isEmpty() &&
						artist_country.getText().isEmpty() &&
						artist_debut.getText().isEmpty() &&
						artist_enter.getText().isEmpty() &&
						artist_type.getText().isEmpty()) {
					errMsg("추가 실패", "빈 공간이 있습니다");
				}else {
					if(as.insertArtist(av)>0) {
						OKMsg("추가 완료", av.getArtist_name() + "을(를) 추가 완료 했습니다.");
						changeScene("Manager_artist.fxml");
					}else {
						errMsg("추가 실패", av.getArtist_name() + "을(를) 추가 실패했습니다");
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		
		cencel_btn.setOnAction(e->{
			changeScene("Manager_artist.fxml");
			
		});
		
	}
	
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
