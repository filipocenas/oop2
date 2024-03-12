//package prakticne1;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///*
// * U ulaznim fajlovima su dati fiskalni racuni izdati od strane jedne mlekare.
// * Fajlovi su imenovani po broju racuna.
// * 
// * Napisati klasu Racun kojom se prestavljaju ovi fiskalni racuni. Svaki racun
// * ima svoj redni broj, datum i vreme kada je izdat, listu stavki na racunu,
// * kao i koliko je gotovine uplaceno kada je racun placen.
// * 
// * Napisati klasu Stavka cije instance predstavljaju stavke racuna, a svaka
// * stavka se sastoji od imena proizvoda, kolicine tog proizvoda, cene po
// * jedinici mere i poreske stope.
// * 
// * Poreska stopa je predstavljena nabrojivim tipom PoreskaStopa koji moze imati
// * tri vrednosti: OPSTA (20% poreza, oznaka DJ), POSEBNA (10% poreza, oznaka E)
// * i OSLOBODJEN (0% poreza, oznaka O).
// *  
// * Napisati program koji pita korisnika za broj racuna i potom ucitava podatke
// * o tom racunu iz odgovarajuceg fajla.
//
// * Implementirati metod parse() koji sadrzaj procitan iz fajla pretvara u
// * objekat klase Racun.
// * 
// * Takodje implementirati metod print() koji stampa podatke o prosledjenom
// * racunu.
// */
//
//class Racun {
//	private final int redniBroj;
//	private final LocalDateTime vreme;
//	private final List<Stavka> stavke;
//	private final double uplaceno;
//	
//	public Racun(int redniBroj, LocalDateTime vreme, List<Stavka> stavke, double uplaceno) {
//		this.redniBroj = redniBroj;
//		this.vreme = vreme;
//		this.stavke = Collections.unmodifiableList(stavke);
//		this.uplaceno = uplaceno;
//	}
//	
//	public int getRedniBroj() { return redniBroj; }
//	public LocalDateTime getVreme() { return vreme; }
//	public List<Stavka> getStavke() { return stavke; }
//	public double getUplaceno() { return uplaceno; }
//	
//}
///*
// * Napisati klasu Stavka cije instance predstavljaju stavke racuna, a svaka
// * stavka se sastoji od imena proizvoda, kolicine tog proizvoda, cene po
// * jedinici mere i poreske stope.
// */
//class Stavka {
//	private final String proizvod;
//	private final double kolicina;
//	private final double cena;
//	private final PoreskaStopa poreskaStopa;
//	
//	public Stavka(String proizvod, double kolicina, double cena, PoreskaStopa poreskaStopa) {
//		this.proizvod = proizvod;
//		this.kolicina = kolicina;
//		this.cena = cena;
//		this.poreskaStopa = poreskaStopa;
//	}
//	
//	public String getProizvod() {
//		return proizvod;
//	}
//	
//	public double getKolicina() {
//		return kolicina;
//	}
//	
//	public double getCena() {
//		return cena;
//	}
//	
//	public PoreskaStopa getPoreskaStopa() {
//		return poreskaStopa;
//	}
//}
///*
// *  Poreska stopa je predstavljena nabrojivim tipom PoreskaStopa koji moze imati
// * tri vrednosti: OPSTA (20% poreza, oznaka DJ), POSEBNA (10% poreza, oznaka E)
// * i OSLOBODJEN (0% poreza, oznaka O).
// */
//enum PoreskaStopa {
//	OPSTA(20, '\u00d0'), POSEBNA(10, 'E'), OSLOBODJEN(0, 'G');
//	private final int stopa;
//	private final char oznaka;
//	
//	private PoreskaStopa(int stopa, char oznaka) {
//		this.stopa = stopa;
//		this.oznaka = oznaka;
//	}
//	
//	public int getStopa() {
//		return stopa;
//	}
//	
//	public char getOznaka() {
//		return oznaka;
//	}
//	
//	public static PoreskaStopa parsePoreska(String oznaka) {
//		if(oznaka.length() != 1) {
//			return null;
//		}
//		char o = oznaka.charAt(0);
//		for(PoreskaStopa ps : values()) {
//			if(ps.oznaka == o) {
//				return ps;
//			}
//		}
//		return null;
//	}
//}
///*
// * Implementirati metod parse() koji sadrzaj procitan iz fajla pretvara u
// * objekat klase Racun.
// * 
// * Takodje implementirati metod print() koji stampa podatke o prosledjenom
// * racunu.
// */
//public class Racuni {
//	public static void main(String [] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Unesite broj racuna: ");
//		String unos = br.readLine();
//		String text = readFile(unos + ".txt");
//		Racun racun = parse(text);
//		print(racun);
//	}
//	
//	private static String readFile(String fileName) throws IOException {
//		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//			StringBuilder text = new StringBuilder();
//			String line;
//			while((line = br.readLine()) != null) {
//				text.append(line);
//				text.append('\n');
//			}
//			return text.toString();
//		}
//	}
//	
//	private static Racun parse(String text) {
//		List<Stavka> stavke = new ArrayList<>();
//		Pattern ptrStavka = Pattern.compile("^(?<naziv>.*?)\\s*\\n\\s*(?<kolicina>\\d{1,}(?:\\.\\d{3})?)x\\s*(?<cena>\\d{1,}\\.\\d{2})\\s+\\d{1,}\\.\\d{2}\\s(?<stopa>[EOĐ])$", Pattern.MULTILINE);
//		Matcher m = ptrStavka.matcher(text);
//		while(m.find()) {
//			String naziv = m.group("naziv");
//			double kolicina = Double.parseDouble(m.group("kolicina"));
//			double cena = Double.parseDouble(m.group("cena"));
//			PoreskaStopa stopa = PoreskaStopa.parsePoreska(m.group("stopa"));
//			Stavka stavka = new Stavka(naziv, kolicina, cena, stopa);
//			stavke.add(stavka);
//		}
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
//		Pattern ptrRacun = Pattern.compile(".*UPLACENO\\s+(?<uplaceno>\\d{1,}\\.\\d{2})\\n.*\\n.*\\n(?<vreme>\\d\\d\\.\\d\\d.\\d\\d\\d\\d.\\s\\d\\d\\:\\d\\d).*\\nBI\\:\\s(?<broj>\\d{6}).*", Pattern.DOTALL);
//		m = ptrRacun.matcher(text);
//		if(m.matches()) {
//			double uplaceno = Double.parseDouble(m.group("uplaceno"));
//			LocalDateTime vreme = LocalDateTime.parse(m.group("vreme"), dtf);
//			int broj = Integer.parseInt(m.group("broj"));
//			Racun racun = new Racun(broj, vreme, stavke, uplaceno);
//			return racun;
//		} else {
//			return null;
//		}
//	}
//	
//	
//	
//	private static void print(Racun racun) {
//		System.out.println();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
//		System.out.println("Racun broj: " + racun.getRedniBroj());
//		System.out.println("Izdat: " + dtf.format(racun.getVreme()));
//		System.out.println("Uplaceno: " + racun.getUplaceno());
//		System.out.println("Kupljeno: ");
//		for(Stavka stavka : racun.getStavke()) {
//			System.out.println(stavka.getProizvod());
//		}
//	}
//}
//
//
//
//
//
//
