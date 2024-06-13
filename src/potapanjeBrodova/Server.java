package potapanjeBrodova;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		int port = 8080;
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Prihvatanje konekcija na port: " + port);
			Socket client = null;
//			BufferedReader in = null;
//			PrintWriter out = null;
			
			try {
				while(!Thread.interrupted()) {
					client = server.accept();
					Brodovi b = new Brodovi(client);
					b.start();
				}
				
				System.out.println("Prihvacena konekcija od " + client.getRemoteSocketAddress());
			} catch (IOException e) {
				System.err.println("Nije moguce uspostavitti konekciju " + e.getMessage());
//				return;
			}
			
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					
				}
				server = null;
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Nije moguce konektovati se na port " + port + ": " + e.getMessage());
		}
	}
}
