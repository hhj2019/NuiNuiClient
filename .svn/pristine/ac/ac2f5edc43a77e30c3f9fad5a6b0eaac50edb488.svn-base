package kr.or.ddit.nn.view.download;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import kr.or.ddit.nn.service.music.DownService;
import util.Server;

class DownTask extends Task<List<File>> {

	private DownService service;
	private String savePath;
	private List<String> sampleFileList;
	
	DownTask(List<String> list){
		sampleFileList = list;
	}

	public void setPath(String url) throws NotBoundException {
		
		try {
			service = (DownService) Server.reg.lookup("downService");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		savePath = url;
	}

	@Override
	protected List<File> call() throws Exception {

		List<File> sampleList = new ArrayList<>();

		int i = 0;
		for (String url : sampleFileList) {
			download(new File(url));
			sampleList.add(new File(url));
			i++;
			this.updateProgress(i, sampleFileList.size());
		}

		return sampleList;
	}

	private void download(File file) throws InterruptedException {
		try {
			service.downloadMp3(file, savePath);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		this.updateMessage("Copying: " + file.getName());
	}
}