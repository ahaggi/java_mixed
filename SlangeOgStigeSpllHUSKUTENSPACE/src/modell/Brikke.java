package modell;

 
/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */

  public class Brikke {
 	private Integer brikkeID;

 	private Rute plass;

 	private Spill spill;

	/**
	 * Lager en ny brikke.
	 * 
	 * @param brikkeID
	 * @param plass
	 * @param spillID
	 */
	public Brikke(Integer brikkeID, Rute plass, Spill spillID) {
		super();
		this.brikkeID = brikkeID;
		this.plass = plass;
		this.spill = spillID;
	}

	/**
	 * tom konstrukt�r
	 */
	public Brikke() {

	}

	/**
	 * flytter brikke
	 * 
	 * @param Rute
	 *            rete
	 */

	public void flytt(Rute rute) {
		plass = rute;
	}

	public Integer getBrikkeID() {
		return brikkeID;
	}

	public void setBrikkeID(Integer brikkeID) {
		this.brikkeID = brikkeID;
	}

	public Rute getPlass() {
		return plass;
	}

	public void setPlass(Rute plass) {
		this.plass = plass;
	}

	public Spill getSpillID() {
		return spill;
	}

	public void setSpillID(Spill spillID) {
		this.spill = spillID;
	}

	@Override
	public String toString() {
		return "Brikke [BrikkeID=" + brikkeID + ", plass=" + plass + "]";
	}

}
