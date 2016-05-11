package ast;
import compiler.Position;

/** Abstract syntax for a new array operation.
 */
public class NewArray extends Expr {

    /** The type of values that will be stored in the array.
     */
    private Type type;

    /** An expression whose value will determine how big
     *  the new array should be.
     */
    private Expr size;

    /** Default constructor.
     */
    public NewArray(Position pos, Type type, Expr size) {
        super(pos);
        this.type = type;
        this.size = size;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "NewArray of " + type);
        size.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) {
        out.print("new ");
        out.print(type.toString());
        out.print("[");
        size.print(out);
        out.print("]");
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return size.toDot(dot, n, "size",
               dot.node("NewArray of " + type, n));
    }

    /** Run scope analysis on this expression.  The scoping parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment in which the expression is evaluated.
     *  Unlike scope analysis for statements, there is no return
     *  result here: an expression cannot introduce new variables in
     *  to a program, so the final environment will always be the same
     *  as the initial environment.
     */
    public void analyze(ScopeAnalysis scoping, Env env) {
        // TODO: replace this with correct code!
    }

    /** Run type checking analysis on this expression.  The typing parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment in which the expression is evaluated.
     *  Unlike scope analysis for statements, there is no return
     *  result here: an expression cannot introduce new variables in
     *  to a program, so the final environment will always be the same
     *  as the initial environment.
     */
    public Type analyze(TypeAnalysis typing) {
        return null; // TODO: replace this with correct code!
    }

    /** Run initialization analysis on this expression.  The init parameter
     *  provides access to the initialization analysis phase (in particular,
     *  to the associated error handler), and the initialized parameter
     *  reflects the set of variables that are known to have been initialized
     *  before this expression is evaluated.  The return result is the set of
     *  variables that are known to be initialized after the expression has
     *  been evaluated.  For expressions that have no side effects, the set
     *  that is returned will be the same as the set that is passed in as
     *  an input.
     */
    public VarSet analyze(InitAnalysis init, VarSet initialized) {
        return null; // TODO: replace this with correct code!
    }
}
