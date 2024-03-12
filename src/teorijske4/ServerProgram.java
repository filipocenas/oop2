package teorijske4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
	public static void main(String[] args) throws IOException {
		try(ServerSocket ss = new ServerSocket(1234)) {
			System.out.println("Server up...");
			while(!Thread.interrupted()) {
				Socket klijent = ss.accept();
				Odgovori odg = new Odgovori(klijent);
				odg.start();
			}
		}
	}
}
