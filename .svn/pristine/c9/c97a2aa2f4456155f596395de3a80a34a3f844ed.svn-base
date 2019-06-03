package kr.or.ddit.nn.view.myInfo;

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
import kr.or.ddit.nn.vo.music.viewMusicVO;

public class My_Playlistitem_Controller implements Initializable {

	@FXML
	private JFXButton close_btn;
	@FXML
	private TableView<viewMusicVO> playlist_table;

	public static int infoplaylistitem;

	private Registry reg;
	private PlaylistItemService plis;
	private int from, to, itemsForPage;
	private ObservableList<viewMusicVO> piList;
	private ObservableList<viewMusicVO> currentPageDate;

	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private Pagination pge;
	@FXML
	private TableColumn<viewMusicVO, Integer> music_id_col;
	@FXML
	private TableColumn<viewMusicVO, String> music_name_col;
	@FXML
	private TableColumn<viewMusicVO, String> artist_name_col;
	@FXML
	private TableColumn<viewMusicVO, String> music_playtime_col;
	@FXML
	private TableColumn<viewMusicVO, String> album_name_col;

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
		close_btn.setOnAction(e -> {
			changeScene("My_Playlist_Main.fxml");
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