package markup;

import java.util.List;

public class Paragraph extends MarkUpElement {
    public Paragraph(List<MarkUpElement> entry) {
        super(entry);
        super.specialSymbolStart = "";
        super.specialSymbolEnd = "";
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.toMarkdown(entry);
    }
    @Override
    public void toTex(StringBuilder entry) {
        super.toTex(entry);
    }
}
