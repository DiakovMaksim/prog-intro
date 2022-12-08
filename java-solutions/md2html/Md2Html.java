package md2html;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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