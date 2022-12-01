package expression;

public class Multiply extends AbstractOperation{
    public Multiply(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '*');
    }
}
