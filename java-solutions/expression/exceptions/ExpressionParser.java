package expression.exceptions;

import expression.TripleExpression;

public class ExpressionParser implements TripleParser {
    public TripleExpression parse(String source) {
        return ExpressionParseStarter.parse(source);
    }
}
