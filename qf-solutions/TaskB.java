import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        int answer = -710 * 25000;
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            System.out.println(answer);
            answer += 710;
        }
    }
}
