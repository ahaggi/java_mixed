package no.hib.dat100.kontakter;

import java.util.HashMap;

public class KontaktListe {

	protected HashMap<String, KontaktInfo> kontakter; 
	// protected gj�r kontakter synlig i subklasser
	
	public KontaktListe() {
		this.kontakter = new HashMap<String, KontaktInfo>();
	}

	public KontaktInfo finnKontaktInfo(String navn) {

		KontaktInfo kontaktinfo = kontakter.get(navn);

		return kontaktinfo;
	}

	public void leggtilNummer(String navn, KontaktType type, int nummer) {

		kontakter.put(navn, new KontaktInfo(type,nummer));
	}
	
	public void nulstill () {
		kontakter.clear(); // clear metoden fjerner alle innganger i en hash-map.
	}
}