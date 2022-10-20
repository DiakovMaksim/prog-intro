package markup;

import java.util.List;

public class Strikeout extends MarkUpElement {

    public Strikeout(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbol = "~";
        super.toMarkdown(entry);
    }
}
