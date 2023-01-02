//207632795

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * @author ori zohar.
 * this is the Var class.
 */
public class Var implements Expression {
    private String var;

    /**
     * this is the constructor.
     * @param var var to put.
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.var)) {
            return assignment.get(this.var);
        } else {
            throw new Exception("Var");
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Var");
    }

    @Override
    public List<String> getVariables() {
        List<String> str = new ArrayList<>();
        str.add(this.var);
        return str;
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public Expression assign(String vari, Expression expression) {
        if (vari.equals(this.var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }
}
