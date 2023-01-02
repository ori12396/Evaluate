//207632795

/**
 * @author ori zohar.
 * this is Xnor class.
 */
public class Xnor extends BinaryExpression {
    /**
     * this the constructor.
     *
     * @param e1 first expression
     * @param e2 second expression
     */
    public Xnor(Expression e1, Expression e2) {
        super(e1, e2, '#');
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try { // check if same value
            if (firstSimplified.toString().equals(secondSimplified.toString())) {
                return new Val(true);
                // if T#F or F#T
            } else if ((firstSimplified.evaluate() && !secondSimplified.evaluate())
                    || (!firstSimplified.evaluate() && secondSimplified.evaluate())) {
                return new Val(false);
            }
        } catch (Exception e) {

        }
        return new Xnor(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return (x == y);
    }
}
