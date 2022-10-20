package markup;
public class Text extends MarkUpElement {

    public Text(String text) {
        super(new StringBuilder(text));
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbol = "";
        super.toMarkdown(entry);
    }
}
