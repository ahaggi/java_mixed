package modell;
/**
 * Synchronized block: 	object-tilstands oppdaterende oprasjoner(f eks .getName()) kan ha Synchronized block
 * Men read oprasjoner (f eks .setName()) kan være uten for Synchronized block

 1-If very few lines of the code required synchronization then it's never recommended 
 	to declare entire method as synchronized we have to enclose those few lines of 
 	the code with in synchronized block.
 2-The main advantage of synchronized block over synchronized method is it reduces waiting 
   time of Thread and improves performance of the system.

	
Example 1: 
To get lock of current object we can declare synchronized block as follows.
If Thread got lock of current object then only it is allowed to execute this block.
Synchronized(this){}

Example 2: 
To get the lock of a particular object 'b' we have to declare a synchronized block as follows.
If thread got lock of 'b' object then only it is allowed to execute this block.
Synchronized(b){}

Example 3: 
To get class level lock we have to declare synchronized block as follows.
If thread got class level lock of Display then only it allowed to execute this block.
Synchronized(Display.class){}

Note:As the argument to the synchronized block we can pass either object reference or ".class file" and we can't pass primitive values as argument [because lock concept is dependent only for objects and classes but not for primitives].
*/


public class L13_synchronization_Block{

}
