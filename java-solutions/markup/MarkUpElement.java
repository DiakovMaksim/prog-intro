package markup;

import java.util.List;

public abstract class MarkUpElement implements MarkUpable {
    StringBuilder entryBBCode;
    StringBuilder entryTex;
    String specialSymbolStart;
    String specialSymbolEnd;
    public MarkUpElement(StringBuilder entry) {
        this.entryBBCode = entry;
        this.entryTex = entry;
    }

    public MarkUpElement(List<MarkUpElement> entry) {
        this.entryBBCode = new StringBuilder();
        this.entryTex = new StringBuilder();
        for (MarkUpElement element : entry) {
            StringBuilder elementString = new StringBuilder();
            element.toMarkdown(elementString);
            this.entryTex.append(elementString);
            elementString = new StringBuilder();
            element.toTex(elementString);
            this.entryBBCode.append(elementString);
        }
    }

    @Override
    public void toMarkdown(StringBuilder entry) {
        entry.append(specialSymbolStart);
        entry.append(this.entryTex);
        entry.append(specialSymbolStart);
    }
    @Override
    public void toTex(StringBuilder entry) {
        entry.append(specialSymbolStart);
        entry.append(this.entryBBCode);
        entry.append(specialSymbolEnd);
    }
}
