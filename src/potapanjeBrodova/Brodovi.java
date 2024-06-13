package potapanjeBrodova;
/*
 * Korišćenjem Socket-a implementirati igru potapanja brodova. 
 * Server na tabli 10x10 sakriva brodove koji su oblika xxxx, xxx i xx 
 * (fiksiranih demenzija i orijentacije). Server klijentu šalje tablu koja 
 * je na početku ispunjena tačkama. Klijent unosi koordinate u obliku x, y 
 * čime bira polje koje gađa na tabli. Ukoliko se na polju koje klijent gađa 
 * nalazi deo broda server menja tačkicu na tom polju simbolom X, a ako na tom 
 * polju nije bio deo broda server tačkicu zamenjuje sa O. Nakon svakog klijentovog 
 * pokušaja server odgovara novim statusom table. Igra se završava kada klijent 
 * pogodi svih 9 polja koja zauzimaju brodovi, a jedan server može da opsluži više 
 * klijenata.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Brodovi extends Thread {
	private boolean [][] dodeljenaPolja;
	private char [][] iscrtanaPolja;
	private int pogodak;
	private BufferedReader in;
	private PrintWriter out;
	private Socket s;
	
	public Brodovi(Socket socket) throws IOException {
		this.s = socket;
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
		
		dodeljenaPolja = new boolean [10][10];
		
		// prvi brod oblika xxxx
		dodeljenaPolja[0][0] = true;
		dodeljenaPolja[0][1] = true;
		dodeljenaPolja[0][2] = true;
		dodeljenaPolja[0][3] = true;
		
		// drugi brod oblika xxx
		dodeljenaPolja[5][5] = true;
		dodeljenaPolja[5][6] = true;
		dodeljenaPolja[5][7] = true;
		
		// treci brod oblika xx
		dodeljenaPolja[8][4] = true;
		dodeljenaPolja[8][5] = true;
		
		
		iscrtanaPolja = new char [10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				iscrtanaPolja [i][j] = '.';
			}
		}
	}
	
	private void iscrtaj() {
		for (int i = 0; i < 10; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 10; j++) {
				sb.append(iscrtanaPolja[i][j]);
			}
			out.println(sb.toString());
		}
		out.println(pogodak);
	}
	
	@Override
	public void run() {
		try {
			boolean kraj = false;
			while(!kraj) {
				String koordinate = in.readLine();
				String [] xy = koordinate.split(",");
				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);
				
				if(dodeljenaPolja[x][y] == true) {
					pogodak++;
					iscrtanaPolja[x][y] = 'X';
				} else {
					iscrtanaPolja[x][y] = 'O';
				}
				iscrtaj();
				if(pogodak == 9) {
					kraj = true;
				}
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
