package no.hib.dat100.kontaktlisteunntak;

import no.hib.dat100.kontakter.KontaktInfo;
import no.hib.dat100.kontakter.KontaktListe;

public class KontaktListeFinnSikker extends KontaktListe {

	public KontaktInfo finnKontaktInfoSikker(String navn) throws KeyNotUsedException {
		
		KontaktInfo ki;
		
		// TODO - START
		// check parametre og signaller exception om nødvendigt
		
		if (navn == null) {
			throw new IllegalArgumentException ("finnKontaktInfo - Parameter navn null");
		}
		else if (kontakter.containsKey(navn))
		{
			ki = kontakter.get(navn);
		}
		else {
			// Kunne alternativ velge å beholde null exception
			throw new KeyNotUsedException("Navn " + navn + " ikke i bruk"); 
		}
		
		// TODO - SLUTT
		return ki;
	}
}
