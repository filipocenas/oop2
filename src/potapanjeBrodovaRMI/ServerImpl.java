package potapanjeBrodovaRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Brodovi novaIgra() throws RemoteException {
		// TODO Auto-generated method stub
		Brodovi igra = new BrodoviImpl();
		System.out.println("Zapoceta je nova igra: " + igra);
		return igra;
	}

}
