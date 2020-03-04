package main;

import java.util.Arrays;

public class LongestIncreasingSubarray {


public static void main(String[] args) {
	int arr[] = { 5, 6, 3, 5, 7, 8, 3, 4, 9, 1, 2 };
	int n = arr.length;
	int res[] = lenOfLongIncSubArr(arr, n);
	System.out.println("Length = " + res.length);

	for (int i = 0; i < res.length; i++) {
		System.out.println(res[i]);
	}

}

public static int[] lenOfLongIncSubArr(int arr[], int n) {

	int maxLength = 1, len = 1;
	int subArrIndex = 0;

	for (int i = 1; i < n; i++) {

		if (arr[i] > arr[i - 1])
			len++;

		else {

			if (maxLength < len) {
				maxLength = len;
				subArrIndex = i - maxLength;

			}

			len = 1;
		}
	}

	if (maxLength < len) {
		maxLength = len;
		subArrIndex = n - maxLength;

	}

	return Arrays.copyOfRange(arr, subArrIndex, maxLength + subArrIndex);
}

	}


