package LittenCom;

public class Koe{
	private Steg denne;
	private Steg topp;
	private int antall;

	public Koe() {
		super();
		denne=null;
		antall=0;
	}


	void leggTil(Steg s){
		if (denne!=null) {
			denne.setNeste(s);
		}
		topp = (topp == null)? s : topp;
		s.setForrige(denne);
		denne =s;
		antall++;	
	}
	
	
	public Steg getDenne() {
		return denne;
	}
	public Steg getTopp() {
		return topp;
	}
	public void setDenne(Steg denne) {
		this.denne = denne;
	}
 }


