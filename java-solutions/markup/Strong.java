package markup;

import java.util.List;

public class Strong extends MarkUpElement {
    public Strong(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbolStart = "__";
        super.toMarkdown(entry);
    }
    @Override
    public void toTex(StringBuilder entry) {
        super.specialSymbolStart = "\\textbf{";
        super.specialSymbolEnd = "}";
        super.toTex(entry);
    }
}
