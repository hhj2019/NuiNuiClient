package kr.or.ddit.nn.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.vo.music.Music_searchVO;
import kr.or.ddit.nn.vo.music.viewMusicVO;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;

public class Serch_Controller implements Initializable {

	@FXML
	private BorderPane main;
	@FXML
	private Pane mainPane;
	@FXML
	private JFXButton close_btn;
	@FXML
	private TableView<viewMusicVO> playlist_table;
	@FXML
	private TableColumn<viewMusicVO, Integer> music_conut_col;
	@FXML
	private TableColumn<viewMusicVO, String> music_name_col;
	@FXML
	private TableColumn<viewMusicVO, String> artist_name_col;
	@FXML
	private TableColumn<viewMusicVO, String> music_playtime_col;
	@FXML
	private TableColumn<viewMusicVO, String> album_name_col;
	@FXML
	private Pagination pge;

	public static String value;

	private Registry reg;
	private musicService ms;
	private int from, to, itemsForPage;
	private ObservableList<viewMusicVO> piList;
	private ObservableList<viewMusicVO> currentPageDate;
	private Music_searchVO msh;

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
			ms = (musicService) reg.lookup("musicService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		msh = new Music_searchVO();
		piList = FXCollections.observableArrayList();

		msh.setValue(value);
		msh.setValue1(value);

		for (int i = 0; i < 2; i++) {

			try {
				piList.setAll(ms.serch(msh));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		music_conut_col.setCellValueFactory(new PropertyValueFactory<>("music_count"));
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
			changeScenes("Main.fxml");
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

	public void changeScenes(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMain.pStage.getScene().setRoot(parent);
	}
}
