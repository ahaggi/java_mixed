package modell;

public class L02_brukAvRunnable  {

	public static void main(String[] args) {
		
		Mythread1 r = new Mythread1();	
		Thread t1=new Thread(r); //OBS MÅ sende r som parameter
		t1.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("main thread00000000000000000000000000000000000000");			
		}

	}
	
}

/**OBS Runnable har kun en metode "run", dvs at den ikke har "start" metode.
 * Derfor må vi bruke Thread klasse også*/

/**implements Runnable is recommended because we dont lose inheritance benefits*/
class Mythread1 implements Runnable { //OBS implements
	@Override
	public void run(){
		for (int i = 0; i < 10; i++) {
			System.out.println("child thread--------------------------------------");
		}
	}
}