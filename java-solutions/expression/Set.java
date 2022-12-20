package expression;

public class Set extends AbstractOperation{
    public Set(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, 's');
    }
    @Override
    public int eval(int left, int right) {
        left |= 1 << right;
        return left;
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " set " + secondExpression + ")";
    }
}
