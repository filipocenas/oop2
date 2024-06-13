package igraPogadjanjaRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class KlijentProgram {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1312);
			Server server = (Server) registry.lookup("Igra");
			Igra igra = server.novaIgra();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(igra.otkriveno());
			Odgovor odgovor;
			do {
				System.out.println("Unesi slovo: ");
				char slovo = in.readLine().charAt(0);
				odgovor = igra.pogadjaj(slovo);
				System.out.println(odgovor.otkriveno + ", zivoti: " + odgovor.zivoti);
			} while (!odgovor.pogodak && odgovor.zivoti > 0);
			if (odgovor.pogodak)
				System.out.println("Pogodio si rec!");
			else
				System.out.println("Nisi pogodio rec!");
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
