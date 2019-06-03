package kr.or.ddit.nn.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import game.gui.AppStart;
import im.conversations.app.launch.MainDemo;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.nn.service.album.albumService;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.vo.album.albumVO;
import kr.or.ddit.nn.vo.music.viewMusicVO;
import util.ExecutablePlayer;

public class MainController implements Initializable {
	
	@FXML
	private BorderPane mainPane;
	@FXML
	private Pane topPane;
	@FXML
	private Pane left_pane;
	@FXML
	private Pane center_pane;
	
	public Pane getCenter_pane() {
		return center_pane;
	}

	public void setCenter_pane(Pane center_pane) {
		this.center_pane = center_pane;
	}

	@FXML
	private ImageView main_logo;
	@FXML
	private ImageView main_title;
	@FXML
	private JFXTextField serch_textfiled;
	@FXML
	private JFXButton serch_btn;
	@FXML
	private JFXButton login_btn;
	@FXML
	private Circle login_img;
	@FXML
	private JFXButton new_album_btn;
	@FXML
	private JFXButton main_chart_btn;
	@FXML
	private JFXButton chart_btn;
	@FXML
	private ImageView chart_img;
	@FXML
	private JFXButton genre_btn;
	@FXML
	private ImageView genre_img;
	@FXML
	private JFXButton chatbot_btn;
	@FXML
	private ImageView chatbot_img;
	@FXML
	private JFXButton info_btn;
	@FXML
	private ImageView info_img;
	@FXML
	private JFXButton ticket_btn;
	@FXML
	private ImageView titcket_img;
	@FXML
	private JFXButton event_btn;
	@FXML
	private ImageView event_img;
	@FXML
	private JFXButton newmusic_btn;
	@FXML
	private ImageView newmusic_img;
	@FXML
	private VBox album_vbox1;
	@FXML
	private VBox album_vbox2;
	@FXML
	private VBox chart_vbox;
	@FXML
	private HBox hbox;
	
	@FXML
	private JFXButton player;
	@FXML
	private JFXButton game;
	
	public String name = "MainController.";
	private musicService musicService;
	private albumService albumService;
	private Registry reg;
	private List<albumVO> albumList;
	static public int music_id;
	static public Stage dialog;
	static public albumVO albumV = new albumVO();
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public void changeCenter(String fxmlURL) {
//		mainPane.setCenter(center_pane);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.setCenter(parent);
//		return loader;
	}
	
