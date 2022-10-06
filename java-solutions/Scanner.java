import java.io.*;
import java.util.Arrays;

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
 */
public class Scanner {
    String inputName;
    String charSet;
    BufferedReader input;
    char[] buffer = new char[1024];
    int quantity = 0;
    int position = 0;
    /*public Scanner(String inputName, String charSet) {
        this.inputName = inputName;
        this.charSet = charSet;
        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(inputName), charSet));
            quantity = input.read(buffer, 0, 1024);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    public Scanner(String inputName) {
        this.inputName = inputName;
        this.charSet = "UTF-8";
        try {
            input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(inputName), charSet));
            quantity = input.read(buffer, 0, 1024);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }*/
    public Scanner(String inputName) {
        try {
            input = new BufferedReader(new StringReader(inputName));
            quantity = input.read(buffer, 0, 1024);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    public Scanner(InputStream inputName) {
        try {
            input = new BufferedReader(new InputStreamReader(
                    inputName, "UTF-8"));
            quantity = input.read(buffer, 0, 1024);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    private void rebuffering() {
        try {
            quantity = input.read(buffer, 0, 1024);
            position = 0;
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    public String next() {
        if (position >= quantity & quantity > 0) {
            rebuffering();
        }
        boolean inNext = false;
        StringBuilder nextString = new StringBuilder();
        while (position < quantity) {
            if (!Character.isWhitespace(buffer[position])){
                inNext = true;
                nextString.append(buffer[position]);
            } else {
                if (inNext) {
                    return nextString.toString();
                }
            }
            position++;
            if (position >= quantity & quantity > 0) {
                rebuffering();
            }
        }
        return nextString.toString();
    }
    public String nextLine() {
        if (position >= quantity & quantity > 0) {
            rebuffering();
        }
        boolean inNext = false;
        StringBuilder nextLine = new StringBuilder();
        while (position < quantity) {
            if (!(buffer[position] == '\n')) {
                inNext = true;
                nextLine.append(buffer[position]);
            } else {
                //    if (inNext) {
                    return nextLine.toString();
                //}
            }
            position++;
            if (position >= quantity & quantity > 0) {
                rebuffering();
            }
        }
        return nextLine.toString();
    }
    public boolean hasNext() {
        while (quantity > 0) {
            while (position < quantity) {
                if (!Character.isWhitespace(buffer[position])) {
                    return true;
                }
                position++;
            }
            rebuffering();
        }
        return false;
    }
    public int nextInt() {
        String nextWord = next();
        do {
            try {
                int nextWordInt = Integer.parseInt(nextWord);
                return nextWordInt;
            } catch (NumberFormatException e) {
                nextWord = next();
            }
        }
        while (hasNext());
        return 0;
    }
    public int nextIntabc() {
        String nextWord = next();
        int nextabc = 0;
        for (int i = 0; i < nextWord.length(); i++) {
            nextabc = nextabc * 10 + (int) nextWord.charAt(i) - (int) 'a';
        }
        return nextabc;
    }
    public boolean hasNextLine() {
        while (quantity > 0) {
            while (position < quantity) {
                if (!(buffer[position] == '\n')) {
                    return true;
                }
                position++;
            }
            rebuffering();
        }
        return false;
    }
    public boolean hasNextInt() {
        return hasNext();
    }
    public void close() {
        try {
            input.close();
        } catch (IOException e) {
            System.out.println("Scanner have been already closed " + e.getMessage());
        }
    }
}