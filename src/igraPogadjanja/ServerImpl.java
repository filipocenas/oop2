package igraPogadjanja;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
	public static final int ZIVOTI = 10;
	public static final int MAX_BR = 100;
	
	protected ServerImpl() throws RemoteException {
		super();
	}
	
	@Override
	public Igra startIgra() throws RemoteException {
		int broj = 1 + (int) (MAX_BR * Math.random());
		int zivoti = ZIVOTI;
		Igra igra = new IgraImpl(broj, zivoti);
		System.out.println("Zapoceta nova igra: " + igra);
		return igra;
	}
}
