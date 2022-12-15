package expression.parser;

public interface Expression {

    int evaluate(int x);

    int evaluate(int x, int y, int z);

    double evaluate(double x);
}
