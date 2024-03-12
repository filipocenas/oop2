package teorijske4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*Server treba da primi poruku od klijenta.
 * Ako je ta poruka "ping", server vraca "pong", a 
 * u suprotnom se server gasi
 */
public class ServerPing {
	public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(1234);
			System.out.println("Server up...");
			Socket client = ss.accept();
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
			
			String message = in.readLine();
			if(message.equalsIgnoreCase("ping")) {
				out.println("Pong");
			} else {
				out.println("Slab sam u to!");
			}
			
			System.out.println("Client sent " + message);
			System.out.println("Server down...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				ss.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
