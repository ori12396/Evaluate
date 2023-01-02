//207632795

import java.util.Map;

/**
 * @author ori zohar.
 * this is Not class.
 */
public class Not extends UnaryExpression {

    /**
     * this is the constructor.
     *
     * @param e expression
     */
    public Not(Expression e) {
        super(e, '~');
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.getX().evaluate(assignment));
    }

    @Override
    public Expression simplify() {
        try { // check if expression is true
            if (super.getX().simplify().evaluate()) {
                return new Val(false);
            } else {
                return new Val(true);
            }
        } catch (Exception e) {
        } //if expression if false
        return new Not(super.getX().simplify());
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(super.getX().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getX().assign(var, expression));
    }
}
