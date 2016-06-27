package modell;


class ThreadDemo3 implements Runnable {
	private Display2 d;
	private String name;

	public ThreadDemo3(Display2 d,String name){
		this.d=d;
		this.name=name;
	}

	public void run(){
		d.wish(name); //vet at den er static, men skriver det sånn for forklaring av bruk static sync metoder
	}

} 
public class L12_synchronization_Static_Methods_and_Non_Static_methos{

	public static void main(String[] args) throws Exception{
		//selv om det er 2 ulike objekter, vil den andre traad vente opptill 1. traad release the class lock eller det motsatte
		Display2 d1=new Display2();// 2 ulike  object
		Display2 d2=new Display2();
		Thread t1 = new Thread(new ThreadDemo3(d1,"dhoni"));
		Thread t2 = new Thread(new ThreadDemo3(d2,"yuvaraj"));// 2 ulike  object
		t1.start();
		t2.start();
	}
}

	