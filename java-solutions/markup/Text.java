package markup;
public class Text extends MarkUpElement {

    public Text(String text) {
        super(new StringBuilder(text));
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        entry.append(this.entryText);
    }

    @Override
    public void toTex(StringBuilder entry) {
        entry.append(this.entryText);
    }
}