	/**
	 * 메인 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeMain(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().setAll(parent);
		return loader;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**************** START: 외부파일 실행 테스트 ****************/
//		ProcessBuilder processBuilder = new ProcessBuilder();
//
//        // Run this on Windows, cmd, /c = terminate after this run
//        processBuilder.command("cmd.exe", "/c", "ping -n 1 google.com");
//
//        try {
//
//            Process process = processBuilder.start();
//
//			// blocked :(
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            int exitCode = process.waitFor();
//            System.out.println("\nExited with error code : " + exitCode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		/************ END: 외부파일 실행 테스트 ****************/
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			musicService = (musicService) reg.lookup("musicService");
			albumService = (albumService) reg.lookup("albumService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		/************ START: Session test ************/

		checkSession();
		// Session.setUser(null);
		// checkSession();

		/************ END: Session test ************/
		
		/**
		 * 로그인 버튼 클릭시 로그인 화면으로 전환(메인 화면 -> 로그인 화면)
		 */
		login_btn.setOnAction(e -> {
			System.out.println("로그인 클릭");

			// Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			// // 로그인 화면

			// loginStage.initModality();
			// loginStage.initOwner(ClientMain.pStage); // ClientMain.pStage를 기준으로 새로운 씬

			Parent newParent = null;

			try {
				newParent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/view/login/login.fxml"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			Stage loginStage = new Stage(); // 새로 띄워줄 화면
			Scene scene = new Scene(newParent);
			loginStage.setScene(scene);
			
			/**
			 * 로그인 창에서 닫기 클릭했을 때
			 */
			

			/**
			 * 로그인 창에서 로그인 버튼 클릭
			 */
			
			loginStage.show();
			
		});
		
		/**
		 * 고객센터 버튼 클릭시
		 */
		info_btn.setOnAction(e -> {
			changeCenter("../view/CustomerService/CS_main.fxml");
			// 자기 버튼 색 변경
			info_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			chatbot_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			chart_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			newmusic_btn.setStyle("-fx-background-color: rgb(140,0,140)");	
		});
		
		/**
		 * 이벤트 버튼 클릭시
		 */
		event_btn.setOnAction(e->{
			changeCenter("../view/event/event_main.fxml");
			// 자기 버튼 색 변경
			event_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			chatbot_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			info_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			chart_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			newmusic_btn.setStyle("-fx-background-color: rgb(140,0,140)");		
		});
		
		/**
		 * 차트 버튼 클릭시
		 */
		chart_btn.setOnAction(e->{
			changeCenter("../view/chart/chart_main.fxml");
			// 자기 버튼 색 변경
			chart_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			chatbot_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			info_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			newmusic_btn.setStyle("-fx-background-color: rgb(140,0,140)");		
		});
		
		/**
		 * 티켓버튼 클릭시
		 */
		ticket_btn.setOnAction(e->{
			changeCenter("/kr/or/ddit/nn/view/ticket/buy_ticket_main.fxml");
			// 자기 버튼 색 변경
			ticket_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			chatbot_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			info_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			chart_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			newmusic_btn.setStyle("-fx-background-color: rgb(140,0,140)");	
		});
		
		newmusic_btn.setOnAction(e->{
			changeCenter("/kr/or/ddit/nn/view/newMusic/NewMusic.fxml");
			newmusic_btn.setStyle("-fx-background-color: rgb(100,0,100)");
			// 다른버튼 기본으로 변경
			chatbot_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			info_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			chart_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
		});
		
		/**
		 * 메인로고, 메인 이름 클릭시 메인으로 변경
		 */
		main_logo.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				changeMain("Main.fxml");
			}
		});
		
		main_title.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				changeMain("Main.fxml");				
			}
		});
		
		/**
		 * 챗봇버튼 클릭시 챗봇 프로그램 실행
		 */
		chatbot_btn.setOnAction(e -> {
			MainDemo demo = new MainDemo();
			try {
				demo.start(MainDemo.myStage);	// static하게 저장한 Stage를 불러옴
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			newmusic_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			info_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			chart_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			event_btn.setStyle("-fx-background-color: rgb(140,0,140)");
			ticket_btn.setStyle("-fx-background-color: rgb(140,0,140)");
		});
		
		serch_btn.setOnAction(e-> {
			
			Serch_Controller.value = serch_textfiled.getText();
			
			changeCenter("serch.fxml");
		});
		
		File file = new File("img/profile-user.png");
		Image img = new Image(file.toURI().toString());
		login_img.setFill(new ImagePattern(img));
		
		newAlbum();
		mainChart();
		
		main_chart_btn.setOnAction(e->{
			changeCenter("/kr/or/ddit/nn/view/chart/chart_main.fxml");
		});
		new_album_btn.setOnAction(e->{
			changeCenter("/kr/or/ddit/nn/view/chart/NewMusic.fxml");
		});
		
		
		/**
		 * 미니플레이어 실행(exe파일)
		 */
		player.setOnAction(e-> {
			ExecutablePlayer.foo();
		});
		
		/**
		 * 게임 시작 버튼
		 */
		game.setOnAction(e -> {
			AppStart gameStart = new AppStart();
			try {
				gameStart.start(AppStart.gameStage);	// static하게 저장한 Stage를 불러옴
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	private Logout_mainController logoutController = null;

	private void checkSession() {
		if (Session.getUser() == null)
			System.out.println("비로그인");
		else {
			System.out.println("로그인");
			//changeScene("Logout_main.fxml");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Logout_main.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
				logoutController = loader.getController();
				logoutController.getSession_name().setText(Session.getUser().getMem_name());
//				System.out.println("로그인된 세션 사용자 : " + Session.getUser().getMem_name());
				mainPane.setTop(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 앨범 사진 컬럼에 추가하기
	 * @param tv
	 */
	@SuppressWarnings("unchecked")
	public static void albumArt(TableColumn tv) {
		tv.setCellFactory(new Callback<TableColumn<albumVO, String>,TableCell<albumVO, String>>(){        
			@Override
			public TableCell<albumVO, String> call(TableColumn<albumVO, String> param) {                
				TableCell<albumVO, String> cell = new TableCell<albumVO, String>(){
					@Override
					public void updateItem(String item, boolean empty) {                        
						if(item!=null){                            
							HBox box= new HBox();
							box.setSpacing(10) ;
							box.setAlignment(Pos.TOP_CENTER);
							
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							imageview.setImage(new Image(new File("img\\albumArt\\"+item).toURI().toString())); 
							
							box.getChildren().addAll(imageview); 
							//SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}
			
		});
	}
	
	private void newAlbum() {
		try {
			albumList = albumService.selectNewAlbum();
			System.out.println(albumList.size());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < 4;i++) {
			if(i<2) {
				File albumArt = new File("img\\albumArt\\"+albumList.get(i).getAlbum_img());
				Image albumImg = new Image(albumArt.toURI().toString());
				ImageView albumImgV = new ImageView(albumImg);
				albumImgV.setFitWidth(150);
				albumImgV.setFitHeight(150);
				Label albumLabel = new Label(albumList.get(i).getAlbum_name());
				albumLabel.setPrefWidth(172);
				albumLabel.setStyle("-fx-font-size: 13pt;");
				albumLabel.setAlignment(Pos.CENTER);
				album_vbox1.getChildren().addAll(albumImgV, albumLabel);
				album_vbox1.setAlignment(Pos.CENTER);
				album_vbox1.setSpacing(10);
				albumImgV.setId("album_Img" + i);
				albumImgV.setOnMouseClicked(e->{
					albumV = new albumVO();
					albumV = albumList.get(Integer.parseInt(albumImgV.getId().substring(9,10)));
					
					dialog = new Stage(StageStyle.UTILITY);

					// 3. 부모창 지정
					dialog.initOwner(ClientMain.pStage);

					dialog.setTitle("앨범 정보");
					
					Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("../view/album/albumdetail.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 5. Scene객체 생성해서 컨테이너 객체 추가
					Scene scene = new Scene(parent);
					
					// 6. Stage객체에 Scene객체 추가
					dialog.setScene(scene);
					dialog.setResizable(false);
					dialog.show();
				});
			}else {
				File albumArt2 = new File("img\\albumArt\\"+albumList.get(i).getAlbum_img());
				Image albumImg2 = new Image(albumArt2.toURI().toString());
				ImageView albumImgV2 = new ImageView(albumImg2);
				albumImgV2.setFitWidth(150);
				albumImgV2.setFitHeight(150);
				Label albumLabel2 = new Label(albumList.get(i).getAlbum_name());
				albumLabel2.setPrefWidth(172);
				albumLabel2.setStyle("-fx-font-size: 13pt;");
				albumLabel2.setAlignment(Pos.CENTER);
				album_vbox2.getChildren().addAll(albumImgV2, albumLabel2);
				album_vbox2.setAlignment(Pos.CENTER);
				album_vbox2.setSpacing(10);
				albumImgV2.setId("album_Img" + i);
				albumImgV2.setOnMouseClicked(e->{
					albumV = new albumVO();
					albumV = albumList.get(Integer.parseInt(albumImgV2.getId().substring(9,10)));
					dialog = new Stage(StageStyle.UTILITY);

					// 3. 부모창 지정
					dialog.initOwner(ClientMain.pStage);

					dialog.setTitle("앨범 정보");
					
					Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("../view/album/albumdetail.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// 5. Scene객체 생성해서 컨테이너 객체 추가
					Scene scene = new Scene(parent);
					
					// 6. Stage객체에 Scene객체 추가
					dialog.setScene(scene);
					dialog.setResizable(false);
					dialog.show();
				});
			}
			
		}
		
		
	}
	
	private List<viewMusicVO> musicList;
	private int im;
	private void mainChart() {
		try {
			musicList = musicService.selectMainChartMusic();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		for(im = 0; im < musicList.size(); im++) {
			Label chartName = new Label(musicList.get(im).getMusic_name() + " - " + musicList.get(im).getArtist_name());
			chartName.setAlignment(Pos.CENTER);
			chartName.setStyle("-fx-font-size: 10pt;");
			chart_vbox.getChildren().add(chartName);
			chart_vbox.setAlignment(Pos.CENTER);
			chart_vbox.setSpacing(10);
			
		}
		
	}
//	public void changeScene(String fxmlURL) {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
//		Parent parent = null;
//		try {
//			parent = loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
////		parent.getChildrenUnmodifiable().get(1)
////		 Platform.runLater();
//		// topPane.getChildren().setAll(parent);
//		mainPane.setTop(parent);
//	}

}
