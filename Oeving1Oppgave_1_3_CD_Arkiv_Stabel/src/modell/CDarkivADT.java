package modell;

public interface CDarkivADT {
	/** 
	 * Returnere en tabell av CD-er
	 @return CD[]
	 **/
	CD[] hentCdTabell();


	/** Legger til en ny CD
	 * @param nyCd CD som skal legges til**/
	void leggTilCd(CD nyCd);

	/** Sletter en CD hvis den fins
	 * @param cdNr int
	 * @return boolean
	 * **/
	boolean slettCd(int cdNr);

	/** Søker og henter CD-er med en gitt delstreng
	 * @param String en gitt tittel
	 * @return CD[] tabell med CD-er  
	 **/
	CD[] sokTittel(String delstreng);

	/** Søker og henter artister med en gitt delstreng
	 * @param delstreng navn på artisten / gruppen som skal søkes
	 * @return CD[] tabell med CD-er  
	 * */
	CD[] sokArtist(String delstreng);

	/** Henter antall CD-er
	 * @return int tallet på registererte CD-er**/
	int hentAntall(); 

	/** Henter antall CD-er for en gitt sjanger
	 * @param sjanger en gitt sjanger **/
	int hentAntall(Sjanger sjanger);

	/** Henter antall CD-er for en gitt sjanger
	 * @param sjanger en gitt sjanger **/
	int hentAntall(String sjanger);

}
