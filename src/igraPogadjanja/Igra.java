package igraPogadjanja;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Igra extends Remote {
	public Odgovor pogadjaj(int brojPokusaja) throws RemoteException;

	
}
