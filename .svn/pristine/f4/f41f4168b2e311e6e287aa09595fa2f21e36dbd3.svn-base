package kr.or.ddit.nn.view.CustomerService.faq;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.nn.service.faq.FAQService;
import kr.or.ddit.nn.vo.faq.FAQVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class CS_faq_detail_controller implements Initializable {

	public static int faq_id;

	private Registry reg;
	private FAQService faq;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane center_pane;
	@FXML
	private JFXButton faq_ok_btn;
	@FXML
	private JFXTextField faq_title;
	@FXML
	private JFXTextField faq_date;
	@FXML
	private JFXTextArea Q_content;
	@FXML
	private JFXTextArea A_content;

	FAQVO faq_vo;

	/**
	 * 메인 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(null);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			faq = (FAQService) reg.lookup("faqService");
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}

		faq_title.setEditable(false);
		faq_date.setEditable(false);
		Q_content.setEditable(false);
		A_content.setEditable(false);

		/**
		 * 자주묻는질문 상세조회
		 */
		try {
			faq_vo = faq.selectFAQdetail(faq_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		faq_title.setText(faq_vo.getFaq_title());
		faq_date.setText(faq_vo.getFaq_date());
		Q_content.setText(faq_vo.getQ_content());
		A_content.setText(faq_vo.getA_content());

		/**
		 * 확인버튼 클릭 시
		 */
		faq_ok_btn.setOnAction(e -> {
			changeScene("CS_faq_main.fxml");
		});
	}

}
