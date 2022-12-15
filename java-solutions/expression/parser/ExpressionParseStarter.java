package expression.parser;

public final class ExpressionParseStarter {
    public static AbstractExpression parse(final String source) {
        return parse(new StringSource(source));
    }
    public static AbstractExpression parse(final CharSource source) {
        return new ExpressionParserMachine(source).parseExpression();
    }
    private static class ExpressionParserMachine extends BaseParser {
        public ExpressionParserMachine(CharSource source) {
            super(source);
        }
        public AbstractExpression parseExpression() {
            AbstractExpression result = parseAbstractExpression(parseElement(), "");
            while (!eof()) {
                result = parseAbstractExpression(result, "");
            }
            return result;
        }

        private AbstractExpression parseAbstractExpression(AbstractExpression leftSide, String start) {
            skipWhitespace();
            if (!eof() && ! take(')')) {
                if (take('+')) {
                    AbstractExpression rightSide = parseElement();
                    skipWhitespace();
                    if (!eof() && !take(')')) {
                        if (now('+') || now('-')) {
                            leftSide = new Add(leftSide, rightSide);
                            return parseAbstractExpression(leftSide, start);
                        } else {
                            return parseAbstractExpression(new Add(leftSide, parseAbstractExpression(rightSide, "")), start);
                        }
                    } else {
                        return new Add(leftSide, rightSide);
                    }
                }
                if (take('-')) {
                    AbstractExpression rightSide = parseElement();
                    skipWhitespace();
                    if (!eof() && !take(')')) {
                        if (now('+') || now('-')) {
                            leftSide = new Subtract(leftSide, rightSide);
                            return parseAbstractExpression(leftSide, start);
                        } else {
                            return parseAbstractExpression(new Subtract(leftSide, parseAbstractExpression(rightSide, "")), start);
                        }
                    } else {
                        return new Subtract(leftSide, rightSide);
                    }
                }
                if (take('*')) {
                    AbstractExpression rightSide = parseElement();
                    skipWhitespace();
                    if (now('*') || now('/') || start == "(") {
                        return parseAbstractExpression(new Multiply(leftSide, rightSide), start);
                    } else {
                        return new Multiply(leftSide, rightSide);
                    }
                }
                if (take('/')) {
                    AbstractExpression rightSide = parseElement();
                    skipWhitespace();
                    if (now('*') || now('/') || start == "(") {
                        return parseAbstractExpression(new Divide(leftSide, rightSide), start);
                    } else {
                        return new Divide(leftSide, rightSide);
                    }
                }
            }
            return leftSide;
        }

        private AbstractExpression parseElement() {
            skipWhitespace();
            if (between('a', 'z')) {
                return parseVariable();
            } else if (between('0', '9')) {
                return parseConst("");
            } else if (take('(')) {
                return parseAbstractExpression(parseElement(), "(");
            } else if (take('-')) {
                if (between('1', '9')) {
                    return parseConst("-");
                } else {
                    return new Minus(parseElement());
                }
            } else {
                throw error("Unsupported input: '" + take() + "' ");
            }
        }

        private Variable parseVariable() {
            return new Variable(String.valueOf(take()));
        }
        private Const parseConst(String sign) {
            StringBuilder constant = new StringBuilder(sign);
            while (between('0', '9')) {
                constant.append(take());
            }
            return new Const(Integer.parseInt(constant.toString()));
        }
    }
}
