package markup;

import java.util.List;

public class Strikeout extends MarkUpElement {

    public Strikeout(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbolStart = "~";
        super.toMarkdown(entry);
    }
    @Override
    public void toTex(StringBuilder entry) {
        super.specialSymbolStart = "\\textst{";
        super.specialSymbolEnd = "}";
        super.toTex(entry);
    }
}
