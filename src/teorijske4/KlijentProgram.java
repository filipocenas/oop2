package teorijske4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class KlijentProgram {
	public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		Socket server = null;
		
		BufferedReader term = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			server  = new Socket("localhost", 1234);
			System.out.println("Client connected...");
			
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
			
			String message = "";
			
			do {
				message = term.readLine();
				out.println(message);
				System.out.println("I sent: " + message);
				String reply = in.readLine();
				System.out.println("Server replied: " + reply);
			} while(message != null && !message.equalsIgnoreCase("bye"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				term.close();
				server.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
