//207632795

/**
 * @author ori zohar.
 * this is the Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * this is the constructor.
     *
     * @param e1 first expression
     * @param e2 second expression
     */
    public Xor(Expression e1, Expression e2) {
        super(e1, e2, '^');
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    /*
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getFirstExpression().evaluate(assignment) ^ getSecondExpression().evaluate(assignment);
    }
    @Override
    public Boolean evaluate() throws Exception {
        return super.getFirstExpression().evaluate() ^ super.getSecondExpression().evaluate();
    }
*/
    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return (x ^ y);
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try {
            //if the first simplified  expression is true
            if (firstSimplified.evaluate()) {
                return new Not(super.getSecondExpression()).simplify();
                //check if simplified first expression is false
            } else if (!firstSimplified.evaluate()) {
                return secondSimplified;
            }
        } catch (Exception e) {
        }
        try {
            //if the first simplified  expression is true
            if (secondSimplified.evaluate()) {
                return new Not(super.getFirstExpression()).simplify();
                //check if simplified second expression is false
            } else if (!secondSimplified.evaluate()) {
                return firstSimplified;
            }
        } catch (Exception e) {

        } //check if both are the same expression
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return new Val(false);
        }
        return new Xor(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }
}
