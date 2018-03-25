package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	public Serveur () throws IOException{
		ServerSocket monSocketEcoute = new ServerSocket (5000);
		System.out.println("Serveur Lancé : ");
		while (true){
			Socket monSocket = monSocketEcoute.accept();
			System.out.println("Un client à établie la connexion ");
			ThreadServer threadServer = new ThreadServer(monSocket);
			threadServer.run();	
		}
		
	}
	public static void main(String[] args) {
		try {
			Serveur s = new Serveur();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
