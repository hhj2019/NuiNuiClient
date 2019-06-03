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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.vo.member.MemberVO;

public class Myinfo_chage_controller implements Initializable{

	@FXML 
	private BorderPane main_pane;
	@FXML 
	private Pane cneter_pane;
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
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setProfileImg();
		setText();		
		
		/**
		 * 수정하기 버튼 클릭 시 
		 */
		update_btn.setOnAction(e->{
			changeScene("MyInfo_update.fxml");
		});
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
