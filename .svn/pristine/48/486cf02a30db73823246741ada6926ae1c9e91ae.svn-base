package kr.or.ddit.nn.view.chart;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.service.playlistitem.PlaylistItemService;
import kr.or.ddit.nn.view.myInfo.My_Playlistitem_Controller;
import kr.or.ddit.nn.vo.music.viewMusicVO;
import kr.or.ddit.nn.vo.playlist.PlaylistVO;

public class Chart_playlistitem_controller implements Initializable{
	
	public static int infoplaylistitem;
	
	@FXML BorderPane main;
	@FXML Pane mainPane;
	@FXML JFXButton close_btn;
	@FXML TableView<viewMusicVO> playlist_table;
	@FXML TableColumn<viewMusicVO, Integer> music_id_col;
	@FXML TableColumn<viewMusicVO, String> music_name_col;
	@FXML TableColumn<viewMusicVO, String> artist_name_col;
	@FXML TableColumn<viewMusicVO, String> music_playtime_col;
	@FXML TableColumn<viewMusicVO, String> album_name_col;
	@FXML Pagination pge;
	
	private Registry reg;
	private PlaylistItemService plis;
	private int from, to, itemsForPage;
	private ObservableList<viewMusicVO> piList;
	private ObservableList<viewMusicVO> currentPageDate;
	
	/**
	 * 창전환
	 * @param url
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
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			plis = (PlaylistItemService) reg.lookup("PlaylistItemService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		piList = FXCollections.observableArrayList();
		try {
			piList.setAll(plis.selectPlayListItem(infoplaylistitem));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		music_id_col.setCellValueFactory(new PropertyValueFactory<>("music_id"));
		music_name_col.setCellValueFactory(new PropertyValueFactory<>("music_name"));
		artist_name_col.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
		music_playtime_col.setCellValueFactory(new PropertyValueFactory<>("music_playtime"));
		album_name_col.setCellValueFactory(new PropertyValueFactory<>("album_name"));
		
		playlist_table.setItems(piList);
		
 	    itemsForPage = 10; // 한페이지에 보여줄 항목 수 설정

		int totItemCnt = piList.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		pge.setPageCount(totPageCnt); // 전체 페이지 수 설정

		pge.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				playlist_table.setItems(getTableViewDate(from, to));

				return playlist_table;
			}

		});
		
		close_btn.setOnAction(e-> {
			changeScene("Chart_playlist.fxml");
		});
	}
	
	private ObservableList<viewMusicVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = piList.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(piList.get(i));
		}
		return currentPageDate;
	}

	

}
