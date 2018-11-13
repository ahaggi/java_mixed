package no.hib.dat101;

import java.util.*;

import javax.persistence.*;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
@Entity
@Table(schema = "slangeogstige")
public class Brett {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer brettID;

	@OneToMany(mappedBy = "brett", cascade = CascadeType.ALL)
	private List<Rute> ruter = new ArrayList<Rute>();

	private int AntallRuter = 100;

	@OneToOne
	@JoinColumn(name = "spill", referencedColumnName = "spillID")
	private Spill spill;

	/**
	 * Tomt brett
	 */
	public Brett() {
		super();
	}

	/**
	 * Nytt brett
	 * 
	 * @param spill
	 */
	public Brett(Spill spill) {
		super();
		this.spill = spill;

		lagNyeRuter();
	}

	/**
	 * Legger til ruter på brett.
	 */
	private void lagNyeRuter() {

		for (int i = 1; i <= AntallRuter; i++) {
			Rute rute = new Rute(i, this);
			ruter.add(rute);
		}
		setDestinasjonForAlle();
	}

	/**
	 * Finner en bestemt rute.
	 * 
	 * @param Id
	 * @return rute
	 */
	public Rute finnRute(int Id) {
		Rute rute = null;
		for (Rute r : ruter) {
			if (r.getRuteId() == Id) {
				return rute = r;
			}
		}
		return rute;
	}

	/**
	 * Setter desinasjonen til en bestemt rute(slange, stige eller seg selv)
	 * 
	 * @param ruteNDX
	 * @param destinasjonNDX
	 */
	private void setDestinasjon(int ruteNDX, int destinasjonNDX) {
		Rute aktuellRute = finnRute(ruteNDX);
		Rute foererTil = finnRute(destinasjonNDX);
		aktuellRute.setDestinasjon(foererTil);
		String beskrivelse = (destinasjonNDX > ruteNDX) ? "Stige" : "Slange";
		aktuellRute.setType(beskrivelse);
	}

	/**
	 * Setter destinasjonen for stigene og slangene i spillet.
	 */
	private void setDestinasjonForAlle() {

		/** stige */
		setDestinasjon(2, 38);
		setDestinasjon(4, 14);
		setDestinasjon(8, 31);
		setDestinasjon(21, 42);
		setDestinasjon(28, 84);
		setDestinasjon(36, 44);
		setDestinasjon(51, 67);
		setDestinasjon(71, 91);
		setDestinasjon(80, 100);

		/** slange */
		setDestinasjon(98, 78);
		setDestinasjon(95, 75);
		setDestinasjon(92, 73);
		setDestinasjon(87, 24);
		setDestinasjon(64, 60);
		setDestinasjon(62, 18);
		setDestinasjon(56, 53);
		setDestinasjon(49, 11);
		setDestinasjon(47, 26);
		setDestinasjon(16, 6);
	}

	public List<Rute> getRuter() {
		return ruter;
	}

	public void setRuter(List<Rute> ruter) {
		this.ruter = ruter;
	}

}
