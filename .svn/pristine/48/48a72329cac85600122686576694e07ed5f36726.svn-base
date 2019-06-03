package kr.or.ddit.nn.view.album;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.MainController;
import kr.or.ddit.nn.service.album.albumService;
import kr.or.ddit.nn.service.chart.ChartService;
import kr.or.ddit.nn.service.chart_item.Chart_itemService;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.vo.music.viewMusicVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class AlbumDetail_controller implements Initializable{

	private @FXML Pane main_pane;
	private @FXML ImageView albumArt;
	private @FXML TableView<viewMusicVO> music_table;
	private @FXML TableColumn<viewMusicVO, String> albumArt_col;
	private @FXML TableColumn<viewMusicVO, String> music_name_col;
	private @FXML TableColumn<viewMusicVO, String> music_playtime_col;
	private @FXML TableColumn<viewMusicVO, String> music_artist_col;
	private @FXML TableColumn<viewMusicVO, String> music_genre;
	private @FXML JFXTextField albumName;
	private @FXML JFXTextField album_artist;
	private @FXML JFXTextField albumDate;
	private @FXML JFXButton cencle_btn;
	
	private Registry reg;
	private albumService as;
	private ObservableList<viewMusicVO> mList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			as = (albumService) reg.lookup("albumService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		MainController.albumArt(albumArt_col);
		mList = FXCollections.observableArrayList();
		albumArt_col.setCellValueFactory(new PropertyValueFactory<>("album_img"));
		music_name_col.setCellValueFactory(new PropertyValueFactory<>("music_name"));
		music_playtime_col.setCellValueFactory(new PropertyValueFactory<>("music_playtime"));
		music_artist_col.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
		music_genre.setCellValueFactory(new PropertyValueFactory<>("music_genre"));
		
		albumArt_col.setStyle("-fx-alignment: CENTER;");
		music_name_col.setStyle("-fx-alignment: CENTER;");
		music_playtime_col.setStyle("-fx-alignment: CENTER;");
		music_artist_col.setStyle("-fx-alignment: CENTER;");
		music_genre.setStyle("-fx-alignment: CENTER;");
		
		try {
			mList.setAll(as.selectNewAlbumDetail(MainController.albumV.getAlbum_id()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		music_table.setItems(mList);
		
		albumArt.setImage(new Image(new File("img\\albumArt\\" + MainController.albumV.getAlbum_img()).toURI().toString()));
		albumName.setText(MainController.albumV.getAlbum_name());
		albumDate.setText(mList.get(0).getAlbum_date());
		album_artist.setText(mList.get(0).getArtist_name());
		album_artist.setEditable(false);
		albumDate.setEditable(false);
		albumName.setEditable(false);
		cencle_btn.setOnAction(e->{
			MainController.dialog.close();
		});
		
	}

}
