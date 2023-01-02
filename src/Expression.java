//207632795

import java.util.Map;
import java.util.List;


/**
 * @author ori
 * Expression interace
 */
public interface Expression {
    /**
     * this method return the boolean value of the expresion.
     * @param assignment the map
     * @return the boolean value
     * @throws Exception if there unrecognized var
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * this method return the boolean value of the expresion.
     * @return the boolean value
     * @throws Exception Exception if there unrecognized var
     */
    Boolean evaluate() throws Exception;

    /**
     * this method return a list of all the variables.
     * @return list of all the variable
     */
    List<String> getVariables();

    /**
     * this method for print the expression.
     * @return the expression as a string.
     */
    String toString();

    /**
     * this method use to replace the var to new expression.
     * @param var a var to replace as a string.
     * @param expression the expression that replace the var
     * @return the new expression after the replacement.
     */
    Expression assign(String var, Expression expression);

    /**
     * this method use to make new expression that use only NAND gate.
     * @return new NAND Expression
     */
    Expression nandify();

    /**
     * this method use to make new epxression that use only NOR gate.
     * @return new NOR expression.
     */
    Expression norify();

    /**
     * this method use the simplify the expression as much as possible.
     * @return new simplified expression.
     */
    Expression simplify();
}