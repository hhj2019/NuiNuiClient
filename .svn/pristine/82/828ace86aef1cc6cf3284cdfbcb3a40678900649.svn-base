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
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.like.LikeService;
import kr.or.ddit.nn.service.playlist.PlaylistService;
import kr.or.ddit.nn.vo.like.LikeVO;
import kr.or.ddit.nn.vo.playlist.PlaylistVO;

public class My_info_Like_Controller implements Initializable {
		
	
	@FXML BorderPane main_pane;
	@FXML Pane center_pane;
	@FXML TableView<LikeVO> playlist_table;
	@FXML TableColumn<LikeVO, Integer> music_id_col;
	@FXML TableColumn<LikeVO, String> music_name_col;
	@FXML TableColumn<LikeVO, String> artist_name_col;
	@FXML TableColumn<LikeVO, Integer> music_playtime_col;
	@FXML TableColumn<LikeVO, String> album_name_col;
	@FXML Pagination pge;
	@FXML JFXButton cancel_btn;
	
	private Registry reg;
	private LikeService lsv;
	
	int cnt_de = 0;
	int real_no = 0;

	private int from, to, itemsForPage;
	private ObservableList<LikeVO> currentPageDate;
	private ObservableList<LikeVO> lList;
	
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
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			lsv = (LikeService) reg.lookup("LikeService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		lList = FXCollections.observableArrayList();
		try {
			lList.setAll(lsv.selectLike(Session.getUser().getMem_id()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		music_id_col.setCellValueFactory(new PropertyValueFactory<>("music_id")); 
		music_name_col.setCellValueFactory(new PropertyValueFactory<>("music_name"));
		artist_name_col.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
		music_playtime_col.setCellValueFactory(new PropertyValueFactory<>("music_playtime"));
		album_name_col.setCellValueFactory(new PropertyValueFactory<>("album_name"));
		
		playlist_table.setItems(lList);
		
		itemsForPage = 10; // 한페이지에 보여줄 항목 수 설정

		int totItemCnt = lList.size();
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
		
		/**
		 * 좋아요삭제 버튼
		 */
		cancel_btn.setOnAction(e-> {
			real_no = playlist_table.getSelectionModel().getSelectedItem().getMusic_id();

			try {
				cnt_de = lsv.deleteLike(real_no);

				if (cnt_de > 0) {
					infoMsg("성공!", "좋아요가 삭제되었습니다!");
					changeScene("My_info_Like.fxml");
				} else {
					System.out.println("삭제 안됨..");
					errMsg("실패!", "오류를 찾아주세요.");
				}

			} catch (RemoteException e1) {
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

	private ObservableList<LikeVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = lList.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(lList.get(i));
		}
		return currentPageDate;
	}

}
