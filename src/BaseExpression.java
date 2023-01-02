//207632795

/**
 * @author ori zohar
 * abstrtact class for the  Base Expression .
 */
public abstract class BaseExpression implements Expression {
    private char operator;

    /**
     * accessors method.
     *
     * @return operator
     */
    public char getOperator() {
        return this.operator;
    }

    /**
     * accessors method.
     *
     * @param sign operator to set.
     */
    public void setOperator(char sign) {
        this.operator = sign;
    }
}
