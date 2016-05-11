package ast;
import compiler.Position;

/** Abstract syntax for bitwise exclusive or expressions (^).
 */
public class BXor extends BinBitwiseExpr {

    /** Default constructor.
     */
    public BXor(Position pos, Expr left, Expr right) {
        super(pos, left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "BXor"; }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) { binary(out, "^"); }
}
