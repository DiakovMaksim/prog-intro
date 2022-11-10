package md2html;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Testing Underline
//    Testing em, strong, s, code, u
//        Running test 01: java md2html.Md2Html "test1.in" "test1.out"
//        Running test 02: java md2html.Md2Html "test2.in" "test2.out"
//        Running test 03: java md2html.Md2Html "test3.in" "test3.out"
//        Running test 04: java md2html.Md2Html "test4.in" "test4.out"
//        Running test 05: java md2html.Md2Html "test5.in" "test5.out"
//        Running test 06: java md2html.Md2Html "test6.in" "test6.out"
//        Running test 07: java md2html.Md2Html "test7.in" "test7.out"
//        Running test 08: java md2html.Md2Html "test8.in" "test8.out"
//        Running test 09: java md2html.Md2Html "test9.in" "test9.out"
//        Running test 10: java md2html.Md2Html "test10.in" "test10.out"
//        Running test 11: java md2html.Md2Html "test11.in" "test11.out"
//        Running test 12: java md2html.Md2Html "test12.in" "test12.out"
//        Running test 13: java md2html.Md2Html "test13.in" "test13.out"
//Exception in thread "main" java.lang.AssertionError: main thrown exception StringIndexOutOfBoundsException: java.lang.StringIndexOutOfBoundsException: Index -1 out of bounds for length 0
//        at base.Asserts.error(Asserts.java:74)
//        at base.Runner$Packages.lambda$main$3(Runner.java:103)
//        at base.Runner$Packages.lambda$args$5(Runner.java:152)
//        at base.Runner$Packages.lambda$files$7(Runner.java:174)
//        at base.TestCounter$SupplierE.get(TestCounter.java:160)
//        at base.TestCounter.get(TestCounter.java:142)
//        at base.TestCounter.call(TestCounter.java:114)
//        at base.Runner$Packages.lambda$files$8(Runner.java:170)
//        at base.Runner.lambda$testEquals$0(Runner.java:31)
//        at base.TestCounter.lambda$test$0(TestCounter.java:58)
//        at base.TestCounter.lambda$testV$2(TestCounter.java:71)
//        at base.Log.silentScope(Log.java:40)
//        at base.TestCounter.testV(TestCounter.java:70)
//        at base.TestCounter.test(TestCounter.java:57)
//        at base.Runner.testEquals(Runner.java:30)
//        at md2html.Md2HtmlTester$Checker.test(Md2HtmlTester.java:208)
//        at md2html.Md2HtmlTester$Checker.test(Md2HtmlTester.java:193)
//        at md2html.Md2HtmlTester.lambda$test$2(Md2HtmlTester.java:183)
//        at base.Log.lambda$action$0(Log.java:18)
//        at base.Log.silentScope(Log.java:40)
//        at base.Log.scope(Log.java:31)
//        at base.Log.scope(Log.java:24)
//        at md2html.Md2HtmlTester.test(Md2HtmlTester.java:183)
//        at base.Selector$Composite.lambda$v$0(Selector.java:134)
//        at base.Selector.lambda$test$2(Selector.java:79)
//        at base.Log.lambda$action$0(Log.java:18)
//        at base.Log.silentScope(Log.java:40)
//        at base.Log.scope(Log.java:31)
//        at base.Log.scope(Log.java:24)
//        at base.Selector.lambda$test$3(Selector.java:79)
//        at java.base/java.lang.Iterable.forEach(Iterable.java:75)
//        at base.Selector.test(Selector.java:79)
//        at base.Selector.main(Selector.java:51)
//        at md2html.FullMd2HtmlTest.main(FullMd2HtmlTest.java:120)
//Caused by: java.lang.StringIndexOutOfBoundsException: Index -1 out of bounds for length 0
//        at java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:55)
//        at java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:52)
//        at java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:213)
//        at java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:210)
//        at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:98)
//        at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
//        at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
//        at java.base/java.lang.String.checkIndex(String.java:4570)
//        at java.base/java.lang.AbstractStringBuilder.charAt(AbstractStringBuilder.java:358)
//        at java.base/java.lang.StringBuilder.charAt(StringBuilder.java:91)
//        at md2html.Scanner.nextLine(Scanner.java:83)
//        at md2html.Md2Html.main(Md2Html.java:16)
//        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
//        at java.base/java.lang.reflect.Method.invoke(Method.java:578)
//        at base.Runner$Packages.lambda$main$3(Runner.java:88)
//        ... 32 more
//ERROR: Tests: failed

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder paragraph = new StringBuilder();
        List<String> text = new ArrayList<>();
        StringBuilder fragment;
        int position = 0;
        try {
            Scanner lineScanner = new Scanner(new FileInputStream(args[0]));
            try {
                while (lineScanner.hasNextLine()) {
                    String line = lineScanner.nextLine();
                        if (!line.trim().isEmpty()) {
                            line = line.replace("&", "&amp;");
                            line = line.replace("<", "&lt;");
                            line = line.replace(">", "&gt;");
                            if (!paragraph.isEmpty()) {
                                paragraph.append(System.lineSeparator());
                            }
                            paragraph.append(line);
                        }
                        if (!lineScanner.hasNextLine() || line.trim().isEmpty()) {
                            if (paragraph.length() > 0) {
                                int level = 0;
                                while (level < paragraph.length() && paragraph.charAt(level) == '#') {
                                    level++;
                                }
                                boolean header = level > 0 && level < paragraph.length() && Character.isWhitespace(paragraph.charAt(level));
                                for (int i = 0; i < paragraph.length(); i++) {
                                    switch (paragraph.charAt(i)) {
                                        case ('*'):
                                        case ('_'):
                                        case ('-'):
                                        case ('+'):
                                        case ('`'):
                                            paragraph = convert(paragraph, i);
                                            break;
                                    }
                                }
                                fragment = new StringBuilder();
                                if (header) {
                                    fragment.append("<h").append(level).append(">");
                                    fragment.append(paragraph.substring(level + 1, paragraph.length()));
                                    fragment.append("</h").append(level).append(">");
                                } else {
                                    fragment.append("<p>").append(paragraph.toString().replace("\\", "")).append("</p>");
                                }
                                text.add(fragment.toString());
                                position++;
                                paragraph = new StringBuilder();
                            }
                        }
                }
            } finally {
                lineScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        }
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"UTF-8"));
            try {
                for (int i = 0; i < position; i++) {
                    writer.write(text.get(i));
                    writer.write(System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Writing error: " + e.getMessage());
        }
    }
    static StringBuilder convert(StringBuilder paragraph, int position) {
        char symbol = paragraph.charAt(position);
        String symbol2 = String.valueOf(symbol) + symbol;
        if (position > 0 && paragraph.charAt(position - 1) == '\\') {
            return paragraph;
        }
        if (position < paragraph.length() - 1 && paragraph.charAt(position + 1) == symbol && symbol != '`') {
            int border = paragraph.indexOf(symbol2, position + 2);
            while (border > 0 && paragraph.charAt(border - 1) == '\\') {
                border = paragraph.indexOf(symbol2, border + 1);
            }
            if (border > 0) {
                String tag = "";
                switch (symbol) {
                    case ('_'):
                    case ('*'):
                        tag = "strong";
                        break;
                    case ('+'):
                        tag = "u";
                        break;
                    case ('-'):
                        tag = "s";
                        break;
                }
                paragraph.replace(position, position + 2, "<" + tag + ">");
                paragraph.replace(border + tag.length(), border + tag.length() + 2, "</" + tag + ">");
            }
        } else if (symbol != '+') {
            int border = paragraph.indexOf(String.valueOf(symbol), position + 1);
            while (border > 0 && (paragraph.charAt(border - 1) == '\\' || border < paragraph.length() - 1 && paragraph.charAt(border + 1) == symbol || paragraph.charAt(border - 1) == symbol)) {
                border = paragraph.indexOf(String.valueOf(symbol), border + 1);
            }
            if (border > 0) {
                String tag = "";
                switch (symbol) {
                    case ('_'):
                    case ('*'):
                        tag = "em";
                        break;
                    case ('-'):
                        return paragraph;
                    case ('`'):
                        tag = "code";
                }
                paragraph.replace(position, position + 1, "<" + tag + ">");
                paragraph.replace(border + tag.length() + 1, border + tag.length() + 2, "</" + tag + ">");
            }
        }
        return paragraph;
    }
}