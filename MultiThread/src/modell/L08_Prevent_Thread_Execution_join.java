package modell;


/**join()
 *  t1 and t2 are 2 threads , if t1 wants to wait until  t2 is completed then t1 has to call t2.join() and t1 will be in waiting state until t2 is completed. 
 *  after t2 is completed then t1 can continue it's execution.
 * */

/**join(Long ms)
 *  t1 and t2 are 2 threads , if t1 wants to wait until t2 is completed within a period of time then t1 has to call t2.join(Long ms) and t1 will be in waiting state until t2 is completed or the time is up. 
 *  if t2 is completed or the time is up then t1 can continue it's execution.
 * */


public class L08_Prevent_Thread_Execution_join{
	
	public static void main(String[] args) throws Exception{
		
	 Thread t = new Thread(new JoinDemo());
		   // this will call run() function
		   t.start();
		   // waits for this thread to die
		   t.join();
		   
		   System.out.print(t.getName());
		   //checks if this thread is alive
		   System.out.println(", status = " + t.isAlive());
		   
	}
}


 class JoinDemo implements Runnable {

	   public void run() {
	   
	   Thread t = Thread.currentThread();
	   System.out.print(t.getName());
	   //checks if this thread is alive
	   System.out.println(", status = " + t.isAlive());
	   }

	  
	} 

