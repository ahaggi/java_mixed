package no.hib.dat101;

import java.util.*;

import javax.persistence.*;

import no.hib.dat101.utsyn.Database;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
@Entity
@Table(schema = "slangeogstige")
public class Spill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer spillID;
	private String navn;

	@Transient
	private Terning terning;

	@OneToMany(mappedBy = "spill", cascade = CascadeType.ALL)
	private List<Spiller> spillere = new ArrayList<Spiller>();

	@OneToOne(mappedBy = "spill", cascade = CascadeType.ALL)
	private Brett brett;

	@OneToMany(mappedBy = "spill", cascade = CascadeType.ALL)
	private List<Brikke> brikker = new ArrayList<Brikke>();

	@Transient
	Database db = new Database(this);

	@Transient
	int loggCount = 1;

	@Transient
	Spill spill = this;

	@Transient
	public static int MILLISEKUND_TUR = 200;

	@Transient
	Spiller vinner = null;

	public Spill() {

	}

	/** konstruktør - med tidligere lagret brett og brikkere */
	public Spill(Brett brett, List<Brikke> brikker, int antallSpillere) {
		super();
		this.brett = brett;
		this.brikker = brikker;
		konstruktørHjelp(antallSpillere);
	}

	/** konstruktør for ø lage ny spillet */
	public Spill(int antallSpillere) {

		this.brett = new Brett(this);

		for (int i = 0; i < 4; i++) {
			Brikke brikke = new Brikke(i + 1, brett.finnRute(1), this);
			brikker.add(brikke);
		}

		konstruktørHjelp(antallSpillere);

	}

	/**
	 * Felles for begge konstruktørene.
	 * 
	 * @param antallSpillere
	 */
	private void konstruktørHjelp(int antallSpillere) {
		navn = "Slange og stige";

		Terning terning = new Terning();
		this.terning = terning;

		for (Brikke brikke : brikker) {
			brikke.setPlass(brett.finnRute(1));
		}

		for (int i = 0; i < antallSpillere; i++) {
			Spiller spiller = new Spiller(i + 1, brikker.get(i), this);
			spillere.add(spiller);
		}
	}

	/*************************************************************/

	/**
	 * Triller terning.
	 * 
	 * @return verdi
	 */
	public Integer trillTerning() {
		terning.trill();
		return terning.getVerdi();
	}

	/**
	 * Sjekker om spiller har vunnet.
	 * 
	 * @param spiller
	 * @return true om spiller har vunnet.
	 */
	public boolean harVunnet(Spiller spiller) {
		return (spiller.getBrikke().getPlass().getRuteId() == 100);
	}

	/**
	 * Starter spill med timer(jFrame).
	 */
	public void startSpill_timer() {
		spillere = getSpillere();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			// mø difinere vinner pekeren her
			int spillereNDX = 0;
			int antallSpillere = spillere.size();

			@Override
			public void run() {
				if (vinner != null) {
					String handling = ("Spilleren: " + vinner.getSpillerID() + " har vunnet");
					Logg logg = new Logg(loggCount++, vinner.getSpillerID(), spill.getSpillID(), 0,
							vinner.getBrikke().getPlass().getRuteId(), brett.finnRute(100).getRuteId(), handling);
//					db.persistLogg(logg);
//					db.close();

					timer.cancel();
					timer.purge();
					return;
				}
				Spiller spiller = spillere.get(spillereNDX);
				spillereNDX = (spillereNDX + 1) % antallSpillere;
				vinner = nyTur(spiller);
			}
		}, 0, MILLISEKUND_TUR);

	}

	/**
	 * Starter spillet(tekstgrensesnitt).
	 */
	public void startSpill() {
		spillere = getSpillere();
		int spillereNDX = 0;
		int antallSpillere = spillere.size();
		vinner = null;

		while (vinner == null) {
			Spiller spiller = spillere.get(spillereNDX);
			spillereNDX = (spillereNDX + 1) % antallSpillere;
			vinner = nyTur(spiller);
		}
		String handling = ("Spilleren: " + vinner.getSpillerID() + " har vunnet");
		Logg logg = new Logg(loggCount++, vinner.getSpillerID(), spill.getSpillID(), 0,
				vinner.getBrikke().getPlass().getRuteId(), brett.finnRute(100).getRuteId(), handling);
//		db.persistLogg(logg);
//		db.close();

	}

	/**
	 * Starter en ny tur.
	 * 
	 * @param spiller
	 * @return vinner
	 */
	private Spiller nyTur(Spiller spiller) {
		int terningVerdi = trillTerning();

		if (spiller.getPunish()) {
			if (terningVerdi == 6) {
				sjekkOmSeks(spiller, terningVerdi);
				spiller.setPunish(false);
			}

		} else {
			sjekkOmSeks(spiller, terningVerdi);
		}
		vinner = (harVunnet(spiller)) ? spiller : null;
		return vinner;
	}

	/**
	 * Sjekker om verdien pø terningen er seks ogsø flytt.
	 * 
	 * @param spiller
	 * @param terningVerdi
	 */
	private void sjekkOmSeks(Spiller spiller, int terningVerdi) {
		int terningVerdi2 = 0;
		int terningVerdi3 = 0;

		flytt(spiller, terningVerdi);
		if (terningVerdi == 6 && vinner == null) {
			terningVerdi2 = trillTerning();
			flytt(spiller, terningVerdi2);
			terningVerdi3 = (terningVerdi2 != 6) ? 0 : trillTerning();
		}

		if (terningVerdi3 == 6 && vinner == null) {
			spiller.setPunish(true);
			flyttTilEn(spiller);
		} else if (terningVerdi3 != 0) {
			flytt(spiller, terningVerdi3);
		}

	}

	/**
	 * Flytter brikke til spiller til rute nr 1.
	 */

	private void flyttTilEn(Spiller spiller) {
		String handling = ("Spilleren: " + spiller.getSpillerID() + " er flyttet til rute 1");

		Logg logg = new Logg(loggCount++, spiller.getSpillerID(), this.getSpillID(), 6,
				spiller.getBrikke().getPlass().getRuteId(), brett.finnRute(1).getRuteId(), handling);
//		db.persistLogg(logg);

		spiller.getBrikke().setPlass(brett.finnRute(1));
	}

	/**
	 * Flytter spilleren.
	 * 
	 * @param spiller
	 * @param terning
	 */
	private void flytt(Spiller spiller, Integer terning) {
		int tilRuteNDX = spiller.getBrikke().getPlass().getRuteId() + terning;
		Rute tilRute = spiller.getBrett().finnRute(tilRuteNDX);
		Rute fraRute = spiller.getBrikke().getPlass();
		String handling = "";
		// først flytt brikken
		if (tilRute != null) {
			spiller.flytt(tilRute);
			handling = ("Spilleren: " + spiller.getSpillerID() + " har flyttet til rute "
					+ spiller.getBrikke().getPlass().getRuteId());

		} else {
			handling = ("Spilleren: " + spiller.getSpillerID() + " er støende pø det samme plass");
			tilRute = spiller.getBrikke().getPlass(); // forandrer null til
														// eksisterende plass
		}

		Logg logg = new Logg(loggCount++, spiller.getSpillerID(), this.getSpillID(), terning, fraRute.getRuteId(),
				tilRute.getRuteId(), handling);
//		db.persistLogg(logg);

		// sjekker om nyrute fører til et annet rute
		Rute foererTil = tilRute.getDestinasjon();
		if (!tilRute.equals(foererTil))
			utfoerStigeEllerSlange(spiller, tilRute, foererTil);
		vinner = (harVunnet(spiller)) ? spiller : null;
	}

	/**
	 * Spilleren klatrer opp en stige eller sklir ned en slange.
	 * 
	 * @param spiller
	 * @param fraRute
	 * @param foererTil
	 */
	private void utfoerStigeEllerSlange(Spiller spiller, Rute fraRute, Rute foererTil) {
		String handling = "";
		spiller.flytt(foererTil);

		if (foererTil.getRuteId() > fraRute.getRuteId()) {
			handling = ("Spilleren: " + spiller.getSpillerID() + " har klatret opp stigen til rute "
					+ foererTil.getRuteId());
		} else {
			handling = ("Spilleren: " + spiller.getSpillerID() + " har sklidd ned slangen til rute "
					+ foererTil.getRuteId());
		}

		Logg logg = new Logg(loggCount++, spiller.getSpillerID(), this.getSpillID(), 0, fraRute.getRuteId(),
				foererTil.getRuteId(), handling);
//		db.persistLogg(logg);
	}

	public Integer getSpillID() {
		return spillID;
	}

	public void setSpillID(Integer spillID) {
		this.spillID = spillID;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Terning getTerning() {
		return terning;
	}

	public void setTerning(Terning terning) {
		this.terning = terning;
	}

	public List<Spiller> getSpillere() {
		return spillere;
	}

	public void setSpillere(List<Spiller> spillere) {
		this.spillere = spillere;
	}

	public Brett getBrett() {
		return brett;
	}

	public void setBrett(Brett brett) {
		this.brett = brett;
	}

	public List<Brikke> getBrikker() {
		return brikker;
	}

	public void setBrikker(List<Brikke> brikker) {
		this.brikker = brikker;
	}

	@Override
	public String toString() {
		return "Spill [spillID=" + spillID + ", navn=" + navn + ", terning=" + terning + ", spillere=" + spillere
				+ ", brett=" + brett + ", brikker=" + brikker + "]";
	}

}
