package ast;
import compiler.Position;

/** Abstract syntax for unary expressions.
 */
public abstract class UnExpr extends Expr {

    /** The operand of a unary operator expression.
     */
    protected Expr exp;

    /** Default constructor.
     */
    public UnExpr(Position pos, Expr exp) {
        super(pos);
        this.exp = exp;
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    abstract String label();

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, label());
        exp.indent(out, n+1);
    }

    /** Print out this unary expression.
     */
    protected void unary(TextOutput out, String op) {
        out.print(op);
        exp.parenPrint(out);
    }

    /** Print out this expression, wrapping it in parentheses if the
     *  expression includes a binary or unary operand.
     */
    public void parenPrint(TextOutput out) {
        out.print("(");
        this.print(out);
        out.print(")");
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return exp.toDot(dot, n, "exp",
               node(dot, label(), n));
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
        exp.analyze(scoping, env);
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
        return exp.analyze(init, initialized);
    }
}
