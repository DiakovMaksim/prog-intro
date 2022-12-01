package expression;

public class Subtract extends AbstractOperation{
    public Subtract(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '-');
    }
}
