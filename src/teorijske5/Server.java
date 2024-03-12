package teorijske5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	private MainFrame frame;
	private ServerSocket server;
	
	public Server(MainFrame frame, int port) throws IOException {
		try {
			this.frame = frame;
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not listen on port " + port + ": " + e.getMessage());
			throw e;
		}
		
	}
	
	@Override
	public void run() {
		
		BufferedReader in = null;
		PrintWriter out = null;
		Socket client = null;
		
	}
	
	
	public static void main(String[] args) {
		
	}
	
}
