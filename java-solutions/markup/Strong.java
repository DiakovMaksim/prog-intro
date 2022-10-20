package markup;

import java.util.List;

public class Strong extends MarkUpElement {
    public Strong(List<MarkUpElement> entry) {
        super(entry);
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        super.specialSymbol = "__";
        super.toMarkdown(entry);
    }
}
