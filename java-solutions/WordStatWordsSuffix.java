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

/*
Testing Base
    Running test 01: java Reverse [1 input lines]
    Running test 02: java Reverse [2 input lines]
    Running test 03: java Reverse [3 input lines]
    Running test 04: java Reverse [4 input lines]
Exception in thread "main" java.lang.AssertionError: Line 3:
     expected ``,
       actual `3 2 1`
        at base.Asserts.error(Asserts.java:75)
        at base.Asserts.assertTrue(Asserts.java:41)
        at base.Asserts.assertEquals(Asserts.java:20)
        at base.Runner.lambda$testEquals$0(Runner.java:36)
        at base.TestCounter.lambda$test$0(TestCounter.java:58)
        at base.TestCounter.lambda$testV$2(TestCounter.java:71)
        at base.Log.silentScope(Log.java:40)
        at base.TestCounter.testV(TestCounter.java:70)
        at base.TestCounter.test(TestCounter.java:57)
        at base.Runner.testEquals(Runner.java:30)
        at reverse.ReverseTester$Checker.test(ReverseTester.java:102)
        at reverse.ReverseTester$Checker.test(ReverseTester.java:96)
        at reverse.ReverseTester$Checker.test(ReverseTester.java:149)
        at reverse.ReverseTester.run(ReverseTester.java:67)
        at reverse.ReverseTester.lambda$variant$2(ReverseTester.java:44)
        at base.Selector.lambda$test$2(Selector.java:79)
        at base.Log.lambda$action$0(Log.java:18)
        at base.Log.silentScope(Log.java:40)
        at base.Log.scope(Log.java:31)
        at base.Log.scope(Log.java:24)
        at base.Selector.lambda$test$3(Selector.java:79)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at base.Selector.test(Selector.java:79)
        at base.Selector.main(Selector.java:51)
        at reverse.FullFastReverseTest.main(FullFastReverseTest.java:15)

* */