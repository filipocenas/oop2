package igraPogadjanjaRMI;

import java.io.Serializable;

public class Odgovor implements Serializable {
	public final String otkriveno;
	public final boolean pogodak;
	public final int zivoti;

	public Odgovor(String otkriveno, boolean pogodak, int zivoti) {
		this.otkriveno = otkriveno;
		this.pogodak = pogodak;
		this.zivoti = zivoti;
	}
}
