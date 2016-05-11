package ast;
import compiler.Position;

/** Abstract syntax for statements that evaluate an expression
 *  and discard the result that is produced; such statements are
 *  only useful in practice if the expression has some potential
 *  side-effect, such as an assignment or a function call.
 */
public class ExprStmt extends StepStmt {

    /** The expression to be evaluated,
     *  discarding any result that is produced.
     */
    private Expr expr;

    /** Default constructor.
     */
    public ExprStmt(Expr expr) {
        this.expr = expr;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "ExprStmt");
        expr.indent(out, n+1);
    }

    /** Print this InitStmt at the current indentation, no line breaks.
     */
    public void print(TextOutput out) {
        expr.print(out);
      }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return expr.toDot(dot, n, "expr",
               node(dot, "ExprStmt", n));
    }

    /** Run scope analysis on this statement.  The scoping parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment at the start of the statement.  The
     *  return result is the environment at the end of the statement.
     */
    public Env analyze(ScopeAnalysis scoping, Env env) {
        expr.analyze(scoping, env);
        return env;
    }

    /** Generate a dot description for the environment structure of this
     *  program.
     */
    public void dotEnv(DotEnvOutput dot) {
        /* nothing to do here */
    }

    /** Run type checker on this statement.  The typing parameter
     *  provides access to the scope analysis phase (specifically,
     *  to the associated error handler).
     */
    public void analyze(TypeAnalysis typing) {
        expr.analyze(typing);
    }

    /** Run initialization analysis on this statement.  The init
     *  parameter provides access to an initialization analysis phase
     *  object (specifically, to an associated error handler).  The
     *  initialized parameter is the set of variables (each represented
     *  by pointers to environment entries) that have definitely been
     *  initialized before this statement is executed.
     */
    public VarSet analyze(InitAnalysis init, VarSet initialized) {
        return expr.analyze(init, initialized);
    }
}
