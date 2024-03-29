package kr.or.ddit.nn.view.myInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.vo.member.MemberVO;
import javafx.scene.layout.BorderPane;

public class Mypage_Main_controller implements Initializable{

	@FXML 
	private BorderPane main_pane;
	@FXML 
	private Pane top_pane;
	@FXML 
	private Pane cneter_pane;
    @FXML
    private JFXButton buyhistory_btn;
    @FXML
    private JFXButton myinfo_btn;
    @FXML
    private JFXButton mylevel_btn;
    @FXML
    private JFXButton playlist_btn;
    @FXML
    private JFXButton musichistory_btn;
    @FXML
    private JFXButton like_btn;
    @FXML
    private ImageView imgBox;
    @FXML
    private Label memId;
    @FXML
    private JFXTextField memName;
    @FXML
    private JFXTextField memNick;
    @FXML
    private JFXTextField memEmail;
    @FXML
    private JFXTextField memPhone;
    @FXML
    private Button update_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		/**
//		 * MenuConnector:
//		 * myinfo에서 서브메뉴 6개에 대해 링크를 설정하는 클래스. 각 컨트롤러마다 아래 3줄을 추가하여 사용.
//		 */
//		MenuConnector menuCon = new MenuConnector();
//		menuCon.setPane(mainPane);
//		menuCon.setBtn(myinfo_btn, mylevel_btn, buyhistory_btn, musichistory_btn, like_btn, playlist_btn);
		
		setProfileImg();
		setText();
		
		myinfo_btn.setOnAction(e->{
			changeScene("My_info_change.fxml");
			// 자기 버튼 색 변경
			myinfo_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			mylevel_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			buyhistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			musichistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			playlist_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			like_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		mylevel_btn.setOnAction(e->{
			changeScene("MyInfo_grade.fxml");
			// 자기 버튼 색 변경
			mylevel_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			myinfo_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			buyhistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			musichistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			playlist_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			like_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		buyhistory_btn.setOnAction(e->{
			changeScene("MyInfo_ticketHistory.fxml");
			// 자기 버튼 색 변경
			buyhistory_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			myinfo_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			mylevel_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			musichistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			playlist_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			like_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		musichistory_btn.setOnAction(e->{
			changeScene("MyInfo_downHistory.fxml");
			// 자기 버튼 색 변경
			musichistory_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			myinfo_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			mylevel_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			buyhistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			playlist_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			like_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		playlist_btn.setOnAction(e->{
			changeScene("My_Playlist_Main.fxml");
			// 자기 버튼 색 변경
			playlist_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			myinfo_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			mylevel_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			buyhistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			musichistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			like_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		like_btn.setOnAction(e-> {
			changeScene("My_info_Like.fxml");
			// 자기 버튼 색 변경
			like_btn.setStyle("-fx-background-color: rgb(230,230,230)");
			// 다른버튼 기본으로 변경
			myinfo_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			mylevel_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			buyhistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			musichistory_btn.setStyle("-fx-border-color: rgb(140,0,140);");
			playlist_btn.setStyle("-fx-border-color: rgb(140,0,140);");
		});
		
		/**
		 * 수정하기 버튼 클릭 시 
		 */
		update_btn.setOnAction(e->{
			changeScene("MyInfo_update.fxml");
		});
	}

//	private void changeScene(String url) {
//		Parent upParent = null;
//		try {
//			upParent = FXMLLoader.load(getClass().getResource(url));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}			
//		mainPane.getChildren().setAll(upParent);
//	}
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		main_pane.setCenter(cneter_pane);
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

	private void setText() {
		
		MemberVO my = Session.getUser();
		
		memName.setText(my.getMem_name());
		memNick.setText(my.getMem_nick());
		memEmail.setText(my.getMem_email());
		memPhone.setText(my.getMem_tel());
		memId.setText(Session.getUser().getMem_id());
		
		memName.setDisable(true);
		memNick.setDisable(true);
		memEmail.setDisable(true);
		memPhone.setDisable(true);
	}

	private void setProfileImg() {
		File file = new File("img/profile-"+Session.getUser().getMem_id()+".jpg");
		Image img;
		if(file.exists()) {
			img = new Image(file.toURI().toString());
		} else {
			img = new Image(new File("img/profile.jpg").toURI().toString());
		}
		imgBox.setImage(img);
	}
}
