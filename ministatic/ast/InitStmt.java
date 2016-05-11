package ast;
import compiler.Position;

/** Abstract syntax for statements that can be used as for loop
 *  initializers.
 */
public abstract class InitStmt extends Stmt {

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out, int n) {
        out.indent(n);
        this.print(out);
        out.println(";");
    }

    /** Print this InitStmt at the current indentation, no line breaks.
     */
    public abstract void print(TextOutput out);
}
