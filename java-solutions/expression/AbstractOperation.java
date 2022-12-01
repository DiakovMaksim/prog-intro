package expression;

public class AbstractOperation extends AbstractExpression {
    AbstractExpression firstExpression;
    AbstractExpression secondExpression;
    char symbol;
    public AbstractOperation(AbstractExpression firstExpression, AbstractExpression secondExpression, char symbol) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.symbol = symbol;
    }
    @Override
    public int evaluate(int variable) {
        return switch (symbol) {
            case ('+') -> firstExpression.evaluate(variable) + secondExpression.evaluate(variable);
            case ('-') -> firstExpression.evaluate(variable) - secondExpression.evaluate(variable);
            case ('*') -> firstExpression.evaluate(variable) * secondExpression.evaluate(variable);
            case ('/') -> firstExpression.evaluate(variable) / secondExpression.evaluate(variable);
            default -> 0;
        };
    }
    @Override
    public double evaluate(double variable) {
        return switch (symbol) {
            case ('+') -> firstExpression.evaluate(variable) + secondExpression.evaluate(variable);
            case ('-') -> firstExpression.evaluate(variable) - secondExpression.evaluate(variable);
            case ('*') -> firstExpression.evaluate(variable) * secondExpression.evaluate(variable);
            case ('/') -> firstExpression.evaluate(variable) / secondExpression.evaluate(variable);
            default -> 0;
        };
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return switch (symbol) {
            case ('+') -> firstExpression.evaluate(x, y, z) + secondExpression.evaluate(x, y, z);
            case ('-') -> firstExpression.evaluate(x, y, z) - secondExpression.evaluate(x, y, z);
            case ('*') -> firstExpression.evaluate(x, y, z) * secondExpression.evaluate(x, y, z);
            case ('/') -> firstExpression.evaluate(x, y, z) / secondExpression.evaluate(x, y, z);
            default -> 0;
        };
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " " + symbol + " " + secondExpression + ")";
    }
    @Override
    public boolean equals(Object comparior) {
        return comparior != null && this.getClass() == comparior.getClass() && this.firstExpression.equals(((AbstractOperation) comparior).firstExpression) && this.secondExpression.equals(((AbstractOperation) comparior).secondExpression);
    }
    @Override
    public int hashCode() {
        return switch (symbol) {
            case ('+') -> 601 * (firstExpression.hashCode() * 17 * 17 + secondExpression.hashCode() * 19 * 19);
            case ('-') -> 701 * (firstExpression.hashCode() * 29 * 29 + secondExpression.hashCode() * 31 * 31);
            case ('*') -> 809 * (firstExpression.hashCode() * 49 * 49 + secondExpression.hashCode() * 53 * 53);
            case ('/') -> 907 * (firstExpression.hashCode() * 61 * 61 + secondExpression.hashCode() * 67 * 67);
            default -> 0;
        };
    }
}
