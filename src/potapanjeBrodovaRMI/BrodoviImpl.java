package potapanjeBrodovaRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BrodoviImpl extends UnicastRemoteObject implements Brodovi {
	private boolean[][] dodeljenaPolja;
	private char[][] iscrtanaPolja;
	private int pogodak;

	public BrodoviImpl() throws RemoteException {
		inicijalizuj();
		rasporediBrodove();
	}

	private void inicijalizuj() {
		iscrtanaPolja = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				iscrtanaPolja[i][j] = '.';
			}
		}
	}

	private void rasporediBrodove() {
		dodeljenaPolja = new boolean[10][10];

		dodeljenaPolja[0][0] = true;
		dodeljenaPolja[0][1] = true;
		dodeljenaPolja[0][2] = true;
		dodeljenaPolja[0][3] = true;

		dodeljenaPolja[2][3] = true;
		dodeljenaPolja[2][4] = true;
		dodeljenaPolja[2][5] = true;

		dodeljenaPolja[5][5] = true;
		dodeljenaPolja[5][6] = true;
		dodeljenaPolja[5][7] = true;

		dodeljenaPolja[7][7] = true;
		dodeljenaPolja[7][8] = true;

	}

	public boolean jeKraj() {
		if (pogodak == 12) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Odgovor pogadjaj(int x, int y) throws RemoteException {
		if (dodeljenaPolja[x][y] == true) {
			pogodak++;
			iscrtanaPolja[x][y] = 'X';
			if (jeKraj()) {
				return Odgovor.BRAVO;
			} else {
				return Odgovor.POSTOJI;
			}
		} else {
			iscrtanaPolja[x][y] = 'O';
		}
		return Odgovor.NEPOSTOJI;
	}

	@Override
	public String stampajTablu() throws RemoteException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				sb.append(iscrtanaPolja[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("#%08X", hashCode());
	}
}
