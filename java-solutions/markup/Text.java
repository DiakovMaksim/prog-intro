package markup;
public class Text extends MarkUpElement {

    public Text(String text) {
        super(new StringBuilder(text));
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbolStart = "";
        super.toMarkdown(entry);
    }

    @Override
    public void toTex(StringBuilder entry) {
        super.specialSymbolStart = "";
        super.specialSymbolEnd = "";
        super.toTex(entry);
    }
}
