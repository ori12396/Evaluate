//207632795

/**
 * @author ori zohar
 * this is And class.
 */
public class And extends BinaryExpression {
    /**
     * this is the constructor.
     *
     * @param x first expression.
     * @param y second expression.
     */
    public And(Expression x, Expression y) {
        super(x, y, '&');
    }

    /*
        @Override
        public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
            return super.getFirstExpression().evaluate(assignment) & super.getSecondExpression().evaluate(assignment);
        }

        @Override
        public Boolean evaluate() throws Exception {
            return super.getFirstExpression().evaluate() & super.getSecondExpression().evaluate();
        }
    */
    @Override
    public Expression assign(String var, Expression expression) {
        return new And(super.getFirstExpression().assign(var, expression),
                super.getSecondExpression().assign(var, expression));
    }

    /**
     * this method get the current operator.
     *
     * @return 'and' operator
     */
    public char getOperator() {
        return '&';
    }

    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return (x & y);
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try { //check if simplified first expression is true
            if (firstSimplified.evaluate()) {
                return secondSimplified;
                //check if simplified first expression is false
            } else if (!firstSimplified.evaluate()) {
                return new Val(false);
            }
        } catch (Exception e) {
        }
        try { //check if simplified second expression is true
            if (secondSimplified.evaluate()) {
                return firstSimplified;
                //check if simplified second expression is true
            } else if (!secondSimplified.evaluate()) {
                return new Val(false);
            }
        } catch (Exception e) {
        } //if they both var
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return firstSimplified;
        }
        return new And(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }
}
