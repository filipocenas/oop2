package igraPogadjanjaRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IgraImpl extends UnicastRemoteObject implements Igra {

	private final static int ZIVOTI = 10;
	private final static String[] RECI = new String[] { "Ptica", "Avion", "Paradajz", "Pilot", "Novine", "Anđeo",
			"Jabuka", "Knjiga", "Telefon", "More", "Automobil", "Kamera", "Drvo", "Laptop", "Škola", "Olovka", "Riba",
			"Sunce", "Planina", "Muzika", "Pas", "Kuća", "Sat", "Čarape", "Cveće", "Mleko", "Sto", "Voda", "Prozor",
			"Kosa", "Most", "Grad", "Med", "Zvezda", "Kiša", "Kafa", "Zima", "Jezero", "Broj", "Bicikl" };

	private final String rec;
	private final StringBuilder otkriveno;
	private int zivoti;

	public IgraImpl(String rec, int zivoti) throws RemoteException {
		this.rec = rec.toUpperCase();
		this.otkriveno = new StringBuilder(rec.length());
		for (int i = 0; i < rec.length(); i++) {
			this.otkriveno.append("-");
		}
		this.zivoti = zivoti;
	}

	public IgraImpl() throws RemoteException {
		this(RECI[(int) (RECI.length * Math.random())], ZIVOTI);
	}

	@Override
	public String otkriveno() throws RemoteException {
		return otkriveno.toString();
	}

	@Override
	public Odgovor pogadjaj(char slovo) throws RemoteException {
		// TODO Auto-generated method stub
		slovo = Character.toUpperCase(slovo);
		System.out.println("Igra " + this + ": pokusaj " + slovo);
		boolean pogodak = true;
		boolean promasaj = true;
		for (int i = 0; i < rec.length(); i++) {
			if (rec.charAt(i) == slovo) {
				promasaj = false;
				otkriveno.setCharAt(i, slovo);
			}
			if (otkriveno.charAt(i) == '-') {
				pogodak = false;
			}
		}
		if (promasaj)
			zivoti--;
		return new Odgovor(otkriveno.toString(), pogodak, zivoti);
	}

	@Override
	public String toString() {
		return String.format("#%08X", hashCode());
	}

}
