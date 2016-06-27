package modell;
/**
 * 1-Two Threads can communicate with each other by using wait(), notify() and notifyAll() methods.
 * 2-The Thread which is require Object-updation , has to call wait() method on the required object then 
 *   immediately the Thread will entered into waiting state.
 * 3-The Thread which is performing updation of object, it is responsible to give notification by 
 *   calling notify() method.
 * 4-After getting notification the waiting Thread will get those updations.
 * 
 * 5-wait(), notify() and notifyAll() methods are available in Object class but not in Thread class 
 *   because Thread can call these methods on any common object.
 * 6-To call wait(), notify() and notifyAll() methods compulsory the current Thread should be owner
 *   of that object
 *                 i.e., current Thread should has lock of that object
 *                 i.e., current Thread should be in synchronized area. Hence we can call wait(), 
 *                       notify() and notifyAll() methods only from synchronized area otherwise we
 *                       will get runtime exception saying IllegalMonitorStateException.
 *                        
 * 7-Once a Thread calls wait() method on the given object 1st it releases the lock of that object 
 *   immediately and entered into waiting state.
 * 8-Once a Thread calls notify() (or) notifyAll() methods it releases the lock of that object but 
 *   may not immediately.
 * 9-Except these (wait(),notify(),notifyAll()) methods there is no other place(method) where the 
 *   "lock releasing" will be happen. 
 * 
*/
class ThreadWaiting implements Runnable {
	int total =0;

	public void run(){

		synchronized(this){
			System.out.println("child thread starts calcuation");//step-2
			for(int i=0;i<=100;i++)
				total=total+i;

			System.out.println("child thread giving notification call");//step-3
			this.notify();
		}	
	}

} 

public class L14_Inter_Thread_communication_wait_notify_notifyAll{

	public static void main(String[] args) throws InterruptedException{
		ThreadWaiting wt = new ThreadWaiting();
		Thread t = new Thread(wt);
		t.start();
		
		synchronized(wt){ //Hvis vi bruker wait uten for sych , vil vi få IllegalMonitorStateException
			System.out.println("main Thread calling wait() method");//step-1
			wt.wait();
			System.out.println("main Thread got notification call");//step-4
			System.out.println("Total is: "+wt.total);
		}
	}
}