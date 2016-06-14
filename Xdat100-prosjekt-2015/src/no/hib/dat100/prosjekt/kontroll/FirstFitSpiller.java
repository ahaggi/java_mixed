package no.hib.dat100.prosjekt.kontroll;

import java.util.ArrayList;

import no.hib.dat100.prosjekt.modell.Kort;

/**
 * Klasse som for أ‚ representere en vriأ‚tter spiller. Strategien er أ‚ lete
 * gjennom kortene man har pأ‚ hand og spille det fآ¯rste som er lovlig.
 *
 */
public class FirstFitSpiller extends Spiller {

	/**
	 * Konstruktآ¯r.
	 * 
	 * @param spiller
	 *            posisjon for spilleren (nord eller syd).
	 */
	public FirstFitSpiller(Spillere spiller) {
		super(spiller);
	}

	/**
	 * Metode for أ‚ implementere strategi. Strategien er أ‚ spille det fآ¯rste
	 * kortet som er lovlig (ogsأ‚ en أ‚tter selv om man har andre kort som ogsأ‚
	 * kan spilles). Dersom man ikke har lovlige kort أ‚ spille, trekker man om
	 * man ikke allerede har trukket maks antall ganger. I sأ‚ fall sier man
	 * forbi.
	 * 
	 * @param topp
	 *            kort som ligg آ¯verst pأ‚ til-bunken.
	 */
	@Override
	public Handling nesteHandling(Kort topp) {

		ArrayList<Kort> hand = getHand().toArrayList();
		ArrayList<Kort> lovlige = new ArrayList<Kort>();
		ArrayList<Kort> attere = new ArrayList<Kort>();
		int [] handFarge =new int [4];
		int flest=0;
		int flestFargeHand=-1;
		for (Kort k : hand) {
			handFarge[k.getFarge().ordinal()]++;
			if (Regler.kanLeggeNed(k, topp)) {//må referere til kort på toppen av til-bunke
				if (Regler.atter(k)) {
					attere.add(k);
				} else {
					lovlige.add(k);
				}
			}
		}
		int [] lovligeFarge =new int [4];
		for (Kort k : lovlige) {
			lovligeFarge[k.getFarge().ordinal()]++;
		}
		
		for (int i = 0; i < lovligeFarge.length; i++) {
		if (flest <handFarge[i]&&lovligeFarge[i]>0) {
			flest=handFarge[i];
			flestFargeHand=i;
				
		}
	}

		
		
		Kort spill = null;
		ArrayList<Kort> spillFra = new ArrayList<Kort>();

		if (!lovlige.isEmpty()) {
			for (Kort k : lovlige) {
				if (k.getFarge().ordinal()==flestFargeHand) {spillFra.add(k);}
			}
		} else if (!attere.isEmpty()) {
			for (Kort k : attere) {spillFra.add(k);}
		
		}
		
		Handling tur = null;
		if (!spillFra.isEmpty()) {
			spill = spillFra.get(0);
			System.out.println("Firstfit : " + spill);
			tur = new Handling(HandlingsType.LEGGNED, spill);
			setAntallTrekk(0);
		} else if (getAntallTrekk() < Regler.maksTrekk()) {
			tur = new Handling(HandlingsType.TREKK, null);
		} else {
			tur = new Handling(HandlingsType.FORBI, null);
			setAntallTrekk(0);
		}

		return tur;
	}
}
