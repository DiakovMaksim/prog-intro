public class TaskA {
    public static void main(String[] args) {
        System.out.print(2 * (int) Math.ceil((Double.parseDouble(args[2]) - Integer.parseInt(args[1])) / (Integer.parseInt(args[1]) - Integer.parseInt(args[0]))) + 1);
    }
}