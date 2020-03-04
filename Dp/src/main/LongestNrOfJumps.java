package main;

import java.util.ArrayList;

public class LongestNrOfJumps {
	public static void main(String[] args) {
		new Solution().Solution();
	}
}

//Find the maximum amount of nodes that can be visited in a flwg (graph/array); 
//If we are at a node list[i], then we can only move to:
//   list[i+1] or list[i-1] an adjecent (to the right or left)
//   OR
//   list[i+2] or list[i-2] the nodes next to an adjecent node (to the right or left)


class Solution { ///////////////////

	int apidata[] = { 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 2, 1, 0, 2 };///////////////////

	int nrOfBars = apidata.length; ///////////////////


	int list[] = new int[nrOfBars];
	ArrayList<Integer> res;
	static int nrOfexec = 0;

	public void Solution() { ///////////////////

		for (int i = 0; i < nrOfBars; i++) {
			list[i] = apidata[i]; ///////////////////
		}

		res = findLongestPath(new ArrayList<Integer>(), 8);

		for (int i = 0; i < res.size(); i++) {
			System.out.println("¤¤¤¤¤¤¤¤¤" + list[res.get(i)]);
		}

		 System.out.println("#### nrOfexec #### "  + nrOfexec);

	}

	public ArrayList<Integer> findLongestPath(ArrayList<Integer> beseokt, int currIndx) {
		nrOfexec++;
		ArrayList<Integer> resFromR1 = new ArrayList<Integer>();
		ArrayList<Integer> resFromR2 = new ArrayList<Integer>();
		ArrayList<Integer> resFromL1 = new ArrayList<Integer>();
		ArrayList<Integer> resFromL2 = new ArrayList<Integer>();

		int indxR1 = currIndx + 1;
		int indxR2 = currIndx + 2;
		int indxL1 = currIndx - 1;
		int indxL2 = currIndx - 2;

		boolean canGoR1 = currIndx + 1 < list.length && !beseokt.contains(indxR1)
				&& list[currIndx + 1] < list[currIndx];
		boolean canGoR2 = currIndx + 2 < list.length && !beseokt.contains(indxR2)
				&& list[currIndx + 2] < list[currIndx];
		boolean canGoL1 = currIndx - 1 >= 0 && !beseokt.contains(indxL1) && list[currIndx - 1] < list[currIndx];
		boolean canGoL2 = currIndx - 2 >= 0 && !beseokt.contains(indxL2) && list[currIndx - 2] < list[currIndx];

		// basis
		if (!canGoR1 && !canGoR2 && !canGoL1 && !canGoL2) {
			beseokt.add(currIndx);
			return beseokt;
		}

		if (canGoR1) {
			ArrayList<Integer> _b = (ArrayList<Integer>) beseokt.clone();
			_b.add(currIndx);
			resFromR1 = findLongestPath(_b, indxR1);
		}

		if (canGoR2) {
			ArrayList<Integer> _b = (ArrayList<Integer>) beseokt.clone();
			_b.add(currIndx);
			resFromR2 = findLongestPath(_b, indxR2);
		}

		if (canGoL1) {
			ArrayList<Integer> _b = (ArrayList<Integer>) beseokt.clone();
			_b.add(currIndx);
			resFromL1 = findLongestPath(_b, indxL1);
		}

		if (canGoL2) {
			ArrayList<Integer> _b = (ArrayList<Integer>) beseokt.clone();
			_b.add(currIndx);
			resFromL2 = findLongestPath(_b, indxL2);
		}

		if (resFromR1.size() >= resFromR2.size() && resFromR1.size() >= resFromL1.size()
				&& resFromR1.size() >= resFromL2.size()) {
			return resFromR1;
		} else if (resFromR2.size() >= resFromR1.size() && resFromR2.size() >= resFromL1.size()
				&& resFromR2.size() >= resFromL2.size()) {
			return resFromR2;
		} else if (resFromL1.size() >= resFromR1.size() && resFromL1.size() >= resFromR2.size()
				&& resFromL1.size() >= resFromL2.size()) {
			return resFromL1;
		} else {
			return resFromL2;
		}

	}

	/**
	 * Returns the max number of jumps possible given the bar graph at hand. For
	 * example, if there is only one bar in the graph this function should return
	 * zero.
	 */
	public int maxNumberOfJumps() {
		// Write your code here
		return res.size();
	}

	/**
	 * Return the squirrel's position at the step asked for. The initial position is
	 * step zero, the position after the first jump is step 1 and so forth.
	 * 
	 * The position should be the index in the bar graph, so for the leftmost bar 0
	 * (zero) should be returned.
	 */
	public int positionAtStep(int step) {
		// Write your code here
		return res.get(step);
	}
}