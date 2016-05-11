package ast;
import compiler.Position;
import compiler.Failure;

/** Abstract syntax for an array access.
 */
public class ArrayAccess extends Expr {

    /** An expression that evaluates to an array.
     */
    protected Expr arr;

    /** An expression that evaluates to an array index.
     */
    protected Expr idx;

    /** Default constructor.
     */
    public ArrayAccess(Position pos, Expr arr, Expr idx) {
        super(pos);
        this.arr = arr;
        this.idx = idx;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "ArrayAccess");
        arr.indent(out, n+1);
        idx.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) {
        arr.print(out);
        out.print("[");
        idx.print(out);
        out.print("]");
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return idx.toDot(dot, n, "idx",
               arr.toDot(dot, n, "arr",
               dot.node("ArrayAccess", n)));
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

	//Made sure everything in the index is in scope
	idx.analyze(scoping, env);
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
        
	//Make sure the the index expr evaluats to an Integer Value
	idx.require(typing, Type.INT);
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
