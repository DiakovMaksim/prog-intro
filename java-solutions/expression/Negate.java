package expression;

public class Negate extends AbstractOperation {
    public Negate(AbstractExpression expression) {
        super(new Const(0), expression, '-');
    }
    @Override
    public int eval(int left, int right) {
        return left - right;
    }
    @Override
    public double eval(double left, double right) {
        return left - right;
    }
    @Override
    public String toString() {
        return "-(" + this.secondExpression + ")";
    }
}
