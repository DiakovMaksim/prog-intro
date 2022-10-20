package markup;

import java.util.List;

public class Paragraph extends MarkUpElement {
    public Paragraph(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbol = "";
        super.toMarkdown(entry);
    }
}
