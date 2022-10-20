package markup;

import java.util.List;

public class Paragraph extends MarkUpElement {
    public Paragraph(List<MarkUpElement> entry) {
        super(entry);
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
