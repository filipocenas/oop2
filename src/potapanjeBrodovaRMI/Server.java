package potapanjeBrodovaRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
/*
 * Korišćenjem RMI implementirati igru potapanja brodova. 
 * Server na tabli 10x10 sakriva brodove koji su oblika xxxx, xxx i xx 
 * (fiksiranih demenzija i orijentacije). 
 * Server klijentu šalje tablu koja je na početku ispunjena tačkama. 
 * 
 * Klijent unosi koordinate u obliku x, y čime bira polje koje gađa na tabli. 
 * Ukoliko se na polju koje klijent gađa nalazi deo broda server menja tačkicu 
 * na tom polju simbolom X, a ako na tom polju nije bio deo broda server tačkicu 
 * zamenjuje sa O. Nakon svakog klijentovog pokušaja server odgovara novim 
 * statusom table. Igra se završava kada klijent pogodi svih 9 polja koja zauzimaju 
 * brodovi, a jedan server može da opsluži više klijenata.
 */
public interface Server extends Remote {
	public Brodovi novaIgra() throws RemoteException;
}
