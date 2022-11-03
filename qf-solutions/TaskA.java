import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        float n = scanner.nextInt();
        System.out.print(2 * (int) Math.ceil((n - b) / (b - a)) + 1);
    }
}