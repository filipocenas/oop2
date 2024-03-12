package teorijske4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPong {
	public static void main(String[] args) {
		
		PrintWriter out = null;
		BufferedReader in = null;
		Socket server = null;
		
		try {
			server = new Socket("localhost", 1234);
			System.out.println("Client is connected to the server");
			
			out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			
			String message = "ping";
			out.println(message);
			System.out.println("I sent " + message);
			String reply = in.readLine();
			System.out.println("Server replied: " + reply);
			System.out.println("Client out...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				server.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
