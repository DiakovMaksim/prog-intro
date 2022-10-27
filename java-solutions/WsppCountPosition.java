import java.io.*;
import java.util.*;

public class WsppCountPosition {
    public static void main(String[] args) {
        int lineNumber = 0;
        // Map
        HashMap<String, List<Integer>> words = new HashMap<>();
        try {
            Scanner lineScanner = new Scanner(new FileInputStream(args[0]));
            try {
                while (lineScanner.hasNextLine()) {
                    lineNumber++;
                    int quantity = 0;
                    String line = lineScanner.nextLine();
                    Scanner scanner = new Scanner(line);
                    while (scanner.hasNextWord()) {
                        String word = scanner.nextWord().toLowerCase();
                        if (word != "") {
                            // getOeDefault
                            if (words.containsKey(word)) {
                                List<Integer> copy = words.get(word);
                                quantity++;
                                copy.add(lineNumber);
                                copy.add(quantity);
                                copy.set(0, copy.get(0) + 1);
                                words.put(word, copy);
                            } else {
                                List<Integer> newList = new ArrayList<Integer>();
                                newList.add(1);
                                newList.add(lineNumber);
                                quantity++;
                                newList.add(quantity);
                                words.put(word, newList);
                            }
                        }
                    }
                }
            } finally {
                lineScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
        ValueComparator comp = new ValueComparator(words);
        TreeMap<String, List<Integer>> sorted = new TreeMap<String, List<Integer>>(comp);
        sorted.putAll(words);
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"utf-8"));
            try {
                for (String word : sorted.keySet()) {
                    writer.write(word + " ");
                    List<Integer> positions = sorted.get(word);
                    int number = 0;
                    for (Integer j : positions) {
                        number++;
                        if ((number % 2) == 0) {
                            writer.write(" " + j.toString() + ":");
                        } else {
                            writer.write(j.toString());
                        }
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
class ValueComparator implements Comparator<String> {
    Map<String, List<Integer>> base;

    public ValueComparator(Map<String, List<Integer>> base) {
        this.base = base;
    }

    @Override
    public int compare(String a, String b){
        List<Integer> firstList = base.get(a);
        List<Integer> secondList = base.get(b);
        if (firstList.get(0).equals(secondList.get(0))) {
            if (firstList.get(1).equals(secondList.get(1))) {
                return firstList.get(2).compareTo(secondList.get(2));
            } else {
                return firstList.get(1).compareTo(secondList.get(1));
            }
        } else {
            return firstList.get(0).compareTo(secondList.get(0));
        }
    }
}