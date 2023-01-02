//207632795

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ori zohar
 * abstract class for Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression x, y;

    /**
     * accessors method.
     *
     * @return first expression.
     */
    public Expression getFirstExpression() {
        return this.x;
    }

    /**
     * accessors method.
     *
     * @return second expression.
     */
    public Expression getSecondExpression() {
        return this.y;
    }


    /**
     * this is the constructor.
     *
     * @param e1       first expression
     * @param e2       second expression
     * @param operator the operator sign.
     */
    public BinaryExpression(Expression e1, Expression e2, char operator) {
        this.x = e1;
        this.y = e2;
        super.setOperator(operator);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return byOperator(this.x.evaluate(assignment), this.y.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return byOperator(this.x.evaluate(), this.y.evaluate());
    }

    /**
     * this function return the boolean that received from activate the the appropriate gate.
     *
     * @param first  first boolean
     * @param second second boolean
     * @return boolean value.
     * @throws Exception if activate on binary itself.
     */
    public boolean byOperator(boolean first, boolean second) throws Exception {
        throw new Exception("Not implement");
    }


    @Override
    public List<String> getVariables() {
        List<String> firstList = this.x.getVariables();
        List<String> secondList = this.y.getVariables();
        List<String> varList = new ArrayList<>(firstList);
        //run all over the second list.
        for (String s : secondList) {
            //check the the current var is not already on the list.
            if (!varList.contains(s)) {
                varList.add(s);
            }
        }
        return varList;
    }

    @Override
    public String toString() {
        char operator = super.getOperator();
        return "(" + this.x.toString() + " " + operator + " " + this.y.toString() + ")";
    }

    @Override
    public Expression nandify() {
        char operator = super.getOperator();
        //if and-operator
        if (operator == '&') {
            return new Nand(new Nand(this.x, this.y).nandify(), new Nand(this.x, this.y).nandify());
            //check if or operator
        } else if (operator == '|') {
            return new Nand(new Nand(this.x, this.x).nandify(), new Nand(this.y, this.y).nandify());
            //check if nor operator
        } else if (operator == 'V') {
            return new Nand(new Nand(new Nand(this.x, this.x), new Nand(this.y, this.y)).nandify(),
                    new Nand(new Nand(this.x, this.x), new Nand(this.y, this.y)).nandify());
            //check if xor operator
        } else if (operator == '^') {
            return new Nand(new Nand(this.x, new Nand(this.x, this.y)).nandify(),
                    new Nand(this.y, new Nand(this.x, this.y)).nandify());
            //check if xnor operator
        } else if (operator == '#') {
            return new Nand(new Nand(new Nand(this.x, this.x).nandify(),
                    new Nand(this.y, this.y)).nandify(), new Nand(this.x, this.y).nandify());
            //if its NAND operator
        }
        return new Nand(this.x.nandify(), this.y.nandify());
    }

    @Override
    public Expression norify() {
        char operator = super.getOperator();
        //check if or operator
        if (operator == '|') {
            return new Nor(new Nor(this.x, this.y).norify(), new Nor(this.x, this.y).norify());
            //check if and operator
        } else if (operator == '&') {
            return new Nor(new Nor(this.x, this.x).norify(), new Nor(this.y, this.y).norify());
            //check if nand operator
        } else if (operator == 'A') {
            return new Nor(new Nor(new Nor(this.x, this.x), new Nor(this.y, this.y)).norify(),
                    new Nor(new Nor(this.x, this.x), new Nor(this.y, this.y)).norify());
            //check if xnor operator
        } else if (operator == '#') {
            return new Nor(new Nor(this.x, new Nor(this.x, this.y)).norify(),
                    new Nor(this.y, new Nor(this.x, this.y)).norify());
            //check if xor operator
        } else if (operator == '^') {
            return new Nor(new Nor(new Nor(this.x, this.x).norify(),
                    new Nor(this.y, this.y).norify()), new Nor(this.x, this.y).norify());
        } //if not operator
        return new Nor(this.x.norify(), this.y.norify());
    }
}
