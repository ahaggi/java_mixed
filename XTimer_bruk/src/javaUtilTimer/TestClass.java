package javaUtilTimer;
import java.util.Timer;
import java.util.TimerTask;

public class TestClass {
    public static long myLong = 1000;
     
    public static void main(String[] args) {
    	
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        	int i =0;
            @Override
            public void run() {
            	 if (i >= 5) {
                     timer.cancel();
                     timer.purge();
                     return;
                 }
            	 i++;
                System.out.println("111");;
            }
        },0, myLong);
    }

    public void doStuff(){
        //do stuff here
    }
}