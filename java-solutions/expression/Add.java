package expression;

public class Add extends AbstractOperation {
    public Add(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '+');
    }
}
