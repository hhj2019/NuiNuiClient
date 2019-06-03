package kr.or.	ddit.nn.view.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Manager_main extends Application{
	
	public static Stage pStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = FXMLLoader.load(getClass().getResource("Manager_MAIN.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("매니저 페이지");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pStage = primaryStage;
	}
	
	

}
