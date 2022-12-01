package expression;

public class Divide extends AbstractOperation {
    public Divide(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '/');
    }
}
