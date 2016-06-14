package no.hib.dat100.prosjekt.kontroll;

import no.hib.dat100.prosjekt.modell.Hand;
import no.hib.dat100.prosjekt.modell.Kort;

/**
 * Abstrakt klasse som implementerer alle metodene i kontrakten ISpiller,
 * bortsett fra nesteHandling(). Dette er grunnen til at klassen er abstrakt.
 * For أ‚ lage "virkelige" spillere, mأ‚ vi arve fra denne klassen og implmentere
 * nesteHandling (fra ISpiller).
 * 
 * Klassen har objektvariablene hand (Hand), antalltrekk (heltall) og spiller
 * (Spillere). Den har to konstruktآ¯rer. Se beskrivelse av disse.
 * 
 */
public abstract class Spiller implements ISpiller {

	private Hand hand;
	private int antalltrekk;
	private Spillere spiller;

	/**
	 * Standard konstruktآ¯r som oppretter en Spiller med en hأ‚nd uten kort,
	 * antalltrekk som 0 og setter spiller til Spillere.INGEN.
	 */
	public Spiller() {
		hand=new Hand();
		antalltrekk=0;
		spiller=Spillere.INGEN;
	}

	/**
	 * Konstruktآ¯r der vi kan sette hvilken spiller det er (NORD, SYD eller
	 * INGEN).
	 * 
	 * @param spiller
	 *            hvilken spiller det er.
	 */
	public Spiller(Spillere spiller) {
		hand=new Hand();
		antalltrekk=0;
		this.spiller=spiller;
	}

	@Override
	public int getAntallKort() {
		return hand.getAntalKort();
	}

	public int getAntallTrekk() {
		return antalltrekk;
	}

	public Spillere hvem() {
		return spiller;
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	public void setAntallTrekk(int t) {
		antalltrekk=t;
	}

	@Override
	public boolean erFerdig() {
		return hand.erTom();
	}

	@Override
	public void leggTilKort(Kort kort) {
		hand.leggTil(kort);
	}

	@Override
	public void fjernKort(Kort kort) {
		hand.fjern(kort);
	}

	@Override
	public void fjernAlleKort() {
		hand.fjernAlle();
	}

	@Override
	public void trekker(Kort kort) {
		if (kort!=null) {//unødvendig test
			leggTilKort(kort);
			antalltrekk++;
		}else{
			System.out.println("kan ikke \"trekker\" NULL ");
		}
	}
}
