package no.hib.dat101;

import java.util.*;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
public class Terning {

	private static final Random RAND = new Random();
	private Integer verdi;

	/**
	 * Tom terning.
	 */
	public Terning() {

	}

	/**
	 * Oppretter ny terning.
	 * 
	 * @param verdi
	 */
	public Terning(Integer verdi) {
		super();
		this.verdi = verdi;
	}

	/**
	 * Triller terningen.
	 */
	public final void trill() {
		verdi = RAND.nextInt(6) + 1;
	}

	public Integer getVerdi() {
		return verdi;
	}

	public void setVerdi(Integer verdi) {
		this.verdi = verdi;
	}

	@Override
	public String toString() {
		return "Terning [verdi=" + verdi + "]";
	}

}
