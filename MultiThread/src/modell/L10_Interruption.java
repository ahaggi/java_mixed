package modell;



/**
    1-Whenever we are calling interrupt() method we may not see the effect immediately, if the target Thread is in sleeping or waiting state it will be interrupted immediately.
    2-If the target Thread is not in sleeping or waiting state then interrupt call will wait until target Thread will enter into sleeping or waiting state. Once target Thread entered into sleeping or waiting state it will effect immediately.
 OBS 3-In its lifetime if the target Thread never entered into sleeping or waiting state then there is no impact of interrupt call simply interrupt call will be wasted.
*/

public class L10_Interruption{

		// If we are commenting line 1 then main Thread won't interrupt child Thread
		// and hence child Thread will be continued until its completion.
		// If we are not commenting line 1 then main Thread interrupts child Thread
		// and hence child Thread
	public static void main(String[] args) throws Exception{

		Thread t = new Thread(new ThreadInterruptDemo());
		t.start();
		t.interrupt();	//--->1  //OBS comenter ut denne linje
		System.out.println("end of main thread");
	}
}


class ThreadInterruptDemo implements Runnable {

	public void run() {
		try	{
			for(int i=0;i<5;i++){
				System.out.println("i am lazy Thread :"+i);
				Thread.sleep(2000);
			}
		}
		catch (InterruptedException e){
			System.out.println("i got interrupted");
		}
	}


} 
