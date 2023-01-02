//207632795

/**
 * @author ori zohar
 * Nand calss.
 */
public class Nand extends BinaryExpression {
    /**
     * this is the constructor.
     *
     * @param e1 first expression
     * @param e2 second expression
     */
    public Nand(Expression e1, Expression e2) {
        super(e1, e2, 'A');
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try {
            //check if he first simplified expression is true
            if (firstSimplified.evaluate()) {
                return new Not(super.getSecondExpression()).simplify();
                //check if the first simplified expression is false
            } else if (!firstSimplified.evaluate()) {
                return new Val(true);
            }
        } catch (Exception e) {
        }
        try {
            // check if the second simplified expression is true
            if (secondSimplified.evaluate()) {
                return new Not(super.getFirstExpression()).simplify();
                //check if the second simplified expression is false
            } else if (!secondSimplified.evaluate()) {
                return new Val(true);
            }
        } catch (Exception e) {
        } //if the same var
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Not(firstSimplified);
        }
        return new Nand(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }

    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return !(x & y);
    }
}
