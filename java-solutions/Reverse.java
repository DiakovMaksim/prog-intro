import java.util.Scanner;
import java.util.Arrays;
public class Reverse {
	public static void main (String[] args) {
		Scanner scanString = new Scanner(System.in);
		int[][] nums;
		nums = new int[1][1];
		int i = 0;
		while(scanString.hasNextLine()) {
			String line = scanString.nextLine();
			Scanner scanInt = new Scanner(line);
			int j = 0;
			while(scanInt.hasNextInt()) {
				if(j >= nums[i].length) {
					int[] copy1d = Arrays.copyOf(nums[i], 2 * nums[i].length);
					nums[i] = copy1d;
				}
				nums[i][j] = scanInt.nextInt();
				j++;
			}
			int[] copy1d = Arrays.copyOf(nums[i], j);
			nums[i] = copy1d;
			if(scanString.hasNextLine()) {
				i++;
				if(i >= nums.length) {
					int[][] copy2d = Arrays.copyOf(nums, 2 * nums.length);
					for(int checker = i; checker < copy2d.length; checker++) {
						copy2d[checker] = new int[1];
					}
					nums = copy2d;
				}
			}
		}
		for(int stringNumber = i; stringNumber >= 0; stringNumber--) {
			for(int columnNumber = nums[stringNumber].length - 1; columnNumber >= 0; columnNumber--) {
				System.out.print(nums[stringNumber][columnNumber] + " ");
			}
			System.out.println();
		}
	}
}