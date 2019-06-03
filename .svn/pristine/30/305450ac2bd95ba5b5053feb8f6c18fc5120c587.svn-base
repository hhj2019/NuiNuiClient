package kr.or.ddit.nn.view.myInfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.music.BuyMusicHistoryService;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.view.download.DownloadFileController;
import kr.or.ddit.nn.vo.music.BuyMusicHistoryVO;
import kr.or.ddit.nn.vo.music.musicVO;
import util.Server;
import javafx.scene.layout.BorderPane;

public class MyInfo_DownHistoryController implements Initializable {

	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private TableView<TableVO> down_table;
	@FXML
	private TableColumn<TableVO, Integer> down_id;
	@FXML
	private TableColumn<TableVO, String> down_name;
	@FXML
	private TableColumn<TableVO, String> down_date;
	@FXML
	private JFXButton down_btn;

	private BuyMusicHistoryService buyMusicHistoryService;
	private musicService musicServiced;

	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	@FXML
	void downMusic(MouseEvent event) {

		TableVO item = down_table.getSelectionModel().getSelectedItem();
		if (item == null)
			errMsg("선택 오류", "테이블에서 노래를 선택해주세요.");
		else {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/kr/or/ddit/nn/view/download/download_info.fxml"));

			Stage downlaodStage = new Stage();
			DownloadFileController downloadController = new DownloadFileController(downlaodStage);

			downloadController.musicId = item.getMusicId();
			downloadController.reDownload = true;
			loader.setController(downloadController);

			Parent p = null;
			try {
				p = loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene downScene = new Scene(p);
			downlaodStage.setScene(downScene);
			downlaodStage.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		MenuConnector menuCon = new MenuConnector();
//		menuCon.setPane(mainPane);
//		menuCon.setBtn(myinfo_btn, mylevel_btn, buyhistory_btn, musichistory_btn, like_btn, playlist_btn);

		try {
			buyMusicHistoryService = (BuyMusicHistoryService) Server.reg.lookup("buyMusicHistory");
			musicServiced = (musicService) Server.reg.lookup("musicService");
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}

		List<BuyMusicHistoryVO> list = null;

		try {
			list = buyMusicHistoryService.viewHistory(Session.getUser().getMem_id());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		ObservableList<TableVO> historyData = FXCollections.observableArrayList();
		int cnt = 1;
		for (BuyMusicHistoryVO item : list) {
			musicVO thisMusic = null;
			try {
				thisMusic = musicServiced.selectMusic(item.getMusic_id());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			String name = thisMusic.getMusic_name();
			Date date = item.getBuy_date();

			historyData.add(new TableVO(item.getMusic_id(), cnt++, name, date));
		}

		down_id.setCellValueFactory(cellData -> cellData.getValue().getNum().asObject());
		down_name.setCellValueFactory(cellData -> cellData.getValue().getMusicName());
		down_date.setCellValueFactory(cellData -> cellData.getValue().getBuyDate());

		down_table.setItems(historyData);
	}

	private class TableVO {

		private int musicId;

		public int getMusicId() {
			return musicId;
		}

		public void setMusicId(int musicId) {
			this.musicId = musicId;
		}

		private IntegerProperty num;
		private StringProperty musicName;
		private StringProperty buyDate;

		TableVO() {
			this(0, 1, null, null);
		}

		TableVO(int musicId, int num, String musicName, Date date) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			this.musicId = musicId;
			this.num = new SimpleIntegerProperty(num);
			this.musicName = new SimpleStringProperty(musicName);
			this.buyDate = new SimpleStringProperty(f.format(date));

		}

		public IntegerProperty getNum() {
			return num;
		}

		public void setNum(IntegerProperty num) {
			this.num = num;
		}

		public StringProperty getMusicName() {
			return musicName;
		}

		public void setMusicName(StringProperty musicName) {
			this.musicName = musicName;
		}

		public StringProperty getBuyDate() {
			return buyDate;
		}

		public void setBuyDate(StringProperty buyDate) {
			this.buyDate = buyDate;
		}
	}
}
