package prakticne2;

/*
 * U ulaznom fajlu 'raspored.ics' je dat raspored casova druge godine IT smera
 * u iCalendar formatu.
 *
 * Kalendar pocinje linijom 'BEGIN:VCALENDAR' a zavrsava se linijom
 * 'END:VCALENDAR'. Izmedju ove dve linije se nalaze podaci o svim dogadjajima
 * ukljucujuci i podatke o casovima predavanja i vezbi.
 *
 * Svaki cas predavanja i vezbi je predstavljen zasebnim dogadjajem. Svaki
 * dogadjaj pocinje linijom 'BEGIN:VEVENT' a zavrsava sa 'END:VEVENT'. Izmedju
 * ovih linija se nalaze podaci za konkretan dogadjaj, odnosno cas.
 *
 * Podaci o casovima se nalaze u osobinama odgovarajucih dogadjaja i zapisani
 * su na sledeci nacin, svaki u svojoj liniji:
 * OSOBINA;ATRIBUT1=Vrednost1;ATRIBUT2=Vrednost2:VrednostOsobine
 * Dugacke osobine se mogu razdvojiti u vise linija. Pri tome prva linija
 * pocinje normalno dok je svaka sledeca uvucena jednom prazninom. Cela osobina
 * se dobija konkatenacijom linija, naravno ne ukljucujuci vodecu prazninu.
 *
 * Osobine od znacaja za raspored casova su:
 *
 * 1) Vreme pocetka i kraja casa (DTSTART i DTEND) cija vrednost sadrzi datum i
 *    vreme u formatu YYYYMMDDTHHMMSS. Prvih 8 znakova predstavlja datum i moze
 *    se slobodno ignorisati. Poslednjih 6 znakova predstavlja vreme i sadrzi
 *    po dve cifre za sat, minut i sekindu pocetka, odnosno kraja casa. Takodje,
 *    osobina moze sadrzati i atribut sa podatkom o vremenskoj zoni koji se
 *    moze slobodno ignorisati.
 *
 * 2) Pravilo ponavljanja (RRULE) sadrzi, izmedju ostalih podataka, dan u
 *    nedelji kada se cas odrzava. Iako je ovaj podatak moguce izracunati na
 *    osnovu datuma u osobini (DTSTART) lakse je koristiti ovu osobinu. Dan u
 *    nedelji je zapisan kao engleska dvoslovna srkacenica (MO, TU, WE...) kao
 *    vrednost komponente 'BYDAY'.
 *
 * 3) Predmet, nastavnik, tip, sala (SUMMARY) se nalaze zajedno odvojeni
 *    zarezom ispred kojeg se nalazi obrnuta kosa crta.
 *
 * Vise podataka o iCalendar formatu se moze naci na sledecem linku:
 * https://en.wikipedia.org/wiki/ICalendar
 *
 * Napisati aplikaciju koja ucitava podatke iz fajla, i najpre proverava da li
 * su svi podaci u fajlu dobro zadati. Ukoliko za neki od casova ne postoji
 * potrebna osobina ili format osobine nije zapisan na gore naveden nacin,
 * program na ekran ispisuje koja osobina je u pitanju i u kom redu fajla se
 * nalazi.
 *
 * Zatim, program od korisnika u ucitava naziv predmeta i ispisuje na ekran dan
 * u nedelji i vremena pocetka i kraja casa predavanja zadatog predmeta (tip
 * casa predavanja je 'P') kao i salu u kojoj se odrzava (ili online ako se
 * odrzava online), odnosno odgovarajucu poruku ukoliko predmet ne postoji u fajlu.
 *
 * Program ne treba da razlikuje mala i velika slova ni u kom delu svoje
 * funkcionalnosti.
 *
 * Jedan primer casa zapisanog kao dogadjaj u kalendaru:
 *
 * ...
 * BEGIN:VEVENT
 * ...
 * DTSTART;TZID=Europe/Belgrade:20220221T120000
 * DTEND;TZID=Europe/Belgrade:20220221T140000
 * ...
 * RRULE:FREQ=WEEKLY;WKST=MO;COUNT=15;BYDAY=MO
 * ...
 * SUMMARY:Objektno - orijentisano programiranje 2\, M. Radovanović\, (P)\, online
 * ...
 * END:VEVENT
 * ...
 * 
 * Ovaj dogadjaj predstavlja predavanja iz predmeta Objektno-orijentisano
 * programiranje 2, koji se odrzava ponedeljkom od 12 do 14h online.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class RasporedProgram {

	public static void main(String[] args) throws IOException {

		String lines = readFile();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Unesite naziv predmeta: ");
		String unos = in.readLine();
		print(lines, unos);

	}

	private static String readFile() throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				RasporedProgram.class.getResourceAsStream("raspored.ics")))) {
			StringJoiner lines = new StringJoiner("\n");
			String line;
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
			return lines.toString();
		}
	}
	
/*
 * Napisati aplikaciju koja ucitava podatke iz fajla, i najpre proverava da li
 * su svi podaci u fajlu dobro zadati. Ukoliko za neki od casova ne postoji
 * potrebna osobina ili format osobine nije zapisan na gore naveden nacin,
 * program na ekran ispisuje koja osobina je u pitanju i u kom redu fajla se
 * nalazi.
 */

	private static void check(String lines) {
		// TODO Implementirati
	}

	private static void print(String lines, String name) {
		// TODO Implementirati
	}
}


