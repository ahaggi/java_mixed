package modell;

/**Class level lock:

    1-Every class in java has a unique lock. If a Thread wants to execute a static 
      synchronized method then it required class level lock.
    2-Once a Thread got class level lock then it is allow to execute any static synchronized 
      method of that class.
    3-While a Thread executing any static synchronized method the remaining Threads are not 
      allow to execute any static synchronized method of that class simultaneously.
    4-But remaining Threads are allowed to execute normal synchronized methods, 
      normal static methods, and normal instance methods simultaneously.
    5-Class level lock and object lock both are different and there is no relationship between these two.
    
 */
/*Obs
 * Hvis en traad har klass level lock(static syn metoder), blir ikke andre traad forhindret til å utfoere
 * andre syn metoder(ikke static) på det samme objektet

*/


class Display2{


	public synchronized static void wish(String name)	{
		for(int i=0;i<5;i++){
			System.out.print("good morning:");

			try	{
				Thread.sleep(1000);	
			}
			catch (InterruptedException e)
			{
				System.out.println("i got interrupted");
			}

			System.out.println(name);
		}
	}
}

class ThreadDemo2 implements Runnable {
	private String name;

	public ThreadDemo2(String name){
		this.name=name;
	}

	public void run(){
		Display2.wish(name);
	}

} 
public class L12_synchronization_Static_Methods{

	public static void main(String[] args) throws Exception{
		
		//selv om det er 2 ulike objekter, vil den andre traad vente opptill 1. traad release the class lock eller det motsatte
		Thread t1 = new Thread(new ThreadDemo2("dhoni"));
		Thread t2 = new Thread(new ThreadDemo2("yuvaraj"));
		t1.start();
		t2.start();
	}
}
