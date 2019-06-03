package kr.or.ddit.nn.view.chart;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Player.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.nn.main.ClientMain;
import kr.or.ddit.nn.main.MainController;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.chart.ChartService;
import kr.or.ddit.nn.service.chart_item.Chart_itemService;
import kr.or.ddit.nn.service.like.LikeService;
import kr.or.ddit.nn.service.music.BuyMusicHistoryService;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.service.ticket.MyTicketService;
import kr.or.ddit.nn.view.download.DownloadFileController;
import kr.or.ddit.nn.view.payment.PaymentController;
import kr.or.ddit.nn.vo.chart.ChartVO;
import kr.or.ddit.nn.vo.like.LikeVO;
import kr.or.ddit.nn.vo.music.BuyMusicHistoryVO;
import kr.or.ddit.nn.vo.music.musicVO;
import kr.or.ddit.nn.vo.music.viewMusicVO;
import kr.or.ddit.nn.vo.ticket.MyTicketVO;
import util.Server;

public class chart_main_controller implements Initializable {

	@FXML
	BorderPane Main_pane;
	@FXML
	Pane center_pane;
	@FXML
	TableView<viewMusicVO> chart_table;
	@FXML
	TableColumn<viewMusicVO, Integer> music_rank;
	@FXML
	TableColumn<viewMusicVO, String> music_img;
	@FXML
	TableColumn<viewMusicVO, String> music_name;
	@FXML
	TableColumn<viewMusicVO, String> music_artist;
	@FXML
	TableColumn<viewMusicVO, String> album_name;
	@FXML
	JFXButton top100_btn;
	@FXML
	ImageView top100_img;
	@FXML
	JFXButton listen_btn;
	@FXML
	ImageView listen_img;
	@FXML
	JFXButton cart_btn;
	@FXML
	ImageView cart_img;
	@FXML
	JFXButton down_btn;
	@FXML
	ImageView down_img;
	@FXML
	JFXButton like_btn;
	@FXML
	ImageView like_img;

	private ChartService cs;
	private Chart_itemService cis;
	private musicService ms;
	private Registry reg;
	private LikeService lsv;

