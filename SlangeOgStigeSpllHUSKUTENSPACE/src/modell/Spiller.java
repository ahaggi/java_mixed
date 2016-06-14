package modell;

 
/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
 public class Spiller {
 	private Integer spillerID;

 	private Brett brett;

 	private Brikke brikke;

 	private Spill spill;

 	private Boolean punish = false;

	/**
	 * Tom spiller.
	 */
	public Spiller() {

	}

	/**
	 * Oppretter ny spiller.
	 * 
	 * @param spillerID
	 * @param brikke
	 * @param spill
	 */
	public Spiller(Integer spillerID, Brikke brikke, Spill spill) {
		super();
		this.spillerID = spillerID;
		this.brett = spill.getBrett();
		this.brikke = brikke;
		this.spill = spill;
	}

	/**
	 * Flytter spiller til en bestemt rute.
	 * 
	 * @param nyRute
	 */
	public void flytt(Rute nyRute) {

		brikke.flytt(nyRute);

	}

	public Integer getSpillerID() {
		return spillerID;
	}

	public void setSpillerID(Integer spillerID) {
		this.spillerID = spillerID;
	}

	public Spill getSpill() {
		return spill;
	}

	public void setSpill(Spill spill) {
		this.spill = spill;
	}

	public Brett getBrett() {
		return brett;
	}

	public void setBrett(Brett brett) {
		this.brett = brett;
	}

	public Brikke getBrikke() {
		return brikke;
	}

	public void setBrikke(Brikke brikke) {
		this.brikke = brikke;
	}

	public Boolean getPunish() {
		return punish;
	}

	public void setPunish(Boolean punish) {
		this.punish = punish;
	}

	@Override
	public String toString() {
		return "Spiller [spillerId=" + spillerID + ", brett=" + brett + ", brikke=" + brikke + "]";
	}

}
