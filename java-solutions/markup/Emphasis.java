package markup;

import java.util.List;

public class Emphasis extends MarkUpElement {
    public Emphasis(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbol = "*";
        super.toMarkdown(entry);
    }
}
