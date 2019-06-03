package kr.or.ddit.nn.view.manager.music.album;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import kr.or.ddit.nn.main.MainController;
import kr.or.ddit.nn.service.album.albumService;
import kr.or.ddit.nn.vo.album.albumVO;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import com.jfoenix.controls.JFXButton;

public class manager_album_controller implements Initializable{

	@FXML BorderPane center_music_pane;
	@FXML Pane album_pane;
	@FXML TableView<albumVO> album_table;
	@FXML TableColumn<albumVO, Integer> album_id_col;
	@FXML TableColumn<albumVO, String> album_img_col;
	@FXML TableColumn<albumVO, String> album_name_col;
	@FXML TableColumn<albumVO, String> artist_name_col;
	@FXML TableColumn<albumVO, String> album_date_col;
	@FXML JFXButton album_insert_btn;
	@FXML JFXButton album_update_btn;
	@FXML JFXButton album_delete_btn;
	@FXML JFXButton music_btn;
	@FXML JFXButton album_btn;
	@FXML JFXButton artist_btn;
	
	private Registry reg;
	private albumService as;
	private ObservableList<albumVO> aList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			as = (albumService) reg.lookup("albumService");
		}catch (RemoteException e2) {
			e2.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		MainController.albumArt(album_img_col);
		
		album_date_col.setCellValueFactory(new PropertyValueFactory<>("album_date"));
		album_img_col.setCellValueFactory(new PropertyValueFactory<>("album_img"));
		album_name_col.setCellValueFactory(new PropertyValueFactory<>("album_name"));
		artist_name_col.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
		aList = FXCollections.observableArrayList();
		try {
			aList.setAll(as.selectAllAlbum());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		album_table.setItems(aList);
		
		album_insert_btn.setOnAction(e->{
			changeScene("insert_album.fxml");
		});
		album_update_btn.setOnAction(e->{
			update_album_controller.artist = album_table.getSelectionModel().getSelectedItem().getArtist_name();
			update_album_controller.name = album_table.getSelectionModel().getSelectedItem().getAlbum_name();
			update_album_controller.date = album_table.getSelectionModel().getSelectedItem().getAlbum_date();
			update_album_controller.img = album_table.getSelectionModel().getSelectedItem().getAlbum_img();
			update_album_controller.id = album_table.getSelectionModel().getSelectedItem().getAlbum_id();
			changeScene("update_album.fxml");
		});
		album_delete_btn.setOnAction(e->{
			albumVO av = new albumVO();
			av.setAlbum_id(album_table.getSelectionModel().getSelectedItem().getAlbum_id());
			try {
				as.deleteAlbum(av);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			changeScene("manager_album.fxml");
		});
		
		artist_btn.setOnAction(e->{
			changeScene("../artist/Manager_artist.fxml");
		});
		music_btn.setOnAction(e->{
			changeScene("../music/manager_music.fxml");
		});
		album_btn.setOnAction(e->{
			changeScene("manager_album.fxml");
		});
		
		
		// SETTING THE CELL FACTORY FOR THE ALBUM ART                 
		
	}
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		center_music_pane.setCenter(album_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		center_music_pane.setCenter(parent);
		return loader;
	}

}
