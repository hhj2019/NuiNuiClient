package kr.or.ddit.nn.main;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.or.ddit.nn.vo.member.MemberVO;
import util.Server;

public class ClientMain extends Application {
	
	public static Stage pStage;
	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		//// Registry reg = LocateRegistry.getRegistry("192.168.203.2", 8888);
		// Registry reg = null;
		// try {
		// reg = LocateRegistry.getRegistry("localhost", 8888);
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// RemoteInterface client = (RemoteInterface) reg.lookup("server");
		//
		// client.printMsg("hello");
		//
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/**
		 * server registry 등록
		 * 사용: Server.reg
		 */
		Server server = new Server();
		
		/************ START: Session test ************/

		MemberVO member = new MemberVO();

		/************ END: Session test ************/
		
		
		Pane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//		Pane root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/nn/view/join/member_join_form.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("늬늬뮤직");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pStage = primaryStage;
		
		

	}

}
