package igraPogadjanja;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
	public Igra startIgra() throws RemoteException;
}
