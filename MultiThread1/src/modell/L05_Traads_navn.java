package modell;


public class L05_Traads_navn{

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName());

		Mythread_extends_Thread t1 = new Mythread_extends_Thread();
		System.out.println(t1.getName());
		
		Thread.currentThread().setName("main_nyNvn");
		System.out.println(10/0);//legg merke til main thread sin navn i exception (Exception in thread "main_nyNvn")

	}
	
	

}
