package kr.or.ddit.nn.view.manager.music.music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.nn.service.album.albumService;
import kr.or.ddit.nn.service.artist.artistService;
import kr.or.ddit.nn.service.music.musicService;
import kr.or.ddit.nn.vo.album.albumVO;
import kr.or.ddit.nn.vo.artist.artistVO;
import kr.or.ddit.nn.vo.music.musicVO;

public class update_music_controller implements Initializable{
	
	
	@FXML private BorderPane main_pane;
	@FXML private Pane center_pane;
	@FXML private JFXTextField music_title;
	@FXML private JFXTextArea lyrics_field;
	@FXML private JFXButton insert_btn;
	@FXML private JFXButton cencel_btn;
	@FXML private JFXTextField artist_name;
	@FXML private JFXTextField genre_name;
	@FXML private JFXButton music_search_btn;
	@FXML private JFXButton artist_search_btn;
	@FXML private JFXButton album_search_btn;
	@FXML private JFXButton genre_search_btn;
	@FXML private JFXTextField album_name;
	
	private Registry reg;
	private musicService musicService;
	private musicVO mv;
	private artistVO av;
	private albumVO albumv;
	private artistService artistService;
	private albumService albumService;
	private Timestamp music_playtime;
	
	static public String music_name;
	static public String music_artist;
	static public String music_album;
	static public String music_lyrics;
	static public String music_genre;	
	static public int music_id;	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			musicService = (musicService) reg.lookup("musicService");
			artistService = (artistService) reg.lookup("artistService");
			albumService = (albumService) reg.lookup("albumService");
		}catch (RemoteException e2) {
			e2.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		albumv = new albumVO();
		av = new artistVO(); 
		mv = new musicVO();
		
		music_title.setText(music_name);
		artist_name.setText(music_artist);
		album_name.setText(music_album);
		genre_name.setText(music_genre);
		lyrics_field.setText(music_lyrics);
		
		
		music_search_btn.setOnAction(e->{
			Stage dialog = new Stage(StageStyle.UTILITY);
		      
		    FileChooser fileChooser = new FileChooser();
		    
		    //확장별로 파일 구분하는 필터 등록하기
		    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio files", "*.wav", "*.mp3"));
		      
		    //Dialog창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고
		    //'취소'버튼을 누르면 null이 반환된다.
		    File selectFile = fileChooser.showOpenDialog(dialog);
		    if(selectFile!=null) {
		    	//이 영역에서 파일내용을 읽어오는 작업을 수행한다.
				try {
					InputStream input = new FileInputStream(selectFile);
					ContentHandler handler = new DefaultHandler();
					Metadata metadata = new Metadata();
					Parser parser = new Mp3Parser();
					ParseContext parseCtx = new ParseContext();
					parser.parse(input, handler, metadata, parseCtx);
					input.close();
					
					String[] metadataNames = metadata.names();
					
					for(String name : metadataNames){
						System.out.println(name + ": " + metadata.get(name));
					}
					
					float time = Float.parseFloat(metadata.get("xmpDM:duration"));
					long playtime = (long) time;
					music_playtime = new Timestamp(playtime);
					 
					music_title.setText(metadata.get("dc:title")); 
					artist_name.setText(metadata.get("Author"));
					genre_name.setText(metadata.get("xmpDM:genre"));
					album_name.setText(metadata.get("xmpDM:album"));
					mv.setMusic_playtime(music_playtime);
					//mv.setMusic_file();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				} catch (TikaException e1) {
					e1.printStackTrace();
				}
		    	
		    }
		      
		});
		
		insert_btn.setOnAction(e->{
			
			try {
				
				mv.setMusic_id(music_id);
				albumv.setAlbum_name(album_name.getText());
				av.setArtist_name(artist_name.getText());
				mv.setMusic_name(music_title.getText());
				mv.setMusic_lyrics(lyrics_field.getText());
				mv.setMusic_genre(genre_name.getText());
				mv.setArtist_id(artistService.selectArtist(av).getArtist_id());
				mv.setAlbum_id(albumService.selectAlbum(albumv).getAlbum_id());
				int cnt = musicService.updateMusic(mv);
				if(cnt > 0) {
					OKMsg("수정성공", music_name+"를 수정했습니다.");
					changeScene("Manager_music.fxml");
				}else {
					errMsg("수정 실패", music_name + "의 수정을 실패했습니다.");
				}
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
		});
		cencel_btn.setOnAction(e->{
			changeScene("Manager_music.fxml");
		});
		
	}
	
	/**
	 * 센터 화면 변경
	 * 
	 * @param fxmlURL
	 * @return
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		main_pane.setCenter(center_pane);
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
	
	/**
	 * 에러메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	/**
	 * 완료메세지 출력
	 * 
	 * @param headerText
	 * @param msg
	 */
	public void OKMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("성공");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
}
