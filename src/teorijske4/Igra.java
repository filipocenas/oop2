package teorijske4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Igra extends Thread {

	private final Socket klijent;
	private final BufferedReader in;
	private final PrintWriter out;
	private final int broj;
	private String nadimak;
	private final RangLista rl;
	private int brPokusaja;
	
	public Igra(Socket klijent, RangLista rl) throws IOException {
		this.klijent = klijent;
		this.in = new BufferedReader(new InputStreamReader(klijent.getInputStream()));
		this.out = new PrintWriter(new OutputStreamWriter(klijent.getOutputStream()), true);
		this.broj = (int) (1 + Math.random() * 100.0);
		this.rl = rl;
		this.brPokusaja = 0;
	}
	
	public int getBrPokusaja() { return brPokusaja; }
	
	@Override
	public void run() {
		try {
			nadimak = in.readLine();
			System.out.println("Pocela je igra sa korisnikom: " + nadimak);
			
			String linija = in.readLine();
			while(linija != null) {
				try {
					int pokusaj = Integer.parseInt(linija);
					brPokusaja++;
					
					if(pokusaj == broj) {
						out.println("=");
						int rang;
						synchronized (rl){ //kriticna oblast 
							rang = rl.dodaj(this);
						}
						out.println(rang);
					} else if(pokusaj < broj) {
						out.println(">");
					} else {
						out.println("<");
					}
				} catch (NumberFormatException e) {
					out.println("! " + e.getMessage());
				}
				linija = in.readLine();
			}
			klijent.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
