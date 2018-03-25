package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private BufferedReader lecture;
	private PrintWriter ecriture;
	private Socket sock;
	
	public Client (){
		try {
			connexionServeur("127.0.0.1", 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void connexionServeur(String adresseIPServeur, int portServer) throws UnknownHostException, IOException{
		Socket maSocket = new Socket (adresseIPServeur, portServer);
		
		InputStream monFlotBasNiveau = maSocket.getInputStream();
		InputStreamReader monFlotDeCaractere = new InputStreamReader(monFlotBasNiveau);
		this.lecture =  new BufferedReader(monFlotDeCaractere);
		String monMessage = this.lecture.readLine();
		System.out.println(monMessage);
		this.lecture.close();
		
		
		this.ecriture = new PrintWriter(maSocket.getOutputStream());
		
		this.ecriture.println("Burn Brain");
		this.ecriture.flush();
		this.ecriture.close();
		
	}
	
	public static void main(String[] args) {
		Client c = new Client();
	}
}
