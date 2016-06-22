package modell;

public class L06_TRaads_Prioritet  {
	
	/**Threads prioritets er fra 10 til 1. ikke til 0 som indeks*/
	
	/**OBS OBS 10 har mest prioritet, 1 har minst prioritet */
	
	/**Thread-scheduler vil bruke tråders-prioritet*/
	
	/**Main-Thread default priority is 5, for others default priority will be inherited from parent-thread dvs det samme prioritet*/

	/**parent thread is not parent class, parent-thread is the thread which call the child-thread*/
	
	/**Ikke alle os støtter for prioritets https://youtu.be/DpM_wER1gAY?t=56m16s*/
	
	public static void main(String[] args) {
		
		System.out.println(Thread.MIN_PRIORITY);// = 1
		System.out.println(Thread.NORM_PRIORITY);// = 5
		System.out.println(Thread.MAX_PRIORITY);// = 10
		
//		Thread.currentThread().setPriority(7);
				
		Mythread_extends_Thread t1 = new Mythread_extends_Thread();
		t1.setPriority(10);
		
//		t1.setPriority(11); //vil throw Exception, allowed value 10 to 1
		t1.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("This line executed by: "+Thread.currentThread().getName());			
		}

	}
	
}

