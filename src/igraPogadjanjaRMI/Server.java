package igraPogadjanjaRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Igra vešanja se igra tako što server pri uspostavi 
 * konekcije izabere neku reč i pošalje klijentu string 
 * iste dužine kao i reč, ali koji se sastoji smo od znakova '-'. 
 * Pored toga, server šalje i broj pokušaja za pogađanje (života). 
 * Posle ovoga, server prihvata od klijenta jedno slovo i šalje novi 
 * string i preostali broj pokušaja. 
 * Ako reč sadrži dato slovo, server otkriva sve pozicije tog slova u 
 * reči (na odgovarajućim mestima se nalazi to slovo umesto prvobitnog 
 * znaka '-'), u suprotnom se umanjuje broj života. Igra se ponavlja 
 * ili dok igrač ne pogodi reč, u kom slučaju igrač pobeđuje, 
 * ili dok ne izgubi poslednji život, u kom slučaju gubi igru. 
 * Po završetku igre, konekcija sa tim klijentom se zatvara.
 * Igru implementirati tako da više igrača može istovremeno 
 * da se igra na istom serveru i svaki igrač ima svoju 
 * reč za pogađanje.
 */
public interface Server extends Remote {
	public Igra novaIgra() throws RemoteException;
}
