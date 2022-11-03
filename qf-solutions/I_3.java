import java.util.Scanner;

public class TaskI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int h = scanner.nextInt();
        int xl = x - h;
        int xr = x + h;
        int yl = y - h;
        int yr = y + h;
        for (int i = 1; i < num; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            h = scanner.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        System.out.println((xl + xr) / 2 + " " + (yl + yr) / 2 + " " + (int) Math.max(Math.ceil((xr - xl) / 2f), Math.ceil((yr - yl) / 2f)));
    }
}
