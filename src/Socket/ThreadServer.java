package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ThreadServer implements Runnable {

	
	private Socket client; 
	
	public ThreadServer(Socket client){
		this.client = client;
	}
	@Override
	public void run() {
		BufferedReader in;
		PrintStream out;
		try {
			in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			out = new PrintStream(this.client.getOutputStream());
			boolean fini = false;
			while (!fini){
				System.out.println(fini);
				String theLine = in.readLine();
				if (theLine.equals(".")){
					fini = true;
				}
				out.println(theLine);
				System.out.println("Fin du thread");
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
