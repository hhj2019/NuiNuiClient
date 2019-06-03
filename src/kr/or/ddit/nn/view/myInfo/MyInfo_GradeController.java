package kr.or.ddit.nn.view.myInfo;

import java.io.File;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.main.Session;
import kr.or.ddit.nn.service.grade.GradeService;
import kr.or.ddit.nn.service.member.MyInfoService;
import kr.or.ddit.nn.vo.grade.GradeVO;
import kr.or.ddit.nn.vo.member.MemberVO;
import util.Server;

public class MyInfo_GradeController implements Initializable {

	private HashMap<Integer, String> iconList = new HashMap<>();

	@FXML
	private BorderPane main_pane;
	@FXML
	private Pane cneter_pane;
	@FXML
	private ImageView imgBox;
	@FXML
	private Label memGrade;

	private MyInfoService myInfo;
	private GradeService gradeInfo;
	private MemberVO my = Session.getUser();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		iconList.put(0, "img/level/nui.png");
		iconList.put(1, "img/level/green.png");
		iconList.put(3, "img/level/gold.png");
		iconList.put(5, "img/level/vip.png");

		try {
			myInfo = (MyInfoService) Server.reg.lookup("myInfo");
			gradeInfo = (GradeService) Server.reg.lookup("gradeInfo");
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
//		try {
//			myInfo.updateMyInfo(my);
//		} catch (RemoteException e2) {
//			e2.printStackTrace();
//		}

		if(my.getGrade_id() > 0)
			System.out.println("level");
		else
			System.out.println("none");
		setMyGrade();

	}

	private void setMyGrade() {
		int level = my.getGrade_id();

		GradeVO gInfo = null;
		try {
			gInfo = gradeInfo.selectGrade(level);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		String myImg = iconList.get(gInfo.getGrade_value());
		imgBox.setImage(new Image(new File(myImg).toURI().toString()));

		memGrade.setText(gInfo.getGrade_name());
	}

}