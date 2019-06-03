package kr.or.ddit.nn.view.mypage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MyPage_main_Controller implements Initializable{

	@FXML BorderPane Mypage;
	@FXML ImageView main_logo;
	@FXML ImageView main_title;
	@FXML JFXButton mylevel_btn;
	@FXML JFXButton buyhistory_btn;
	@FXML JFXButton my_music_btn;
	@FXML JFXButton myinfo_btn;
	@FXML Pane mainPane;
	
	/**
	 * 센터 화면 변경
	 * @param fxmlURL
	 * @return
	 */
	
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
		
		my_music_btn.setOnAction(e-> {
			changeScene("My_Playlist_Main.fxml");
			
		});
		
	}

}
