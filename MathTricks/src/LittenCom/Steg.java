package LittenCom;

public class Steg{
	int x;
	int y;
 	Steg neste;
	Steg forrige;
	boolean[][] daVaerndeliste;
	
	Steg(int x, int y ) {
		this.x = x;
		this.y = y;
 	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
 	public Steg getNeste() {
		return neste;
	}
	public void setNeste(Steg neste) {
 		this.neste = neste;
 	}
	public void foranderNeste(Steg neste) {
		System.out.println(neste +" ");
		this.x = neste.getForrige().getX();
		this.y = neste.getForrige().getY();
		this.neste = neste;
//		forender x og y
	}
	public Steg getForrige() {
		return forrige;
	}
	public void setForrige(Steg forrige) {
		this.forrige = forrige;
	}
	public boolean[][] getDaVaerndeliste() {
		return daVaerndeliste;
	}
	public void setDaVaerndeliste(boolean[][] naaVaerndeliste) {
		this.daVaerndeliste = naaVaerndeliste;
	}
	
	
	
	
}
