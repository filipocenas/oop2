package potapanjeBrodovaRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BrodoviProgram {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry registry = LocateRegistry.getRegistry("localhost", 2409);
		Server server = (Server) registry.lookup("Igra");
		Brodovi igra = server.novaIgra();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Odgovor odgovor;
		
		do {
			System.out.println("Unesite koordinate (x, y): ");
			String unos = in.readLine();
			String [] koordinate = unos.split(",");
			odgovor = igra.pogadjaj(Integer.parseInt(koordinate[0]), Integer.parseInt(koordinate[1]));
			
			
			switch(odgovor) {
			case POSTOJI:
				System.out.println(igra.stampajTablu());
				System.out.println("Zakacili ste brod.");
				break;
			case NEPOSTOJI:
				System.out.println(igra.stampajTablu());
				System.out.println("Promasili ste brod.");
				break;
			case BRAVO:
				System.out.println(igra.stampajTablu());
				System.out.println("Potopili ste sve brodove.");
				break;
			}
		} while (odgovor != Odgovor.BRAVO);
	}
}
