public class TaskB {
    public static void main(String[] args) {
        int answer = -710 * 25000;
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(answer);
            answer++;
        }
    }
}
