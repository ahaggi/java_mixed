package modell;

import easyIO.In;
import easyIO.Out;

public class CD {

	//	Et entydig CD-nummer (heltall, ingen krav om bestemte nummerserier e.l.)
	//	• Navn på artist/gruppe
	//	• Tittel på CD
	//	• År for lansering (heltall)
	//	• Sjanger (pop, rock, opera, klassisk)// av type enum (best) eller type String. Se på slutten av oppgaven om enum.
	//	• Plateselskap

	private int cdNumer;
	private String artistNavn;
	private String cdTittle;
	private int lansAar;
	private Sjanger sjange;
	private String plateselskap;

	private In tastatur = new In();
	private Out skjerm = new Out();

	public CD(){
		cdNumer=0;
		artistNavn="";
		cdTittle="";
		lansAar=0;
		sjange =null;
		plateselskap="";
	}

	public CD(int cdn, String artn,String cdt, int lAar, Sjanger sj,String ps){

		cdNumer=cdn;
		artistNavn=artn;
		cdTittle=cdt;
		lansAar=lAar;
		sjange =sj;
		plateselskap=ps;
	}

	public int getCdNumer() {
		return cdNumer;
	}

	public void setCdNumer(int cdNumer) {
		this.cdNumer = cdNumer;
	}

	public String getArtistNavn() {
		return artistNavn;
	}

	public void setArtistNavn(String artistNavn) {
		this.artistNavn = artistNavn;
	}

	public String getCdTittle() {
		return cdTittle;
	}

	public void setCdTittle(String cdTittle) {
		this.cdTittle = cdTittle;
	}

	public int getLansAar() {
		return lansAar;
	}

	public void setLansAar(int lansAar) {
		this.lansAar = lansAar;
	}

	public Sjanger getSjange() {
		return sjange;
	}

	public void setSjange(Sjanger sjange) {
		this.sjange = sjange;
	}

	public String getPlateselskap() {
		return plateselskap;
	}

	public void setPlateselskap(String plateselskap) {
		this.plateselskap = plateselskap;
	}



}
