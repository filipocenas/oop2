package igraPogadjanjaRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerProgram {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1312);
			Server server = new ServerImpl();
			registry.rebind("Igra", server);
			System.err.println("Server je pokrenut...");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
