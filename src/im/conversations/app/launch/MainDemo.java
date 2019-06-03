package im.conversations.app.launch;

import com.jfoenix.controls.JFXDecorator;

import im.conversations.app.controller.MainController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDemo extends Application {

	@FXMLViewFlowContext private ViewFlowContext flowContext;
	
	/**
	 * MainController에서 호출하기 위해서 main함수 주석.
	 * 실제 호출되는 부분은 MainController임
	 */
//	public static void main(String[] args) {
//		launch(args);
//	}
	
	/**
	 * MainController에서 myStage를 사용하기 위해서 public static 선언
	 */
	public static Stage myStage = new Stage();

	public void start(Stage stage) throws Exception {

		new Thread(()->{
			try {
				//SVGGlyphLoader.loadGlyphsFont(MainDemo.class.getResourceAsStream("/resources/fonts/icomoon.svg"),"icomoon.svg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}).start();

		Flow flow = new Flow(MainController.class);
		DefaultFlowContainer container = new DefaultFlowContainer();
		flowContext = new ViewFlowContext();
		flowContext.register("Stage", stage);
		flow.createHandler(flowContext).start(container);
		
		JFXDecorator decorator = new JFXDecorator(stage, container.getView());
		decorator.setCustomMaximize(true);
		Scene scene = new Scene(decorator, 1200, 800);
		//scene.getStylesheets().add(MainDemo.class.getResource("/resources/css/jfoenix-fonts.css").toExternalForm());
		//scene.getStylesheets().add(MainDemo.class.getResource("/resources/css/jfoenix-design.css").toExternalForm());
		scene.getStylesheets().add(MainDemo.class.getResource("/resources/css/main.css").toExternalForm());
		//		stage.initStyle(StageStyle.UNDECORATED);
		//		stage.setFullScreen(true);
		stage.setMinWidth(1000);
		stage.setMinHeight(800);
		stage.setScene(scene);
		stage.show();
		
		/**
		 * start시 지정되는 primaryStage를 static로 저장한 뒤 MainDemo에서 MainController 호출
		 */
		myStage = stage; // 테스트
	}

}