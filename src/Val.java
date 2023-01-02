//207632795

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * @author ori zohar
 * this is val class.
 */
public class Val implements Expression {
    private boolean val;

    /**
     * this is the constructor.
     * @param val val to enter.
     */
    public Val(boolean val) {
        this.val = val;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.val;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.val;
    }

    @Override
    public List<String> getVariables() {
        List<String> emptyList = new ArrayList<>();
        return emptyList;
    }

    @Override
    public String toString() {
        if (this.val) {
            return "T";
        }
        return "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }
}

