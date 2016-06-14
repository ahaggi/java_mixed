package no.hib.dat100.prosjekt.kontroll;

import java.util.ArrayList;

import no.hib.dat100.prosjekt.modell.Bunke;
import no.hib.dat100.prosjekt.modell.Kort;

/**
 * Klassen har objektvariaber som er referanser til de spillerne, nord og syd
 * (type ISpiller). Den har ogsأ‚ en bunke man deler/trekker fra og en bunke man
 * spiller til.
 * 
 */
public class Spill {

	ISpiller nord;
	ISpiller syd;
	Bunke bunketil;
	Bunke bunkefra;
	
	/**
	 * Gir referanse/peker til syd.
	 * 
	 * @return referanse/peker til syd.
	 */
	public ISpiller getSyd() {
			return syd;
	}

	/**
	 * Gir referanse/peker til nord.
	 * 
	 * @return referanse/peker til nord.
	 */
	public ISpiller getNord() {
		return nord;
	}

	/**
	 * Gir peker/referanse til til-bunken.
	 * 
	 * @return referanse/peker til til-bunken.
	 */
	public Bunke getBunkeTil() {
		return bunketil;
	}

	public Bunke getBunkeFra() {
		return bunkefra;
	}

	/**
	 * Metoden oppretter to spillere, nord og syd. Det opprettes to bunker, fra
	 * og til. Alle kortene legges til fra. Bunken fra stokkes. Deretter deles
	 * det ut kort fra fra-bunken til nord og syd i henhold til regler. Til
	 * slutt tas آ¯verste kortet fra fra-bunken og legges til til-bunken.
	 * 
	 * Nord har type RandomSpiller (som er forhأ‚ndefinert). Syd vil vأٹre spiller
	 * av en klasse laget av gruppen.
	 */
	public void start() {
		nord = new RandomSpiller(Spillere.NORD);
		syd = new FirstFitSpiller(Spillere.SYD);
		bunketil = new Bunke();
		bunkefra = new Bunke();
		bunkefra.leggTilAlle();
		bunkefra.stokk();
		delutKort();
		vendOverste();
	}

	/**
	 * Deler ut kort til nord og syd.
	 * 
	 */
	private void delutKort() {
		for (int i = 1; i <= Regler.antallKortVedStart(); i++) {
			nord.leggTilKort(bunkefra.trekk());
			syd.leggTilKort(bunkefra.trekk());
		}
	}

	/**
	 * Tar آ¯verste kortet fra fra-bunken og legger dettte til til-bunken (med
	 * billedsiden opp, men det trenger ikke gruppen tenke pأ‚).
	 */
	private void vendOverste() {
		bunketil.leggTil(bunkefra.trekk());
	}

	/**
	 * Nأ‚r fra-bunken blir tom, tar man vare pأ‚ kortet pأ‚ toppen av til-bunken.
	 * Deretter legges alle den andre kortene i til-bunken over i fra-bunken.
	 * Denne stokkes og kortet som man har tatt vare pأ‚ legges tilbake i
	 * til-bunken. Det vil nأ‚r vأٹre det eneste kortet i til-bunken.
	 */
	public void snuTilBunken() {

		if (bunkefraTom()) {
			Kort k = bunketil.trekk();//tar vare på kortet på toppen av til-bunken
			int bunketilantallkort=bunketil.getAntalKort();
			for (int i = 1; i <=bunketilantallkort ; i++) {//
				bunkefra.leggTil(bunketil.trekk());
			}
			bunkefra.stokk();
			bunketil.leggTil(k);
			
		}
	}

	/**
	 * Trekker et kort fra fra-bunken til spilleren gitt som parameter. Om
	 * fra-bunken er tom, mأ‚ man "snu" til-bunken. Se info om metoden
	 * snuTilBunken().
	 * 
	 * @param spiller
	 *            spilleren som trekker.
	 * 
	 * @return kortet som trekkes.
	 */
	public Kort trekkFraBunke(ISpiller spiller) {

		if (bunkefraTom()) {
			snuTilBunken();
		}
			Kort k=bunkefra.topp();
			spiller.trekker(bunkefra.trekk());
			return k;
	}

	/**
	 * Sjekker om til-bunken er tom.
	 * 
	 * @return true om til-bunken er tom, false ellers.
	 */
	public boolean bunketilTom() {
		return (bunketil.erTom());
	}

	/**
	 * Sjekker om fra-bunken er tom.
	 * 
	 * @return true om fra-bunken er tom, false ellers.
	 */
	public boolean bunkefraTom() {
		return (bunkefra.erTom());
	}

	/**
	 * Gir antall kort nord har pأ‚ hأ‚nden.
	 * 
	 * @return antall kort nord har pأ‚ hأ‚nden.
	 */
	public int antallNord() {
		return nord.getAntallKort();
	}

	/**
	 * Gir antall kort i fra-bunken.
	 * 
	 * @return antall kort i fra-bunken.
	 */
	public int antallBunkeFra() {
		return bunkefra.getAntalKort();		
	}

