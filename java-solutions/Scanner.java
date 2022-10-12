import java.io.*;
import java.util.Arrays;

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
            quantity = input.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    public Scanner(InputStream inputName) {
        try {
            input = new BufferedReader(new InputStreamReader(
                    inputName, "UTF-8"));
            quantity = input.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    private void rebuffering() {
        try {
            quantity = input.read(buffer, 0, buffer.length);
            position = 0;
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
    }
    public String next() {
//        if (position >= quantity & quantity > 0) {
//            rebuffering();
//        }
//        boolean inNext = false;
//        StringBuilder nextString = new StringBuilder();
//        while (position < quantity) {
//            if (!Character.isWhitespace(buffer[position])){
//                inNext = true;
//                nextString.append(buffer[position]);
//            } else {
//                if (inNext) {
//                    return nextString.toString();
//                }
//            }
//            position++;
//            if (position >= quantity & quantity > 0) {
//                rebuffering();
//            }
//        }
//        return nextString.toString();
        boolean inNext = false;
        StringBuilder nextString = new StringBuilder();
        while (quantity > 0) {
            while (position < quantity) {
                if (!Character.isWhitespace(buffer[position])) {
                    inNext = true;
                    nextString.append(buffer[position]);
                } else {
                    if (inNext) {
                        return nextString.toString();
                    }
                }
                position++;
            }
            rebuffering();
        }
        return nextString.toString();
    }
    public String nextLine() {
        StringBuilder nextLine = new StringBuilder();
        while (quantity > 0) {
            while (position < quantity) {
                if (!(buffer[position] == '\n')) {
                    nextLine.append(buffer[position]);
                } else {
                    position++;
                    return nextLine.toString();
                }
                position++;
            }
            rebuffering();
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
        if (position >= quantity & quantity < buffer.length) {
            return false;
        }
        return true;
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