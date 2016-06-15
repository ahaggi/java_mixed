package modell;
public enum Sjanger {
	ROCK(0), POP(1), OPERA(2), CLASSIC(3),UKJENT(4); 
	private int nr; 
	private Sjanger(int n) { //Konstruktør
		nr = n;
	} 
	public int getNr() {
		return nr;
	}
	
	
	public static Sjanger finnSjanger(int n) {
		Sjanger sjang = UKJENT;
		for (Sjanger sj : Sjanger.values()) {
			if (sj.ordinal() == n) {
				sjang = sj;
				break;
			}
		}
		return sjang;
	} //metode
	
	
	
	public static Sjanger finnSjanger(String navn){
		Sjanger sjang = UKJENT;
		for (Sjanger sj : Sjanger. values()){
			if(sj. toString(). equals(navn. toUpperCase())){
				sjang = sj;
				break;
			}
		}
		return sjang;
	}//metode
	
	
}//class

