import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        int quantity = 0;
        HashMap<String, List<Integer>> words = new LinkedHashMap<>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            try {
                while (scanner.hasNextWord()) {
                    String word = scanner.nextWord().toLowerCase();
                    if (word != "") {
                        if (words.containsKey(word)) {
                            List<Integer> copy = words.get(word);
                            quantity++;
                            copy.add(quantity);
                            copy.set(0, copy.get(0) + 1);
                            words.put(word, copy);
                        } else {
                            List<Integer> newList = new ArrayList<Integer>();
                            newList.add(1);
                            quantity++;
                            newList.add(quantity);
                            words.put(word, newList);
                        }
                    }
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"utf-8"));
            try {
                Set<String> wordsSet = words.keySet();
                for (String word : wordsSet) {
                    writer.write(word);
                    List<Integer> positions = words.get(word);
                    for (Integer i : positions) {
                        writer.write(" " + i);
                    }
                    writer.write(System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Writing error: " + e.getMessage());
        }
    }
}
