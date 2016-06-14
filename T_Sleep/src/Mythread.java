public class Mythread implements Runnable{
	
	public synchronized void goo(){


System.out.println("Before Wait");

try {
	wait();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

System.out.println("After Wait");


}


public synchronized void foo(){

System.out.println("Before Notify");

notify();

System.out.println("After Notify");

}


@Override
public void run() {
	// TODO Auto-generated method stub
goo();
foo();
}

}