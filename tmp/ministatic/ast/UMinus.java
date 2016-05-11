package ast;
import compiler.Position;

/** Abstract syntax for unary minus expressions.
 */
public class UMinus extends UnArithExpr {

    /** Default constructor.
     */
    public UMinus(Position pos, Expr exp) {
        super(pos, exp);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "UMinus"; }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) { unary(out, "-"); }
}
