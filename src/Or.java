//207632795

import java.util.Map;

/**
 * @author ori zohar
 * this is Or class.
 */
public class Or extends BinaryExpression {

    /**
     * this is the constructor.
     *
     * @param x first expression
     * @param y second expression.
     */
    public Or(Expression x, Expression y) {
        super(x, y, '|');
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //return getFirstExpression().evaluate(assignment) || getSecondExpression().evaluate(assignment);
        boolean first = super.getFirstExpression().evaluate(assignment),
                second = super.getSecondExpression().evaluate(assignment);
        return first || second;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return super.getFirstExpression().evaluate() || super.getSecondExpression().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(super.getFirstExpression().assign(var, expression),
                getSecondExpression().assign(var, expression));
    }

    @Override
    public Expression simplify() {
        Expression firstSimplified = super.getFirstExpression().simplify(),
                secondSimplified = super.getSecondExpression().simplify();
        try { //check if simplified first expression is false
            if (!firstSimplified.evaluate()) {
                return secondSimplified;
                //check if simplified first expression is true
            } else if (firstSimplified.evaluate()) {
                return new Val(true);
            }
        } catch (Exception e) {
        }
        try { //check if simplified second expression is false
            if (!secondSimplified.evaluate()) {
                return firstSimplified;
                //check if simplified second expression is true
            } else if (secondSimplified.evaluate()) {
                return new Val(true);
            }
        } catch (Exception e) {
        } //if they both  same var
        if (firstSimplified.toString().equals(secondSimplified.toString())) {
            return firstSimplified;
        }
        return new Or(super.getFirstExpression().simplify(), super.getSecondExpression().simplify());
    }

    /**
     * this method get the current operator.
     *
     * @return 'or' operator
     */
    public char getOperator() {
        return '|';
    }

    @Override
    public boolean byOperator(boolean x, boolean y) throws Exception {
        return (x || y);
    }
}
