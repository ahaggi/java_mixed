package main;

public class Memoization {

	public static void main(String[] args) {
		int n = 6; 
		int[] res = new int[1000];

	    System.out.println(fib(n , res)); 
	}

	static int fib(int n, int[] memorizedRes) {

		// base case
		if (n <= 1)
			return n;

		// if fib(n) has already
		// been computed we do not
		// do further recursive
		// calls and hence reduce
		// the number of repeated
		// work
		if (memorizedRes[n] != 0)
			return memorizedRes[n];

		else {

			// store the computed value
			// of fib(n) in an array
			// term at index n to so that
			// it does not needs to be
			// precomputed again
			
			System.out.println("calculate the term" + n);
			memorizedRes[n] = fib(n - 1,  memorizedRes) + fib(n - 2,  memorizedRes);

			return memorizedRes[n];
		}
	}

}
