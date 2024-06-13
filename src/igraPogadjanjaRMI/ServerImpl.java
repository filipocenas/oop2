package igraPogadjanjaRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {

	protected ServerImpl() throws RemoteException {
		super();
	}

	@Override
	public Igra novaIgra() throws RemoteException {
		Igra igra = new IgraImpl();

		System.out.println("Zapoceta je nova igra: " + igra);
		return igra;
	}

}
