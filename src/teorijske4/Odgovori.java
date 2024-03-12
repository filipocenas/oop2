package teorijske4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Odgovori extends Thread {
	
	private BufferedReader in;
	private PrintWriter out;
	
	public Odgovori(Socket klijent) throws IOException {
		in = new BufferedReader(new InputStreamReader(klijent.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(klijent.getOutputStream()), true);
	}
		
	@Override
	public void run() {
		String poruka = "";
		do {
			try {
				poruka = in.readLine();
				System.out.println(poruka);
				if(poruka.equalsIgnoreCase("ping")) {
					out.println("Pong");
				} else if(poruka.equalsIgnoreCase("bye")) {
					out.println("bye");
				} else {
					out.println("I don't understand!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(poruka != null && !poruka.equalsIgnoreCase("bye"));
	}
}


