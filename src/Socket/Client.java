//package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
	private BufferedReader lecture;
	private PrintWriter ecriture;
	private Socket sock;
	private Socket maSocket;

	public Client (){
		try {
			connexionServeur("127.0.0.1", 5000);
			Thread e = new Thread(new ThreadEnvoi());
			e.start();
			Thread r = new Thread(new ThreadRecoit());
			r.start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public class ThreadEnvoi implements Runnable{
	
		@Override
		public void run (){
			while (true){
			try {
				ecriture = new PrintWriter(maSocket.getOutputStream());
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez saisir un mot :");
				String str = sc.nextLine(); 
				ecriture.println(str);
				ecriture.flush();
			} catch (Exception e){
				e.printStackTrace();
			}

			}
		}
	}
	
	public class ThreadRecoit implements Runnable{
		@Override
		public void run (){
			while (true){
				try{
					InputStream monFlotBasNiveau = maSocket.getInputStream();
					InputStreamReader monFlotDeCaractere = new InputStreamReader(monFlotBasNiveau);
					lecture =  new BufferedReader(monFlotDeCaractere);
					String monMessage = lecture.readLine();
					System.out.println(monMessage);
				} catch (Exception e){
					e.printStackTrace();						
				}

			}
			

		}
	
	}
	private void connexionServeur(String adresseIPServeur, int portServer) throws UnknownHostException, IOException{
		this.maSocket = new Socket (adresseIPServeur, portServer);	
	}
	
	public static void main(String[] args) {
		Client c = new Client();
	}
}
