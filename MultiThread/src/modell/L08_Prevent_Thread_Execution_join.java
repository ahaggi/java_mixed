package modell;


/**join()
 *  t1 and t2 are 2 threads , if t1 wants to wait until  t2 is completed then t1 has to call t2.join() and t1 will be in waiting state until t2 is completed. 
 *  after t2 is completed then t1 can continue it's execution.
 * This implementation uses a loop of this.wait calls conditioned on this.isAlive. As a thread terminates the this.notifyAll method is invoked. It is recommended that applications not use wait, notify, or notifyAll on Thread instances.
 * */

/**join(Long ms)
 *  t1 and t2 are 2 threads , if t1 wants to wait until t2 is completed within a period of time then t1 has to call t2.join(Long ms) and t1 will be in waiting state until t2 is completed or the time is up. 
 *  if t2 is completed or the time is up then t1 can continue it's execution.
 * */

/**join(long millis, int nanos) throws InterruptedException
 * legg merke til at millis er long mens nano er int. Grunnen er nanos har grense f eks 2 ms og 999 nano, i tilfellet vi øker nano med 1 det vil være 3 ms oooo ns

 * Waits at most millis milliseconds plus nanos nanoseconds for this thread to die.
*/


/**Every join method throws interrupter exception which is checker exception hence we handle this exception either by using try catch or throws exception , otherwise we will get compiling   error*/
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

// Another Example:
//	 class MyThread extends Thread
//	 {
//	 	public void run()
//	 	{
//	 		for(int i=0;i<5;i++)
//	 		{
//	 			System.out.println("Sita Thread");
//	 			try
//	 			{
//	 				Thread.sleep(2000);
//	 			}
//	 			catch (InterruptedException e){}
//	 		}
//	 	}
//	 }
//	 class ThreadJoinDemo
//	 {
//	 	public static void main(String[] args)throws InterruptedException
//	 	{
//	 		MyThread t=new MyThread();
//	 		t.start();
//	 		//t.join();	//--->1
//	 		for(int i=0;i<5;i++)
//	 		{
//	 			System.out.println("Rama Thread");
//	 		}
//	 	}
//	 }
//
//	     If we are commenting line 1 then both Threads will be executed simultaneously and we can't expect exact execution order.
//	     If we are not commenting line 1 then main Thread will wait until completing child Thread in this the output is sita Thread 5 times followed by Rama Thread 5 times.
