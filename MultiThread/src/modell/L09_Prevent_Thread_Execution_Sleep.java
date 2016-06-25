package modell;

/**If a Thread don't want to perform any operation for a particular amount of time then we should go for sleep() method.

1-public static native void sleep(long ms) throws InterruptedException NOT IMPLEMENTED IN JAVA
2-public static void sleep(long ms,int ns)throws InterruptedException      IMPLEMENTED IN JAVAIMPLEMENTED IN JAVA
*/

public class L09_Prevent_Thread_Execution_Sleep{

	public static void main(String[] args) throws Exception{
		System.out.println("M");
		Thread.sleep(3000);
		System.out.println("E");
		Thread.sleep(3000);
		System.out.println("G");
		Thread.sleep(3000);
		System.out.println("A");
	}
}

