//207632795

import java.util.ArrayList;
import java.util.List;

/**
 * @author ori zohar
 * this is the unary expreesion class.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression x;

    /**
     * this is the constructor.
     *
     * @param e        expression
     * @param operator operator to set
     */
    public UnaryExpression(Expression e, char operator) {
        this.x = e;
        super.setOperator(operator);
    }

    /**
     * accessors method.
     *
     * @return x
     */
    public Expression getX() {
        return this.x;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(this.x.getVariables());
    }

    @Override
    public String toString() {
        return super.getOperator() + "(" + this.x + ")";
    }

    @Override
    public Expression nandify() {
        return new Nand(this.x.nandify(), this.x.nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(this.x.norify(), this.x.norify());
    }

}
