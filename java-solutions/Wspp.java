import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        int wordQuantities = 0;
        HashMap<String, List<Integer>> words = new LinkedHashMap<>();
        int quantity = 0;
        try {
            Reader reader = new InputStreamReader(new FileInputStream(args[0]), "utf-8");
            try {
                int read = reader.read();
                StringBuilder wordBuilder = new StringBuilder();
                while (read >= 0) {
                    char symbol = (char) read;
                    boolean whitespace = true;
                    if (Character.isLetter(symbol) || symbol == '\'' || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                        whitespace = false;
                        wordBuilder.append(symbol);
                        read = reader.read();
                    }
                    if (wordBuilder.length() != 0 & (whitespace | read < 0)) {
                        String wordToAdd = wordBuilder.toString();
                        wordToAdd = wordToAdd.toLowerCase();
                        if (words.containsKey(wordToAdd)) {
                            List<Integer> copy = words.get(wordToAdd);
                            quantity++;
                            copy.add(quantity);
                            copy.set(0, copy.get(0) + 1);
                            words.put(wordToAdd, copy);
                        } else {
                            List<Integer> newList = new ArrayList<Integer>();
                            newList.add(1);
                            quantity++;
                            newList.add(quantity);
                            words.put(wordToAdd, newList);
                        }
                        wordBuilder = new StringBuilder();
                        if (whitespace) {
                            read = reader.read();
                        }
                    } else {
                        if (whitespace) {
                            read = reader.read();
                        }
                    }
                }
            } finally {
                reader.close();
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