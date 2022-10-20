package markup;

public interface MarkUpable {
    void toMarkdown(StringBuilder entry);
    void toTex(StringBuilder entry);
}
