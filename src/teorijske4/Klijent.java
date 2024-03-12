package teorijske4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent {
	
	public static void main(String[] args) {
		try {
			Socket ss = new Socket("localhost", 8080);
			System.out.println("Uspostavili ste konekciju sa serverom");
			
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(ss.getOutputStream()), true);
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("Kako se zoves? ");
				String nadimak = stdIn.readLine();
				out.println(nadimak);
				
				boolean kraj = false;
				
				while(!kraj) {
					System.out.println("Unesi broj: ");
					String pokusaj = stdIn.readLine();
					out.println(pokusaj);
					
					String odgovor = in.readLine(); //ovde ucitavamo sa in.readLine jer ocekujemo odgovor sa Servera
					System.out.println(odgovor);
					if(odgovor  == null || "=".equals(odgovor)) {
						kraj = true;
					}
				}
			} finally {
				ss.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