	private ObservableList<viewMusicVO> vmList;
	private ChartVO cv;
	private LikeVO lvo;
	static public int music_id;
	static public Stage dialog;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ms = (musicService) reg.lookup("musicService");
			cs = (ChartService) reg.lookup("chartService");
			cis = (Chart_itemService) reg.lookup("chart_itemService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		MainController.albumArt(music_img);
		vmList = FXCollections.observableArrayList();
		music_rank.setCellValueFactory(new PropertyValueFactory<>("music_rank"));
		music_img.setCellValueFactory(new PropertyValueFactory<>("album_img"));
		music_name.setCellValueFactory(new PropertyValueFactory<>("music_name"));
		music_artist.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
		album_name.setCellValueFactory(new PropertyValueFactory<>("album_name"));

		music_rank.setStyle("-fx-alignment: CENTER;");
		music_img.setStyle("-fx-alignment: CENTER;");
		music_name.setStyle("-fx-alignment: CENTER;");
		music_artist.setStyle("-fx-alignment: CENTER;");
		album_name.setStyle("-fx-alignment: CENTER;");

		try {
			cv = new ChartVO();
			cv.setChart_title("top100");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.KOREA);
			Date currentTime = new Date();
			String dTime = formatter.format(currentTime);
			System.out.println(dTime);
			cv.setChart_date(dTime);
			System.out.println(cv.getChart_title());

			vmList.setAll(ms.selectMusicCount(cis.selectChartMusic(cs.selectAllChart(cv).getChart_id())));
			for (int i = 0; i < vmList.size(); i++) {
				vmList.get(i).setMusic_rank(i + 1);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		chart_table.setItems(vmList);

		chart_table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					music_id = chart_table.getSelectionModel().getSelectedItem().getMusic_id();

					dialog = new Stage(StageStyle.UTILITY);

					// 3. 부모창 지정
					dialog.initOwner(ClientMain.pStage);

					dialog.setTitle("노래 정보");

					Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("music_detail.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 5. Scene객체 생성해서 컨테이너 객체 추가
					Scene scene = new Scene(parent);

					// 6. Stage객체에 Scene객체 추가
					dialog.setScene(scene);
					dialog.setResizable(false);
					dialog.show();
				}
				// 테스트중
				Main.music_file_temp = chart_table.getSelectionModel().getSelectedItem().getMusic_file();
				System.out.println(Main.music_file_temp);
			}
		});

		down_btn.setOnAction(e -> {
			viewMusicVO v = chart_table.getSelectionModel().getSelectedItem();
			if (v == null)
				errMsg("선택 오류", "테이블에서 노래를 선택해주세요.");
			else {
				int musicId = v.getMusic_id();
				Boolean reDownload = checkDownloaded(musicId);

				if (Session.getUser() == null) {
					errMsg("로그인 오류", "로그인 후 이용 가능합니다.");
				} else if (reDownload == true) {
					/** 한번 결제한 노래를 다시 다운로드 할 경우 **/
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/kr/or/ddit/nn/view/download/download_info.fxml"));

					Stage downlaodStage = new Stage();
					DownloadFileController downloadController = new DownloadFileController(downlaodStage);

					downloadController.musicId = musicId;
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
				} else {
					/** 새로운 노래를 다운로드 할 경우 **/
					List<MyTicketVO> ticketList = searchMyTicket();
					int total = 0;
					for (MyTicketVO item : ticketList) {
						total += item.getTicket_download_count();
					}

					if (total < 1) {
						/** 이용권이 없을 때 카드 결제 후 음원 다운로드 **/
						errMsg("이용 불가", "다운로드 가능한 이용권의 갯수가 부족합니다. 결제 창으로 이동합니다.");

						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/kr/or/ddit/nn/view/payment/payment.fxml"));
						PaymentController payCon = new PaymentController(musicId);
						loader.setController(payCon);
						Parent p = null;
						try {
							p = loader.load();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Stage payStage = new Stage();
						Scene payScene = new Scene(p);
						payStage.setScene(payScene);
						payStage.show();
					} else {
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/kr/or/ddit/nn/view/download/download_info.fxml"));

						Stage downlaodStage = new Stage();
						DownloadFileController downloadController = new DownloadFileController(downlaodStage);

						downloadController.musicId = musicId;
						downloadController.sampleTicketInfo = ticketList;
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
			}

		});

		cart_btn.setOnAction(e -> {
			if (Session.getUser() == null) {
				errMsg("로그인", "로그인 후 이용가능합니다.");
			} else if (Session.getUser() != null) {
				music_id = chart_table.getSelectionModel().getSelectedItem().getMusic_id();
				changeScene("Chart_playlist.fxml");
			}
		});

		like_btn.setOnAction(e -> {
			music_id = chart_table.getSelectionModel().getSelectedItem().getMusic_id();
			if (Session.getUser() == null) {
				errMsg("로그인", "로그인 후 이용가능합니다.");
			} else if (Session.getUser() != null) {
				Like();
			}
		});

	}

	private Boolean checkDownloaded(int musicId) {
		Boolean result = false;
		BuyMusicHistoryService buyHistoryService = null;
		try {
			buyHistoryService = (BuyMusicHistoryService) Server.reg.lookup("buyMusicHistory");
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		BuyMusicHistoryVO buyVO = new BuyMusicHistoryVO();
		buyVO.setMem_id(Session.getUser().getMem_id());
		buyVO.setMusic_id(musicId);
		try {
			int cnt = buyHistoryService.selectHistory(buyVO);
			if (cnt > 0)
				result = true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;

	}

	private List<MyTicketVO> searchMyTicket() {
		MyTicketService myTicketService = null;
		try {
			myTicketService = (MyTicketService) Server.reg.lookup("myTicketService");
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}

		List<MyTicketVO> list = null;
		try {
			list = myTicketService.viewUsableTicket(Session.getUser().getMem_id());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private void Like() {
		LikeService likeService = null;
		try {
			likeService = (LikeService) Server.reg.lookup("LikeService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		lvo = new LikeVO();

		lvo.setMem_id(Session.getUser().getMem_id());
		lvo.setMusic_id(music_id);
		int cnt = 0;
		try {
			cnt++;
			likeService.insertLike(lvo);
			if (cnt > 0) {
				OKMsg("성공!", "좋아요가 추가되었습니다");
				changeScene("chart_main.fxml");
			}
			if (cnt < 0) {
				errMsg("실패!", "오류를 찾아주세요.");
				changeScene("chart_main.fxml");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 에러메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	/**
	 * 완료메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void OKMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("성공");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(center_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main_pane.setCenter(parent);
		return loader;
	}

	/**
	 * 음악 스타트
	 * 
	 * @param event
	 */
	@FXML
	public void listen_musicStart(MouseEvent event) {

		for (int i = 0; i < chart_table.getItems().size(); i++) {
			System.out.println(i + "번째");
			if (chart_table.getSelectionModel().isSelected(i) == true) {
				System.out.println("테스트중");
				Main playerMain = new Main();
//				playerMain.playerStage = new Stage();
				Main.playerStage = new Stage();
				musicVO mVO = new musicVO();
				try {
//					playerMain.start(playerMain.playerStage);
					playerMain.start(Main.playerStage);
					playerMain.setButtonAction();
					music_id = chart_table.getSelectionModel().getSelectedItem().getMusic_id();
					mVO = ms.selectMusic(music_id);
					mVO.setMusic_count(mVO.getMusic_count() + 1);
					ms.updateMusic(mVO);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return;
			} else {
				System.out.println("nothing");
			}
		}

	}

	/**
	 * Top100 듣기
	 */
	@FXML
	public void listen_top100MusicStart() {
		Main playerMain = new Main();
//		playerMain.playerStage = new Stage();
		playerMain.start(playerMain.playerStage);
		playerMain.playTop100(); // 최신곡 디렉토리 생성 후 플레이하면 될듯
	}

}
