package modell;

public class L03_brukAvRunnable  {

	public static void main(String[] args) {
		
		MyRunnable_implements_Runnable r = new MyRunnable_implements_Runnable();	
		
		Thread t1=new Thread(); 
		Thread t2=new Thread(r); //OBS MÅ sende r som parameter
		
		/**les nøy gjennom de ulike tilfeller */
		
		t1.start();//new thread  + execute Thread class start metode som er tomt.
		t1.run();//no new thread +Thread class run method som er tomt.
		t2.start();//new thread  + execute Thread class start + MyRunnable run method.
		t2.run();//no new thread + MyRunnable run method.
		r.run(); //no new thread + MyRunnable run method.
	//	r.start();// MyRunnable har ikke Start metode.
		
	

	}
	
}
