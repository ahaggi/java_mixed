package modell;

public class L07_Prevent_Thread_Execution_yield{
	public static void main(String[] args) {
		new YieldDemo("Thread 1");
		new YieldDemo("Thread 2");
		new YieldDemo("Thread 3");
	}
}




	/**yield()
	 * current executing Thread will pause it's executions to give the chance for waiting threads which have same priority
	 * If there is no waiting threads or if all waiting threads have lower priority then current executing Thread can continue*/
	/**Thread tx  is pausing to give chance for t1,t2,t3,t4 and t5 which all have a priority of 5, assume t3 got the chance, the next thread to execute will be t5,t2,t1,t4 OR tx  */

	/**Ikke alle os støtter for prioritets https://youtu.be/DpM_wER1gAY?t=56m16s
	 * 										https://youtu.be/AZuwWOURi2Y?t=51m12s*/
	//  public static native void yield()	NATIVE It marks a method, 
	//	that it will be implemented in other languages, not in Java. 
	//	It works together with JNI (Java Native Interface). 
	//	Native methods were used in the past to write performance critical sections 
	//	but with Java getting faster this is now less common. 
	//	Native methods are currently needed when You need to call a library from Java that is written 
	//	in other language. You need to access system or hardware resources that are only reachable
	//	from the other language (typically C). 
	//	Actually, many system functions that interact with real computer (disk and network IO, for instance) 
	//		can only do this because they call native code.




class YieldDemo implements Runnable {
	Thread t;
	
	YieldDemo(String str) {
		t = new Thread(this, str);
		t.start();
	}

	public void run()  {

		for (int i = 0; i < 20; i++) {
			// yields control to another thread every 5 iterations
			if ((i % 5) == 0) {
				System.out.println(Thread.currentThread().getName() + "	yielding control...");
				/* causes the currently executing thread object to temporarily  pause and allow other threads to execute */
				Thread.yield();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("i got interrupted");
				}

			}
		}

		System.out.println(Thread.currentThread().getName() + " has finished executing.");
	}

	
}	




/**join()
 *  t1 and t2 are 2 threads , if t1 wants to wait until  t2 is completed then t1 has to call t2.join() and t1 will be in waiting state until t2 is completed. 
 *  after t2 is completed then t1 can continue it's execution.
 * */

/**join(Long ms)
 *  t1 and t2 are 2 threads , if t1 wants to wait until t2 is completed within a period of time then t1 has to call t2.join(Long ms) and t1 will be in waiting state until t2 is completed or the time is up. 
 *  if t2 is completed or the time is up then t1 can continue it's execution.
 * */

