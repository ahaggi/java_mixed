package modell;

class SkrivUt{
	public  static synchronized void skrivChar(){
		for (int i = 0; i < 10; i++) {
			System.out.print((char)(i+65));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("i got interrupted");
			}
			
		}
	}
	public   synchronized void skrivNr(){
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("i got interrupted");
			}
			
		}
	}
}

class ThreadDemo4 implements Runnable {
	private SkrivUt sk;
	private String name;

	public ThreadDemo4(SkrivUt sk,String name){
		this.sk=sk;
		this.name=name;
	}

	public void run(){
		sk.skrivNr();
		}
} 

class ThreadDemo5 implements Runnable {
	private SkrivUt sk;
	private String name;

	public ThreadDemo5(SkrivUt sk,String name){
		this.sk=sk;
		this.name=name;
	}

	public void run(){
		sk.skrivChar();
		}
} 

public class L12_synchronization_Static_Methods2{

	public static void main(String[] args) throws Exception{
		SkrivUt sk=new SkrivUt();
		Thread t1 = new Thread(new ThreadDemo4(sk,"dhoni")); //en traad vil ha objekt lock
		Thread t2 = new Thread(new ThreadDemo5(sk,"yuvaraj"));//en traad vil ha klass-level lock
															   //så begge vil operere uavhengig og samtidig
															   //selv om den er det samme "sk" objekt
		t1.start();
		t2.start();
	}
}

	