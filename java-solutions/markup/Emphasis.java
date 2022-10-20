package markup;

import java.util.List;

public class Emphasis extends MarkUpElement {
    public Emphasis(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbolStart = "*";
        super.toMarkdown(entry);
    }
    @Override
    public void toTex(StringBuilder entry) {
        super.specialSymbolStart = "\\emph{";
        super.specialSymbolEnd = "}";
        super.toTex(entry);
    }
}
