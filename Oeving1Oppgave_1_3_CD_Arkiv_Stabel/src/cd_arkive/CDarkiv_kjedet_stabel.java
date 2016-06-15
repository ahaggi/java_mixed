package cd_arkive;

import easyIO.*;
import modell.CD;
import modell.CDarkivADT;
import modell.Sjanger;


public class  CDarkiv_kjedet_stabel  implements CDarkivADT{

	/*
	 * Data:
• En samling CD-er
Operasjoner:
• Legge inn en ny CD. Det er frivillig om du vil teste på om den ligger der fra før.
• Slette en CD
• Søker og henter CD-er med en gitt delstreng i tittelen
• Søker og henter artister med en gitt delstreng i tittelen
• Henter antall CD-er
	 */

	private LinearNode<CD> start;
	private int antall;
	In tastatur = new In();
	Out skjerm = new Out();

	/**
	 * 	Oppretter en tom kjede
	 */
	public CDarkiv_kjedet_stabel(){
		start=null;
		antall=0;
	}


	public CD[] hentCdTabell() {
		CD []cd =new CD[antall];
		LinearNode<CD> denne = start;
		for (int i = 0; i < cd.length; i++) {
			cd[i]=denne.getElement();
			denne = denne.getNeste();
		}
		return cd;//TODO tom tabell
	}

	public void leggTilCd(CD nyCD){
		if(nyCD!=null) {

			if(finnCD_nr(nyCD.getCdNumer())==null) {//sjekker om nyCD ikke finnes fra før
				LinearNode<CD> ny = new LinearNode<CD>(nyCD);
				ny.setNeste(start);
				start = ny;
//				System.out.println(start.getElement().getCdNumer());
				antall++;
			}else{
				skjerm.outln("Feil melding: CD Nr("+nyCD.getCdNumer()+"), er registrert fra før");
			}//end if else finnes
		}else{
			skjerm.outln("Feil melding: oppgitt opplysninger ble ikke registrert");
		}//end if else null
	}

	public boolean slettCd(int cdNr){
		boolean funnet = false;
		LinearNode<CD> forrige = null;
		LinearNode<CD> denne = start;
		LinearNode<CD> cdNode = finnCD_nr(cdNr);
		if (cdNode!=null) {
			while (denne != null && !funnet){
				if (cdNode.getElement().equals (denne.getElement())){//ikke node equals element men en node.getement equals element  
					funnet = true;
				}else{
					forrige = denne;
					denne = denne.getNeste();
				}
			}
			if (antall == 1) {
				start = null;
			}else if (start.equals (denne)){
				start = denne.getNeste();
			}else{
				forrige.setNeste(denne.getNeste());//hvis denne er det siste nodet, kan denne.getneste() bli null 
			}
			antall--;
		}//end if cdNode !=null
		return funnet;
	}

	public CD [] sokTittel(String cdTl){

		CD hjelpeCD[]=null;
		int resAntall=0;

		LinearNode<CD> denne = start;
		while (denne != null) {
			if (denne.getElement().getCdTittle().contains(cdTl.trim())) {
				resAntall++;
			}
			denne = denne.getNeste();
		}
		if (resAntall==0) {
			return hjelpeCD;//retunerer tom tabell
		}else{
			hjelpeCD=new CD[resAntall];
			int resIndx=0;
			denne = start;
			while (denne != null) {
				if (denne.getElement().getCdTittle().contains(cdTl.trim())) {
					hjelpeCD[resIndx]=denne.getElement();
					resIndx++;
				}
				denne = denne.getNeste();
			}
			return hjelpeCD;//retunerer full tabell
		}//end else if resAntall ==0

	}

	public CD [] sokArtist(String artNv){

		CD hjelpeCD[]=null;
		int resAntall=0;

		LinearNode<CD> denne = start;
		while (denne != null) {
			if (denne.getElement().getArtistNavn().contains(artNv.trim())) {
				resAntall++;
			}
			denne = denne.getNeste();
		}
		if (resAntall==0) {
			return hjelpeCD;//retunerer tom tabell
		}else{
			hjelpeCD=new CD[resAntall];
			int resIndx=0;
			denne = start;
			while (denne != null) {
				if (denne.getElement().getArtistNavn().contains(artNv.trim())) {
					hjelpeCD[resIndx]=denne.getElement();
					resIndx++;
				}
				denne = denne.getNeste();
			}
			return hjelpeCD;//retunerer full tabell
		}//end else if resAntall ==0

	}

	public int hentAntall(){return antall;}


	public int hentAntall(Sjanger sjanger){
		int resAntall=0;
		LinearNode<CD> denne = start;
		while (denne != null) {
			if (denne.getElement().getSjange().equals(sjanger)) {
				resAntall++;
			}
			denne = denne.getNeste();
		}
		return resAntall;
	}

	public int hentAntall(String sjanger){
		int resAntall=0;
		LinearNode<CD> denne = start;
		while (denne != null) {
			if (denne.getElement().getSjange().equals(sjanger.trim())) {
				resAntall++;
			}
			denne = denne.getNeste();
		}
		return resAntall;
	}



	//1 hjelpe metoder

	
	public LinearNode<CD> finnCD_nr(int cdNr){
		LinearNode<CD> denne = start;
		while (denne != null && (denne.getElement().getCdNumer()!=cdNr)) {
			denne = denne.getNeste();
		}
		return (denne);	
	}

}
