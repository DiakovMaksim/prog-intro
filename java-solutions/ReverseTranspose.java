import java.util.Scanner;
import java.util.Arrays;
public class ReverseTranspose {
	public static void main (String[] args) {
		Scanner scanString = new Scanner(System.in);
		int[][] nums;
		nums = new int[1][1];
		int[] lengths;
		lengths = new int[1];
		int i = 0;
		int maxLength = 1;
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
			if(i >= lengths.length) {
				int[] copyLengths = Arrays.copyOf(lengths, 2 * lengths.length);
				lengths = copyLengths;
			}
			lengths[i] = j;
			int[] copy1d = Arrays.copyOf(nums[i], j);
			nums[i] = copy1d;
			if(j > maxLength) {
				maxLength = j;
			}
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
		for(int columnNumber = 0; columnNumber < maxLength; columnNumber++) {
			for(int stringNumber = 0; stringNumber < nums.length; stringNumber++) {
				if(columnNumber < lengths[stringNumber]) {
				System.out.print(nums[stringNumber][columnNumber] + " ");
				}
			}
			System.out.println();
		}
	}
}