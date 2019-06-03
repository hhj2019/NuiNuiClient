package util;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static Registry reg;
	private int portNum = 8888;
	private String url = "localhost";

	public Server() {
		if (reg == null) {
			try {
				reg = LocateRegistry.getRegistry(url, portNum);
			} catch (RemoteException e) {
				System.out.println("error: Server class");
				e.printStackTrace();
			}
		}
	}
	
}
