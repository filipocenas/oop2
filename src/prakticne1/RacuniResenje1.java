package prakticne1;

/*
 * U ulaznim fajlovima su dati fiskalni racuni izdati od strane jedne mlekare.
 * Fajlovi su imenovani po broju racuna.
 * 
 * Napisati klasu Racun kojom se prestavljaju ovi fiskalni racuni. Svaki racun
 * ima svoj redni broj, datum i vreme kada je izdat, listu stavki na racunu,
 * kao i koliko je gotovine uplaceno kada je racun placen.
 * 
 * Napisati klasu Stavka cije instance predstavljaju stavke racuna, a svaka
 * stavka se sastoji od imena proizvoda, kolicine tog proizvoda, cene po
 * jedinici mere i poreske stope.
 * 
 * Poreska stopa je predstavljena nabrojivim tipom PoreskaStopa koji moze imati
 * tri vrednosti: OPSTA (20% poreza, oznaka DJ), POSEBNA (10% poreza, oznaka E)
 * i OSLOBODJEN (0% poreza, oznaka O).
 *  
 * Napisati program koji pita korisnika za broj racuna i potom ucitava podatke
 * o tom racunu iz odgovarajuceg fajla.

 * Implementirati metod parse() koji sadrzaj procitan iz fajla pretvara u
 * objekat klase Racun.
 * 
 * Takodje implementirati metod print() koji stampa podatke o prosledjenom
 * racunu.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Racun {

	private final int broj;
	private final LocalDateTime vreme;
	private final int uplaceno;
	private final List<Stavka> stavke;

	public Racun(int broj, LocalDateTime vreme, int uplaceno, List<Stavka> stavke) {
		this.broj = broj;
		this.vreme = vreme;
		this.uplaceno = uplaceno;
		this.stavke = Collections.unmodifiableList(stavke);
	}

	public int getBroj() {
		return broj;
	}

	public LocalDateTime getVreme() {
		return vreme;
	}

	public int getUplaceno() {
		return uplaceno;
	}

	public List<Stavka> getStavke() {
		return stavke;
	}
}

class Stavka {

	private final String proizvod;
	private final double kolicina;
	private final double cena;
	private final PoreskaStopa stopa;

	public Stavka(String proizvod, double kolicina, double cena, PoreskaStopa stopa) {
		this.proizvod = proizvod;
		this.kolicina = kolicina;
		this.cena = cena;
		this.stopa = stopa;
	}

	public String getProizvod() {
		return proizvod;
	}

	public double getKolicina() {
		return kolicina;
	}

	public double getCena() {
		return cena;
	}

	public PoreskaStopa getStopa() {
		return stopa;
	}
}

enum PoreskaStopa {

	OPSTA(20, '\u00d0'), POSEBNA(10, 'E'), OSLOBODJEN(0, 'G');

	private final int stopa;
	private final char oznaka;

	private PoreskaStopa(int stopa, char oznaka) {
		this.stopa = stopa;
		this.oznaka = oznaka;
	}

	public int getStopa() {
		return stopa;
	}

	public char getOznaka() {
		return oznaka;
	}

	public static PoreskaStopa parseStopa(String oznaka) {
		if (oznaka.length() != 1) {
			return null;
		}
		char o = oznaka.charAt(0);
		for (PoreskaStopa ps : values()) {
			if (ps.oznaka == o) {
				return ps;
			}
		}
		return null;
	}
}

public class RacuniResenje1 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Unesite broj racuna: ");
		String unos = in.readLine();
		String text = readFile(unos + ".txt");
		Racun racun = parse(text);
		print(racun);

	}

	private static String readFile(String name) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(name))) {
			StringBuilder text = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
			return text.toString();
		}
	}

	private static Racun parse(String text) {
		List<Stavka> stavke = new ArrayList<>();
		Pattern ptrStavka = Pattern.compile("^(?<naziv>.*?)\\s*\\n\\s*(?<kolicina>\\d{1,}(?:\\.\\d{3})?)x\\s*(?<cena>\\d{1,}\\.\\d{2})\\s*\\d{1,}\\.\\d{2}\\s(?<stopa>[EOÐ])$", Pattern.MULTILINE);
		Matcher m = ptrStavka.matcher(text);
		while (m.find()) {
			String naziv = m.group("naziv");
			double kolicina = Double.parseDouble(m.group("kolicina"));
			double cena = Double.parseDouble(m.group("cena"));
			PoreskaStopa stopa = PoreskaStopa.parseStopa(m.group("stopa"));
			Stavka stavka = new Stavka(naziv, kolicina, cena, stopa);
			stavke.add(stavka);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
		Pattern ptrRacun = Pattern.compile(".*UPLACENO\\s+(?<uplaceno>\\d{1,}\\.\\d{2})\n.*(?<vreme>\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d\\. \\d\\d:\\d\\d).*BI: (?<broj>\\d{6}).*", Pattern.DOTALL);
		m = ptrRacun.matcher(text);
		if (m.matches()) {
			int uplaceno = (int) Double.parseDouble(m.group("uplaceno"));
			LocalDateTime vreme = LocalDateTime.parse(m.group("vreme"), formatter);
			int broj = Integer.parseInt(m.group("broj"));
			Racun racun = new Racun(broj, vreme, uplaceno, stavke);
			return racun;
		} else {
			return null;
		}
	}

	private static void print(Racun racun) {
		System.out.println();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
		System.out.println("Racun br. " + racun.getBroj());
		System.out.println("Izdat:    " + formatter.format(racun.getVreme()));
		System.out.println("Uplaceno: " + racun.getUplaceno());
		System.out.println("Kupljeno:");
		for (Stavka stavka : racun.getStavke()) {
			System.out.println(stavka.getProizvod());
		}
		
	}
}
