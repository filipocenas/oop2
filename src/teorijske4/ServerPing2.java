package teorijske4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPing2 {
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
			
			String message = "";
			
			do {
				message = in.readLine();
				System.out.println("Client sent: " + message);
				if(message.equalsIgnoreCase("ping")) {
					out.println("pong");
				} else if(message.equalsIgnoreCase("bye")) {
					out.println("Bye!");
				} else {
					out.println("I don't understand...");
				}
			} while (message != null && !message.equalsIgnoreCase("bye"));
			
			System.out.println("Server down...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				ss.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
