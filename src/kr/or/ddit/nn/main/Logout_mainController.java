package kr.or.ddit.nn.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Logout_mainController implements Initializable {

	@FXML
	private Pane logout_pane;
	@FXML
	private Circle login_img;
	@FXML
	private Label session_name;

	public Label getSession_name() {
		return session_name;
	}

	public void setSession_name(Label session_name) {
		this.session_name = session_name;
	}

	@FXML
	private JFXButton mypage_btn;
	@FXML
	private JFXButton logout_btn;
	@FXML
	private ImageView main_logo;
	@FXML
	private ImageView main_title;
	@FXML
	private JFXTextField serch_textfiled;
	@FXML
	private JFXButton serch_btn;
	
	public void changeScene(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMain.pStage.getScene().setRoot(parent);
	}

	/**
	 * 센터화면 변경
	 */
	public void changeMain(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMain.pStage.getScene().setRoot(parent);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		logout_btn.setOnAction(e -> {
			Session.setUser(null);
			changeScene("Main.fxml");
		});

		File file = new File("img/profile-" + Session.getUser().getMem_id() + ".jpg");
		Image img;
		if (file.exists()) {
			img = new Image(file.toURI().toString());
		} else {
			img = new Image(new File("img/profile.jpg").toURI().toString());
		}
		login_img.setFill(new ImagePattern(img));

		/////////////
		// Thread thread = new Thread() {
		// @Override
		// public void run() {
		// Platform.runLater(() -> {
		// session_name.setText(Session.getUser().getMem_name());
		// });
		// try {
		// Thread.sleep(1000);
		// } catch(InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// };
		////////////////

		mypage_btn.setOnAction(e -> {
			changeCenter();
		});

		serch_btn.setOnAction(e -> {
			Serch_Controller.value = serch_textfiled.getText();
			changeCenters();
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
	}

	/**
	 * 내정보 관리창으로 변경
	 */
	private void changeCenter() {
		Parent infoRoot = null;
		try {
			infoRoot = FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/view/myInfo/MyInfo_Main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BorderPane mainRoot = (BorderPane) ClientMain.pStage.getScene().getRoot();
		mainRoot.setCenter(infoRoot);
	}

	/**
	 * 검색창으로 변경
	 */
	private void changeCenters() {
		Parent infoRoot = null;
		try {
			infoRoot = FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/main/serch.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BorderPane mainRoot = (BorderPane) ClientMain.pStage.getScene().getRoot();
		mainRoot.setCenter(infoRoot);
	}
}
