package modell;

public abstract class Ansatte {
	String navn;
	double timeLoen;
	double bonusFaktor;

	public Ansatte(String n) {
		navn=n;
	}

	public abstract double  beregnLoenn();

}


class DT_ansatte extends Ansatte{

	public DT_ansatte(String n, double t) {
		super(n);
		timeLoen=t;
		bonusFaktor=1.05;
	}

	@Override
	public double beregnLoenn() {
		return timeLoen * bonusFaktor;
	}

}


interface KredittVerdig{
	double beregnKreditt();
}


class HT_ansatte extends Ansatte implements KredittVerdig{
	double ansiennitetsFaktor;

	public HT_ansatte(String n, double t) {
		super(n);
		timeLoen=t;
		bonusFaktor=1.1;
		ansiennitetsFaktor=100;
	}

	@Override
	public double beregnKreditt() {
		return timeLoen + 
				ansiennitetsFaktor;
	}

	@Override
	public double beregnLoenn() {
		return timeLoen * bonusFaktor + ansiennitetsFaktor;
	}

}