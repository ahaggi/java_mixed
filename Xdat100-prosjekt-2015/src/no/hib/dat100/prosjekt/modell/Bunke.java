package no.hib.dat100.prosjekt.modell;

/**
 * Klasse for Â representere en bunke kort. Kan om n¯dvendig inneholde hele
 * kortstokken. Man kan bare ta ut / trekke fra toppen av bunken.
 *
 */
public class Bunke extends KortSamling {

	/**
	 * Lager en tom bunke.
	 * 
	 */

	public Bunke() {
		super();
	}

	/**
	 * Fjerner kortet som ligger pÂ toppen av bunken.
	 * 
	 * @return Kortet pÂ toppen av bunken. Kortet blir fjernet fra bunken.
	 * 
	 */
	public Kort trekk() {
		if (super.seSiste()!=null) {
			return super.taSiste();
		}else{
			super.fjern(super.seSiste());
			return trekk();
		}
	}

	/**
	 * "Ser" pÂ kortet som ligger pÂ toppen av bunken.
	 * 
	 * @return referanse til kortet pÂ toppen av bunken. Kortet blir ikke
	 *         fjernet.
	 * 
	 */
	public Kort topp() {
		if (super.seSiste()!=null) {
			return super.seSiste();
		}else{
			super.fjern(super.seSiste());
			return topp();
		}
	}
}
