package markup;

import java.util.List;

public abstract class MarkUpElement implements MarkUpable {
    StringBuilder entry;
    String specialSymbol;
    public MarkUpElement(StringBuilder entry) {
        this.entry = entry;
    }

    public MarkUpElement(List<MarkUpElement> entry) {
        this.entry = new StringBuilder();
        for (MarkUpElement element : entry) {
            StringBuilder elementString = new StringBuilder();
            element.toMarkdown(elementString);
            this.entry.append(elementString);
        }
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        entry.append(specialSymbol);
        entry.append(this.entry);
        entry.append(specialSymbol);
    }
}
