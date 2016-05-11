package ast;
import compiler.Position;

/** Abstract syntax for logical or expressions (||).
 */
public class LOr extends BinLogicExpr {

    /** Default constructor.
     */
    public LOr(Position pos, Expr left, Expr right) {
        super(pos, left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "LOr"; }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) { binary(out, "||"); }
}
