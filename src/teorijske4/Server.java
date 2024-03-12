package teorijske4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/*
 * pravimo igricu pogadjanja broja.
 * Server "zamisli" neki broj dok ga klijent pogadja
 * Server vraca neku povratnu info u zavisnosti od toga sta je klijent uneo
 * < - server je zamislio manji broj od unetog
 * > - server je zamislio veci broj od unetog
 * = - server je zamislio taj broj
 * ! - greska pri unosu odnosno klijent je uneo nedozvoljeni karakter
 */
public class Server {
	
	public static void main(String [] args) {
		
		try {
			ServerSocket ss = new ServerSocket(8080);
			RangLista rl = new RangLista();
			System.out.println("Server pokrenut.");
			
			/*
			 * thread.interrupted se odnosi na glavnu nit koju ima server.
			 * Sve dok nesto ne prekine server(sve dok server radi),
			 * prihvataju se klijenti
			 */
			try {
				
				while(!Thread.interrupted()) { 
					Socket klijent = ss.accept();
					System.out.println("Konekcija je uspostavljena");
					Igra igra = new Igra(klijent, rl);
					igra.start();
					System.out.println("Igra je pocela!");
				}
			} finally {
				ss.close(); //zatvaranje servera u slucaju da se nesto lose desi tokom izvrsavanja
			}
			
			//<, >, =, !
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
