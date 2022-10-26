package markup;

import java.util.List;

public abstract class MarkUpElement implements MarkUpable {
    StringBuilder entryText;
    List<MarkUpElement> entry;
    String specialSymbolStart;
    String specialSymbolEnd;
    public MarkUpElement(StringBuilder entry) {
        this.entryText = entry;
    }

    public MarkUpElement(List<MarkUpElement> entry) {
        this.entry = entry;
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        entry.append(specialSymbolStart);
        for (MarkUpElement part : this.entry) {
            part.toMarkdown(entry);
        }
        entry.append(specialSymbolStart);
    }
    @Override
    public void toTex(StringBuilder entry) {
        entry.append(specialSymbolStart);
        for (MarkUpElement part : this.entry) {
            part.toTex(entry);
        }
        entry.append(specialSymbolEnd);
    }
}
