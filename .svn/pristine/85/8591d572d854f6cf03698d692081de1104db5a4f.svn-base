package kr.or.ddit.nn.view.manager.music.music;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

public class lyric_controller implements Initializable{

	@FXML Pane main_pane;
	@FXML JFXButton ok_btn;
	@FXML JFXTextArea text_area;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		text_area.setText(manager_music_controller.lyric);
		ok_btn.setOnAction(e->{
			manager_music_controller.dialog.close();
		});
	}

}
