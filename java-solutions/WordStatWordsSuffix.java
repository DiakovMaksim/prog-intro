import java.io.*;
import java.util.Arrays;
public class WordStatWordsSuffix {
	public static void main(String[] args) {
		int wordQuantities = 0;
		String[] words = new String[1];
		int[] quantities = new int[1];
		try {
			Reader reader = new InputStreamReader(new FileInputStream(args[0]), "utf-8");
			try {
				int read = reader.read();
				String word = "";
				while (read >= 0) {
					char symbol = (char) read;
					if (Character.isLetter(symbol) || symbol == '\'' || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
						word += symbol;
						word = word.toLowerCase();
						if (word.length() > 3) {
							word = word.substring(1,4);
						}
					} else {
						boolean founded = false;
						if (word != "") {
							for (int i = 0; i < wordQuantities; i++) {
								if (word.equals(words[i])) {
									quantities[i]++;
									word = "";
									founded = true;
								}
							}
							if (!founded) {
								if (wordQuantities + 1 >= words.length) {
									words = Arrays.copyOf(words, 2 * quantities.length);
									quantities = Arrays.copyOf(quantities, 2 * quantities.length);
								}
								words[wordQuantities] = word;
								quantities[wordQuantities] = 1;
								wordQuantities++;
								word = "";
							}
						}
					}
					read = reader.read();
				}
				if (word != "") {
					boolean founded = false;
					for (int i = 0; i < wordQuantities - 1; i++) {
						if (word.equals(words[i])) {
							quantities[i]++;
							founded = true;
							word = "";
						}
					}
					if (!founded) {
						if (wordQuantities + 1 >= words.length) {
							words = Arrays.copyOf(words, 2 * quantities.length);
							quantities = Arrays.copyOf(quantities, 2 * quantities.length);
						}
						words[wordQuantities] = word;
						quantities[wordQuantities] = 1;
						wordQuantities++;
						word = "";
					}
				}
			} finally {
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("Reading error: " + e.getMessage());
		}
		for (int i = 1; i < wordQuantities; i++) {
			for (int j = i; j > 0; j--) {
				if (words[j].compareTo(words[j-1]) < 0) {
					String copyString = words[j];
					words[j] = words[j-1];
					words[j-1] = copyString;
					int copyInt = quantities[j];
					quantities[j] = quantities[j-1];
					quantities[j-1] = copyInt;
				}
			}
		}
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"utf-8"));
			try {
				for (int i = 0; i < wordQuantities; i++) {	
					writer.write(words[i] + " " + quantities[i] + System.lineSeparator());
				}
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("Writing error: " + e.getMessage());
		}
	}
}