	/**
	 * Gir antall kort i til-bunken.
	 * 
	 * @return antall kort i til-bunken.
	 */
	public int antallBunkeTil() {
		return bunketil.getAntalKort();
	}

	/**
	 * Metode som leser آ¯verste kortet i til-bunken. Kortet vil fremdeles vأٹre
	 * آ¯verst i til-bunken etter at metoden er utfآ¯rt.
	 * 
	 * @return آ¯verste kortet i til-bunken.
	 */
	public Kort seOverste() {
		return bunketil.topp();
	}

	/**
	 * Gir syds hand som en ArrayList av Kort.
	 * 
	 * @return syds hand som en ArrayList av Kort.
	 */
	public ArrayList<Kort> getSydHand() {
		ArrayList<Kort> sydhand = new ArrayList<Kort>();
		for (Kort kort : syd.getHand().toArrayList()) {
			sydhand.add(kort);
		}
		return sydhand;
	}

	/**
	 * Gir neste handling for en spiller (spilt et kort, trekker et kort, forbi)
	 * 
	 * @param spiller
	 *            spiller som handle.
	 * 
	 * @return handlingen som blir utfآ¯rt.
	 */
	public Handling nesteHandling(ISpiller spiller) {
		return spiller.nesteHandling(bunketil.topp());//må referere til kort på toppen av til-bunke
	}

	/**
	 * Metoden spiller et kort. Den sjekker at spiller har kortet. Dersom det er
	 * tilfelle, fjernes kortet fra spilleren og legges til til-bunken. Metoden
	 * nulltiller ogsأ‚ antall ganger spilleren har trukket kort.
	 * 
	 * @param spiller
	 *            den som spiller.
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom spilleren har kortet, false ellers.
	 */
	public boolean leggnedKort(ISpiller spiller, Kort kort) {
		if (spiller.getHand().har(kort)) {//siden "har" methoden ikke er implementert i "ISpiller" ,må vi bruke "getHand()"  
			spiller.fjernKort(kort);
			spiller.getHand();
			bunketil.leggTil(kort);
			spiller.setAntallTrekk(0);
			return true;
		}
		return false;
	}

	/**
	 * Metode for أ‚ si forbi. Mأ‚ nullstille antall ganger spilleren har trukket
	 * kort.
	 * 
	 * @param spiller
	 *            spilleren som er i tur.
	 */
	public void forbiSpiller(ISpiller spiller) {
		spiller.setAntallTrekk(0);
	}

	/**
	 * Metode for أ‚ utfآ¯re en handling (trekke, spille, forbi). Dersom hanling
	 * er kort, blir kortet ogsأ‚ spilt.
	 * 
	 * @param spiller
	 *            spiller som utfآ¯rer handlingen.
	 * @param handling
	 *            handling som utfآ¯res.
	 * 
	 * @return kort som trekkes, kort som spilles eller null ved forbi.
	 */
	public Kort utforHandling(ISpiller spiller, Handling handling) {

		Kort k = null;
		
		if (handling.getType()== HandlingsType.LEGGNED){
			k = handling.getKort();
			leggnedKort(spiller, k);
		}else if (handling.getType()== HandlingsType.TREKK) {
				k = trekkFraBunke(spiller);//DET BLIR ET STORT PROBLEM. HVIS VI RETUNERE "handling.getKort()" FORDI KORTET ER NULL I"HandlingsType.TREKK"
		}else if (handling.getType()== HandlingsType.FORBI) {
			forbiSpiller(spiller);
		}
		return k;
	}

	/**
	 * Siller et kort fra syd.
	 * 
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom kortet er lovlig أ‚ spille, false ellers.
	 */
	public boolean nedkortSyd(Kort kort) {
			return leggnedKort(syd, kort);
	}

	/**
	 * Foreslأ‚r hva syd skal spille.
	 * 
	 * @return kort som blir foreslأ‚tt.
	 */
	public Kort foreslaaKortSyd() {
		ArrayList<Kort> hand = getSydHand();
		ArrayList<Kort> lovlige = new ArrayList<Kort>();
		ArrayList<Kort> attere = new ArrayList<Kort>();
		int [] handFarge =new int [4];
		int flest=0;
		int flestFargeHand=-1;
		for (Kort k : hand) {
			handFarge[k.getFarge().ordinal()]++;
			if (Regler.kanLeggeNed(k, bunketil.topp())) {//må referere til kort på toppen av til-bunke
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

		
		
		Kort foreslaar = null;
		ArrayList<Kort> spillFra = new ArrayList<Kort>();

		if (!lovlige.isEmpty()) {
			for (Kort k : lovlige) {
				if (k.getFarge().ordinal()==flestFargeHand) {spillFra.add(k);}
			}
		} else if (!attere.isEmpty()) {
			for (Kort k : attere) {spillFra.add(k);}
		
		}

		if (!spillFra.isEmpty()) {
//			Arrays.parallelSort(spillFra);
			foreslaar = spillFra.get(0);
		}
		return foreslaar;
	}
}