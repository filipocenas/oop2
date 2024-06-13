package igraPogadjanja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class KlijentProgram {
	public static void main(String[] args) throws IOException, NotBoundException, NumberFormatException {
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		Server server = (Server) registry.lookup("Igra");
		Igra igra = server.startIgra();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Odgovor odgovor;
		do {
			System.out.println("Pogodi broj: ");
			int broj = Integer.parseInt(in.readLine());
			odgovor = igra.pogadjaj(broj);
			switch (odgovor) {
			case VECI:
				System.out.println("Zamisljeni broj je veci.");
				break;
			case MANJI:
				System.out.println("Zamisljeni broj je manji.");
				break;
			case POGODAK:
				System.out.println("Pogodio si broj.");
				break;
			case KRAJ:
				System.out.println("Nema vise pokusaja...");
				break;
			}
		} while (odgovor != Odgovor.POGODAK && odgovor != Odgovor.KRAJ);
	}
}
