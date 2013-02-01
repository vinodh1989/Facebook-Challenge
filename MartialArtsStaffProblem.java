import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
 * 
 * VERY CLOSE TO ANSWER. JUST NEEDED FEW MORE MINUTES TO FIGURE OUT.
 * PLEASE SEE THE LOGIC. MOST OF IT IS CORRECT.
 * EXCEPT FOR THE COMPARISIONS.
 * 
 */
public class MartialArtsStaffProblem {
	private static int[] trunk = null;
	private static int trunkSize = -1;
	private static int leftFoundIndex = -1;
	private static int rightFoundIndex = -1;
	private static int foundLength = -1;
	private static boolean found = false;

	public static void main(String[] args) throws Exception {
		readInput();
		solveProblem();
		printOutput();
	}

	private static void printOutput() throws IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
				System.out));
		output.write(leftFoundIndex + " " + rightFoundIndex + " " + foundLength);
		output.flush();
		output.close();
	}

	private static void readInput() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		String trunkInput = input.readLine();
		trunkSize = trunkInput.length();
		trunk = new int[trunkSize];

		int i = 0;
		while (i < trunkSize) {
			trunk[i] = Integer.parseInt(trunkInput.charAt(i) + "");
			i++;
		}
		input.close();
	}

	private static void solveProblem() throws Exception {
		// Find pairs of equal length staff
		// Longest sub-staff can be n/2
		// shortest sub-staff can be 1
		// Find what ideally should be checking
		// condition to verify we have correct pair of staff. (n+1)/2
		// we need to consider reverse of substaffs also
		// Find average mass point for this staff
		// See if above two are same. if it is store our staffs
		// Do this process for all staffs till we find the longest one.

		for (int staffLen = trunkSize / 2; staffLen >= 1; staffLen--) {
			int leftSubStaffStartIndex = 0;

			while (leftSubStaffStartIndex <= trunkSize - 2 * staffLen) {
				String left1 = getSubStaff(leftSubStaffStartIndex, staffLen);
				String left2 = new StringBuilder(left1).reverse().toString();// the
																				// reverse
				int rightSubStaffStartIndex = leftSubStaffStartIndex + staffLen;
				while (rightSubStaffStartIndex + staffLen <= trunkSize) {
					String right1 = getSubStaff(rightSubStaffStartIndex,
							staffLen);
					String right2 = new StringBuilder(right1).reverse()
							.toString();// the reverse
					if (getWeight(left1 + right1)
							/ getSumOfMass(left1 + right1) == staffLen
							|| getWeight(left1 + right2)
									/ getSumOfMass(left1 + right2) == staffLen
							|| getWeight(left2 + right1)
									/ getSumOfMass(left2 + right1) == staffLen
							|| getWeight(left2 + right2)
									/ getSumOfMass(left2 + right2) == staffLen)

					{
						foundLength = staffLen;
						leftFoundIndex = leftSubStaffStartIndex;
						rightFoundIndex = rightSubStaffStartIndex;
						found = true;
						return;
					}

					rightSubStaffStartIndex++;
				}
				leftSubStaffStartIndex++;
			}

		}

	}

	private static int getWeight(String subStaff) {
		int length = subStaff.length();
		int i = 0;
		int weight = 0;
		while (i < length) {
			weight = weight + Integer.parseInt(subStaff.charAt(i) + "")
					* (i + 1);
			i++;
		}
		return weight;
	}

	private static int getSumOfMass(String subStaff) {
		int length = subStaff.length();
		int i = 0;
		int weight = 0;
		while (i < length) {
			weight = weight + Integer.parseInt(subStaff.charAt(i) + "");
			i++;
		}
		return weight;
	}

	private static String getSubStaff(int startIndex, int sections)
			throws Exception {
		if (startIndex < 0 || startIndex + sections > trunkSize) {
			throw new Exception("Out of range");
		}
		StringBuffer sb = new StringBuffer();
		int endIndex = startIndex + sections;
		while (startIndex < endIndex) {
			sb.append(trunk[startIndex]);
			startIndex++;
		}
		return sb.toString();
	}

}