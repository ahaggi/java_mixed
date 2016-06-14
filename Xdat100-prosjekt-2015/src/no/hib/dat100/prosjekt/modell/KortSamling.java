package no.hib.dat100.prosjekt.modell;

import java.util.ArrayList;
import java.util.Random;

/**
 * Struktur for أ‚ lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * konstanter i klassen som angir antall kort i hver av de 4 fargene. Nأ‚r
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det vأٹre praktisk أ‚ ha denne mindre.
 * 
 */
public abstract class KortSamling {

	public static final int MAKS_KORT_FARGE = 13;
	private final int MAKS_KORT = 4 * MAKS_KORT_FARGE;
	private Kort[] korttabell;
	private int antall=0;			

	// legg til objektvariable her

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		korttabell= new Kort [MAKS_KORT];
	}

	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {
		return (antall==0);
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke vأٹre
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan fأ‚
	 * tilgang til antallet ved أ‚ bruke metoden getAntallKort(). Metoden er
	 * fآ¯rst og fremst ment أ‚ brukes i eventuelle subklasser. Om man trenger
	 * kortene utenfor arvehierarkiet, anbefales metoden toArrayList().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {// kan bruke getSamling() eller toArrayList()
		return korttabell;
	}

	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {
		return antall;
	}

	/**
	 * Legger alle korta (hele kortstokken) til samlnga. Korta vil vأٹre sortert
	 * slik at de normalt mأ‚ stokkes fآ¯r bruk.
	 */
	public void leggTilAlle() {
		for (Kortfarge f : Kortfarge.values()) {
			for (int i = 1; i <= MAKS_KORT_FARGE; i++) {//kort per farge
				Kort k = new Kort( f, i);
				leggTil(k);
			}//for
		}//for each 
	}

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		for (int i = 0; i < antall; i++) {
			korttabell[i]=null;
		}
		antall=0;
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) {//ikke sorter noe her. Fordi methoden brukes av "Bunke"
		if(antall<=korttabell.length && kort!=null){//ikke nødvendig å teste
			korttabell[antall]=kort;
			antall++;
		}
	}

	/**
	 * Ser pأ‚ siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet.
	 */
	public Kort seSiste() {
		return korttabell[antall-1];
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		Kort k=null;
		if (!erTom()) {
		//	antall--
			k=korttabell[antall-1];//Antall peker på en ny tom plass i korttabell
			fjern(k);
			}
		return k;
	}

	/**
	 * Undersآ¯ker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {

		for (int i = 0; i < antall; i++) {
			if (korttabell[i].equals(kort)) {//OPS OPS blir feil hvis det var (kort.equals(korttabell[i])). Man kan sammenligne noe med null men ikke det motsatte
				return true;
			}
		}
		return false;
	}

	/**
	 * Fjernar et kort frأ‚ samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting.
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 */
	public void fjern(Kort kort) {
		for (int i = 0; i < antall; i++) {
			if (korttabell[i].equals(kort)) {
				int xantall= antall-1;//ikke nødvendig men bare vane
				if (i != xantall) {//tester hvis "i" er index til det siste kortet i tabellen
				korttabell[i]=korttabell[antall-1];
				korttabell[antall-1]=null;
				antall--;
				}else {
				korttabell[antall-1]=null;
				antall--;
				}//end else
			}//end if
			}//end for
	}

	/**
	 * Stokkar en kortsamling. Etter metoden er utfآ¯rt har alle kort samme
	 * sannsynligheit for أ‚ vأٹre pأ‚ ein spesiell plass.
	 * 
	 */
	public void stokk() {
		Random random = new Random();
		Kort k=null;
		if (!erTom()) {

		for (int i = 0; i < antall; i++) {
			int randomIndx =random.nextInt(antall);//for å få fom 0 tom antall, hvis (antall =7) fom 0 tom 6
			k=korttabell[randomIndx];
			korttabell[randomIndx]=korttabell[i];
			korttabell[i]=k;
		}//for
		
		}//if
	}

	/**
	 * Gir kortene som en ArrayList.
	 * 
	 * @return samlinga av kort som en ArrayList. Korta vil ha samme rekkefآ¯lge
	 *         som i kortsamlinga.
	 */
	public ArrayList<Kort> toArrayList() {
		
		ArrayList<Kort> kort = new ArrayList<Kort>();
		for (int i = 0; i < antall; i++) {
			kort.add(korttabell[i]);
		}
		return kort;
	}
}
