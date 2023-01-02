//207632795

import java.util.Map;
import java.util.TreeMap;

/**
 * @author ori
 * expression test class.
 */
public class ExpressionsTest {
    /**
     * main method.
     *
     * @param args args from the command line
     * @throws Exception to catch evaluate possible exception
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new Or(new Or(new Val(false), new Var("x")), new Xor(new Var("y"), new Var("z")));
        System.out.println(expression);
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", false);
        assignment.put("y", false);
        assignment.put("t", false);
        assignment.put("z", true);
        System.out.println(expression.evaluate(assignment));
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
    }
}
