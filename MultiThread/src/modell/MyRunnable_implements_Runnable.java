package modell;
	
	/**OBS Runnable har kun en metode "run", dvs at den ikke har "start" metode.
	 * Derfor må vi bruke Thread klasse også*/

	/**implements Runnable is recommended because we dont lose inheritance benefits*/
	class MyRunnable_implements_Runnable implements Runnable { //OBS implements
		@Override
		public void run(){
			Thread.currentThread().setName("child thread");
			
			for (int i = 0; i < 10; i++) {
				System.out.println("This line executed by: "+Thread.currentThread().getName()+" , priority is "+Thread.currentThread().getPriority());
			}
		}
	}
