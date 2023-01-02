//207632795

/**
 * @author ori zohar
 * this is Nor class.
 */
public class Nor extends BinaryExpression {
    /**
     * this is the constructor.
     *
     * @param e1 first expression
     * @param e2 second expression
     */
    public Nor(Expression e1, Expression e2) {
        super(e1, e2, 'V');
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try {
            //check if first simplified expression is false
            if (!firstSimplified.evaluate()) {
                return new Not(super.getSecondExpression()).simplify();
                //check if the first simplified Expression is true
            } else if (firstSimplified.evaluate()) {
                return new Val(false);
            }
        } catch (Exception e) {
        }
        try {
            //check if the second simplified expression is false
            if (!secondSimplified.evaluate()) {
                return new Not(super.getFirstExpression()).simplify();
                //check if second simplified expression is true
            } else if (secondSimplified.evaluate()) {
                return new Val(false);
            }
        } catch (Exception e) {
        }
        //if the same var
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Not(firstSimplified);
        }
        return new Nor(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }

    /*
        public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
            return getFirstExpression().evaluate(assignment) || getSecondExpression().evaluate(assignment);
        }

        @Override
        public Boolean evaluate() throws Exception {
            return super.getFirstExpression().evaluate() || super.getSecondExpression().evaluate();
        }
    */
    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return !(x || y);
    }
}
