package kr.or.ddit.nn.view.manager.qna;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.nn.service.answer.AnswerService;
import kr.or.ddit.nn.service.question.QuestionService;
import kr.or.ddit.nn.vo.qna.QnAVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;

public class Qan_main_controller implements Initializable{
	private int question_id;

	@FXML
	private BorderPane Main_pane;
	@FXML
	private Pane in_pane;
	@FXML
	private Pagination qna_pagination;
	@FXML
	private TableView<QnAVO> qna_table;
	@FXML
	private TableColumn<QnAVO, String> q_info;
	@FXML
	private TableColumn<QnAVO, String> qna_title;
	@FXML
	private TableColumn<QnAVO, String> qna_date;
	
	private Registry reg;
	private QuestionService question;
	private AnswerService answer;
	
	private int from, to, itemsForPage;
	private ObservableList<QnAVO> qna_List;
	private ObservableList<QnAVO> currentPageDate;
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Main_pane.setCenter(in_pane);
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
		q_info.setStyle("-fx-alignment: CENTER;");
		qna_date.setStyle("-fx-alignment: CENTER;");
		
		//질문, 답변 리스트에 넣어주기
		List<QnAVO> q_List = new ArrayList<>();
		List<QnAVO> a_List = new ArrayList<>();
		
		//질문 답변 정리해서 List에 넣기
		qna_List = FXCollections.observableArrayList();

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			question = (QuestionService) reg.lookup("questionService");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			answer = (AnswerService)reg.lookup("answerService");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		/**
		 * 관리자 질문 전체 조회
		 */
		try {
			q_List.addAll(question.SelectMQuestionList());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		int j = 0;
		
		for(int i = 0; i < q_List.size(); i++) {
			q_info.setCellValueFactory(new PropertyValueFactory<>("q_info"));
			qna_title.setCellValueFactory(new PropertyValueFactory<>("question_title"));
			qna_date.setCellValueFactory(new PropertyValueFactory<>("question_date"));
			
			qna_List.add(q_List.get(i));
			question_id = q_List.get(i).getQuestion_id();
			try {
				if(answer.SelectMAnswerList(question_id).isEmpty()){
					continue;
				}else {					
					a_List.addAll(answer.SelectMAnswerList(question_id));
					qna_List.add(a_List.get(j++));
				}
			}catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		qna_table.getItems().addAll(qna_List);
		
		/**
		 * 페이지네이션
		 */
		itemsForPage = 10;
		int totItemCnt = qna_List.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		qna_pagination.setPageCount(totPageCnt);
		qna_pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer param) {
				from = param * itemsForPage;
				to = from + itemsForPage - 1;
				qna_table.setItems(getTableViewDate(from, to));

				return qna_table;
			}
		});
		
		/**
		 * 상세보기
		 */
		qna_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					if (qna_table.getSelectionModel().isEmpty()) {
						return;
					} else {
						qna_detail_controller.question_id = qna_table.getSelectionModel().getSelectedItem().getQuestion_id();
						changeScene("Manager_qna_detil.fxml");
					}
				}
			}
		});
	}
	
	/**
	 * TableView에 채워줄 데이터를 가져오는 메서드
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<QnAVO> getTableViewDate(int from, int to) {
		currentPageDate = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int toSize = qna_List.size();
		for (int i = from; i <= to && i < toSize; i++) {
			currentPageDate.add(qna_List.get(i));
		}
		return currentPageDate;
	}
}
