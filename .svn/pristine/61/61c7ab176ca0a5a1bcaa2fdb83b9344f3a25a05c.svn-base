package kr.or.ddit.nn.view.newMusic;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.vo.music.viewMusicVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class music_detail_controller2 implements Initializable{

	@FXML BorderPane Main_pane;
	@FXML Pane center_pane;
	@FXML Label music_name;
	@FXML JFXTextField artist_text;
	@FXML JFXTextField album_text;
	@FXML JFXTextField genre_text;
	@FXML JFXTextArea lyrics_text;
	@FXML JFXTextField date_text;
	@FXML JFXButton close_btn;
	@FXML ImageView music_img;
	
	private musicService ms;
	private Registry reg;
	private viewMusicVO vmv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ms = (musicService) reg.lookup("musicService");
		}catch (RemoteException e2) {
			e2.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		vmv = new viewMusicVO();
		try {
			vmv = ms.selectMusicInfo(NewMusic_controller.music_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		artist_text.setText(vmv.getArtist_name());
		music_name.setText(vmv.getMusic_name());
		album_text.setText(vmv.getAlbum_name());
		genre_text.setText(vmv.getMusic_genre());
		lyrics_text.setText(vmv.getMusic_lyrics());
		date_text.setText(vmv.getAlbum_date());
		music_img.setImage(new Image(new File("img\\albumArt\\"+vmv.getAlbum_img()).toURI().toString()));
		artist_text.setEditable(false);
		album_text.setEditable(false);
		genre_text.setEditable(false);
		lyrics_text.setEditable(false);
		date_text.setEditable(false);
		
		close_btn.setOnAction(e->{
			NewMusic_controller.dialog.close();
		});
	}
	
}
