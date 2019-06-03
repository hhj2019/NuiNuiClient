package im.conversations.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import im.conversations.app.beans.Message;
import im.conversations.app.beans.User;
import im.conversations.app.factory.MsgListCell;
import im.conversations.app.factory.UserListCell;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

@FXMLController(value = "/resources/fxml/mainPanel.fxml" , title = "Menu Design  ")
public class ChatPanelController {
	
	/**
	 * 왓슨 처리부분
	 */
	static String iamApiKey = "6HUKHbj_94rCoYSRWTDf1XC_0hgj3E9gPAbmDpNkGn4w";
	static String iamUrl = "https://gateway-syd.watsonplatform.net/assistant/api";
	static String version = "2019-02-28";
	static String assistantId = "6055d361-3f36-402c-9b8e-cc853a0e5773";
	static String sessionId;
	static boolean printErrLog = true;
	
	private Assistant service = null;
	
	public ChatPanelController() {
		try {
			// Invoke a Watson Assistant method
			IamOptions imaOptions = new IamOptions.Builder().apiKey(iamApiKey).build();
			service = new Assistant(version, imaOptions);
			service.setEndPoint(iamUrl);

			/** Creating session start... **/
			CreateSessionOptions options = new CreateSessionOptions.Builder(assistantId).build();
			SessionResponse response = service.createSession(options).execute().getResult();
			JsonParser parser = new JsonParser();
			JsonObject jsonObj = (JsonObject) parser.parse(response.toString());
			sessionId = jsonObj.get("session_id").getAsString();
			System.out.println("session id: " + sessionId);
			/** Creating session End... **/
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (RequestTooLargeException e) {
			e.printStackTrace();
		} catch (ServiceResponseException e) {
			e.printStackTrace();
		}
		System.out.println("생성자 생성");
	}
	
	
	
	@FXML private JFXListView<User> sideList2;
	@FXML private JFXListView<Message> sideList3;
	@FXML private JFXTextField msgBox;
	@FXML private ImageView imgView11;
	
	static ObservableList<User> items =FXCollections.observableArrayList ();
	static ObservableList<Message> messages =FXCollections.observableArrayList ();
	
	/*@FXML
	private JFXTextField enterMsg;*/
	
	@PostConstruct
	public void init() throws FlowException, VetoException {
		sideList2.depthProperty().set(1);
		sideList3.depthProperty().set(1);
		/*enterMsg.setLabelFloat(true);
		enterMsg.setPromptText("Type Something");*/
		items.add(new User("승태","/resources/img/user11.jpg","12:30","# Music is My Life!","한가해요",true,false));
		items.add(new User("왓슨이","/resources/img/user22.jpg","11:11","# 음악  추천받고 싶으세요?","언제는 말씀하세요",false,true));
//		items.add(new User("User 3","/resources/img/dv2.jpg","10:11","# status of third User","away",true,true));
		
		sideList2.setItems(items);
		sideList2.setCellFactory((ListView<User> l) -> new UserListCell());
		
		sideList3.setItems(messages);
		sideList3.setCellFactory((ListView<Message> l) -> new MsgListCell());
		
		 Image image = new Image("/resources/img/smile2.png");
		 imgView11.setImage(image);
		 imgView11.setFitWidth(30);
		 imgView11.setFitHeight(30);
		 imgView11.setPreserveRatio(true);
		 imgView11.setSmooth(true);
		
		msgBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode() == KeyCode.ENTER)  {
		        	
		        	String msgText = msgBox.getText();
		        	
		        	if(msgText!=null && !"".equals(msgText))
		        	{
		        		
		        		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		        		Calendar cal = Calendar.getInstance();
		        		
		        		messages.add(new Message("승태","/resources/img/user11.png",dateFormat.format(cal.getTime()),msgText));
		        		msgBox.setText("");

		        		// 메시지 전송(Enter)후에 왓슨이의 메시지 받아오기
		        		send(msgText.trim());
		        		
		        	}
		        }
		    }
		});
		
		
	}
	
	///////////////////////////////////////////////////
	/**
	 * 메시지 전송하는 함수
	 * @param input
	 */
	private void send(String input) {
		System.out.println("여긴가?");
		String result = requestMessage(service, input); // 여기가 진행안됨.
		System.out.println("여긴가?2");
		JsonParser parser = new JsonParser();
		JsonObject objResult = (JsonObject) parser.parse(result);
		JsonObject objOutput = (JsonObject) objResult.get("output");
		JsonArray genericArr = (JsonArray) objOutput.get("generic");
		
		// 전송시간 저장하기 위한 부분
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();

		for (Object obj : genericArr) {
			JsonObject json = (JsonObject) obj;
//			System.out.println("From Watson: " + json.get("text").getAsString());
			// 메세지 전송할 떄 ObservableList에 저장 해야 하므로.
			messages.add(new Message("왓슨이", "/resources/img/user22.jpg", dateFormat.format(cal.getTime()), json.get("text").getAsString()));
		}
	}
	
	/**
	 * 입력받은 message를 전송(Request)
	 * @param service
	 * @param message
	 * @return
	 */
	static String requestMessage(Assistant service, String message) {
		MessageInput input = new MessageInput.Builder().messageType("text").text(message).build();
		MessageOptions options = new MessageOptions.Builder(assistantId, sessionId).input(input).build();
		MessageResponse response = service.message(options).execute().getResult();

		return response.toString();
	}	
}
