import java.io.*;
import java.util.Arrays;
public class WordStatInput {
	public static void main(String[] args) {
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
					} else {
						boolean founded = false;
						if (word != "") {
							for (int i = 0; i < quantities.length; i++) {
								if (word.equals(words[i])) {
									quantities[i]++;
									word = "";
									founded = true;
								}
							}
							if (!founded) {
								words[words.length - 1] = word;
								quantities[words.length - 1] = 1;
								word = "";
								words = Arrays.copyOf(words, 1 + quantities.length);
								quantities = Arrays.copyOf(quantities, 1 + quantities.length);
							}
						}
					}
					read = reader.read();
				}
				if (word != "") {
					boolean founded = false;
					for (int i = 0; i < quantities.length; i++) {
						if (word.equals(words[i])) {
							quantities[i]++;
							founded = true;
							word = "";
						}
					}
					if (!founded) {
						words[quantities.length - 1] = word;
						quantities[quantities.length - 1] = 1;
						words = Arrays.copyOf(words, 1 + quantities.length);
						quantities = Arrays.copyOf(quantities, 1 + quantities.length);
						word = "";
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
				for (int i = 0; i < quantities.length - 1; i++) {
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