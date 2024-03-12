package prakticneMreznoProg;
/*
 * Igra vešanja se igra tako što server pri uspostavi konekcije
 * izabere neku reč i pošalje klijentu string iste dužine kao i reč,
 * ali koji se sastoji smo od znakova '-'. 
 * Pored toga, server šalje i broj pokušaja za pogađanje (života). 
 * Posle ovoga, server prihvata od klijenta jedno slovo i šalje novi 
 * string i preostali broj pokušaja. 
 * Ako reč sadrži dato slovo, server otkriva sve pozicije tog slova u reči 
 * (na odgovarajućim mestima se nalazi to slovo umesto prvobitnog znaka '-'),
 * u suprotnom se umanjuje broj života. 
 * Igra se ponavlja ili dok igrač ne pogodi reč, 
 * u kom slučaju igrač pobeđuje, ili dok ne izgubi poslednji život, 
 * u kom slučaju gubi igru. 
 * Po završetku igre, konekcija sa tim klijentom se zatvara.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Igra extends Thread {
	
	//standardna podesavanja za novu igru
	private static final int ZIVOTI = 10;
	private static final String [] RECI	 = {
			"avion",
			"vetar",
			"jabuka",
			"kupus",
			"lisica",
			"olovka",
			"papir",
	};
	
	//komunikacija sa igracem
	private final Socket socket;
	private final BufferedReader in;
	private final PrintWriter out;
	
	//stanje igre
	private final String rec;
	private final StringBuilder otkriveno;
	private int zivoti;
	
	public Igra(Socket socket) throws IOException {
		this(socket, RECI[(int) (RECI.length * Math.random())], ZIVOTI);
	}
	
	//nova igra sa zeljenim podesavanjima
	public Igra(Socket socket, String rec, int zivoti) throws IOException {
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
		//pocetno stanje igre
		this.rec = rec.toUpperCase();
		this.otkriveno = new StringBuilder(rec.length());
		for(int i = 0; i < rec.length(); i++) {
			this.otkriveno.append('-');
		}
		this.zivoti = zivoti;
	}
	
	
	
	
	
	
	
}
