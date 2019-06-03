package kr.or.ddit.nn.view.manager.music.album;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import kr.or.ddit.nn.vo.album.albumVO;
import kr.or.ddit.nn.vo.artist.artistVO;

public class update_album_controller  implements Initializable{

	
	@FXML BorderPane main_pane;
	@FXML Pane center_pane;
	
	@FXML JFXTextField album_name;
	@FXML JFXTextField artist_name;
	@FXML JFXTextField album_date;
	@FXML JFXTextField album_img;
	
	@FXML JFXButton insert_img_btn;
	@FXML JFXButton insert_btn;
	@FXML JFXButton cencel_btn;
	@FXML JFXButton insert_file;
	
	private Registry reg;
	private albumService as;
	private albumVO av;
	private artistService artistSer;
	
	static public String name;
	static public String artist;
	static public String date;
	static public String img;
	static public int id;
	private BufferedOutputStream bufferOut = null;
	private FileInputStream imgIn = null;
	private FileOutputStream imgOut = null;
	private BufferedInputStream bufferIn = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			as = (albumService) reg.lookup("albumService");
			artistSer = (artistService) reg.lookup("artistService");
		}catch (RemoteException e2) {
			e2.printStackTrace();
		}catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		album_date.setText(date);
		album_img.setText(img);
		album_name.setText(name);
		artist_name.setText(artist);
		
		insert_btn.setOnAction(e->{
			artistVO artist = new artistVO();
			try {
				artist = artistSer.selectArtistForName(artist_name.getText());
				av = new albumVO();
				av.setAlbum_id(id);
				av.setAlbum_date(album_date.getText());
				av.setAlbum_img(album_img.getText());
				av.setAlbum_name(album_name.getText());
				av.setArtist_id(artist.getArtist_id());
				
				if(as.updateAlbum(av)>0) {
					OKMsg("수정완료", album_name.getText() + "를(을) 수정했습니다.");
					changeScene("Manager_album.fxml");
				}else {
					errMsg("수정 실패", "앨범 수정을 실패했습니다.\n아티스트가 있는지 확인 해주세요.");
				}
				imgIn.close();
				imgOut.close();
				bufferIn.close();
				bufferOut.close();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		cencel_btn.setOnAction(e->{
			changeScene("Manager_album.fxml");
		});
		
		insert_img_btn.setOnAction(e->{
			Stage dialog = new Stage(StageStyle.UTILITY);
		      
		    FileChooser fileChooser = new FileChooser();
		         
		    //확장별로 파일 구분하는 필터 등록하기
		    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("image files", "*.jpg", "*.jpeg","*.png"));
		      
		    //Dialog창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고
		    //'취소'버튼을 누르면 null이 반환된다.
		    File selectFile = fileChooser.showOpenDialog(dialog);
		    if(selectFile!=null) {
		    	album_img.setText(selectFile.getName());
		    }
//		    File file = new File("img\\" + selectFile.getName());
		    try {
				imgIn = new FileInputStream(selectFile.getPath());
				imgOut = new FileOutputStream("img\\albumArt\\" + selectFile.getName());
				int write = 0;
				bufferOut = new BufferedOutputStream(imgOut, 5);
				bufferIn = new BufferedInputStream(imgIn, 5);
				while((write=bufferIn.read()) != -1) {
					bufferOut.write(write);
				}
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		insert_file.setOnAction(e->{
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
					artist_name.setText(metadata.get("Author"));
					album_date.setText(metadata.get("xmpDM:releaseDate"));
					album_name.setText(metadata.get("xmpDM:album"));
					
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
