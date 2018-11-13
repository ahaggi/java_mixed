package no.hib.dat101;

import javax.persistence.*;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
@Entity
@Table(schema = "slangeogstige")

public class Rute {
	@Id

	private Integer ruteID;
	@OneToOne
	@JoinColumn(name = "destinasjon", referencedColumnName = "ruteID", insertable = true, updatable = true)
	private Rute destinasjon;

	private String type;

	@ManyToOne
	@JoinColumn(name = "brett", referencedColumnName = "brettID")
	private Brett brett;

	/**
	 * tom konstrukt�r
	 */
	public Rute() {

	}

	/**
	 * lager en ny rute
	 * 
	 * @param ruteID
	 * @param brettId
	 */
	public Rute(Integer ruteID, Brett brettId) {
		super();
		this.ruteID = ruteID;
		this.brett = brettId;
		type = "Normal";
		destinasjon = this;
	}

	public Integer getRuteId() {
		return ruteID;
	}

	public void setRuteId(Integer ruteID) {
		this.ruteID = ruteID;
	}

	public Rute getDestinasjon() {
		return destinasjon;
	}

	public void setDestinasjon(Rute destinasjon) {
		this.destinasjon = destinasjon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Brett getBrettId() {
		return brett;
	}

	public void setBrettId(Brett brettId) {
		this.brett = brettId;
	}

}