package modell;

public class L3_brukAvRunnable2  {

	public static void main(String[] args) {
		
		Mythread2 r = new Mythread2();	
		
		Thread t1=new Thread(); 
		Thread t2=new Thread(r); //OBS MÅ sende r som parameter
		
		/**les nøy gjennom de ulike tilfeller */
		
		t1.start();//new thread  + execute Thread class start metode som er tomt.
		t1.run();//no new thread +Thread class run method som er tomt.
		t2.start();//new thread  + execute Thread class start + Mythread2 run method.
		t2.run();//no new thread + Mythread2 run method.
		r.run(); //no new thread + Mythread2 run method.
	//	r.start();// Mythread2 har ikke Start metode.
		
		for (int i = 0; i < 10; i++) {
			System.out.println("main thread00000000000000000000000000000000000000");			
		}

	}
	
}

/**OBS Runnable har kun en metode "run", dvs at den ikke har "start" metode.
 * Derfor må vi bruke Thread klasse også*/

/**implements Runnable is recommended because we dont lose inheritance benefits*/

class Mythread2 implements Runnable { //OBS implements
	@Override
	public void run(){
		for (int i = 0; i < 10; i++) {
			System.out.println("child thread--------------------------------------");
		}
	}
}