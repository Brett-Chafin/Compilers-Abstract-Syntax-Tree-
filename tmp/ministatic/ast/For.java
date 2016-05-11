package ast;
import compiler.Position;

/** Abstract syntax for "for" loops.
 */
public class For extends PosStmt {

    /** The initializer statement.
     */
    InitStmt first;

    /** The test statement, to determine if the
     *  loop body should be executed.
     */
    StepStmt test;

    /** The step statement, executed immediately
     *  before the next loop iteration.
     */
    StepStmt step;

    /** The body of the loop.
     */
    Stmt body;

    /** Default constructor.
     */
    public For(Position pos, InitStmt first, StepStmt test, StepStmt step, Stmt body) {
        super(pos);
        this.first = first;
        this.test = test;
        this.step = step;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "For");
        first.indent(out, n+1);
        test .indent(out, n+1);
        step .indent(out, n+1);
        body .indent(out, n+1);
    }

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out, int n) {
        out.indent(n);
        out.print("for (");
        first.print(out);
        out.print("; ");
        test.print(out);
        out.print("; ");
        step.print(out);
        out.print(")");
        body.printElse(out, n);
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return body .toDot(dot, n, "body",
               step .toDot(dot, n, "step",
               test .toDot(dot, n, "test",
               first.toDot(dot, n, "first",
               node(dot, "For", n)))));
    }

    /** Run scope analysis on this statement.  The scoping parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment at the start of the statement.  The
     *  return result is the environment at the end of the statement.
     */
    public Env analyze(ScopeAnalysis scoping, Env env) {
        return null; // TODO: replace this with correct code!
    }

    /** Generate a dot description for the environment structure of this
     *  program.
     */
    public void dotEnv(DotEnvOutput dot) {
        first.dotEnv(dot);
        test .dotEnv(dot);
        step .dotEnv(dot);
        body .dotEnv(dot);
    }

    /** Run type checker on this statement.  The typing parameter
     *  provides access to the scope analysis phase (specifically,
     *  to the associated error handler).
     */
    public void analyze(TypeAnalysis typing) {
        // TODO: replace this with correct code!
    }

    /** Run initialization analysis on this statement.  The init
     *  parameter provides access to an initialization analysis phase
     *  object (specifically, to an associated error handler).  The
     *  initialized parameter is the set of variables (each represented
     *  by pointers to environment entries) that have definitely been
     *  initialized before this statement is executed.
     */
    public VarSet analyze(InitAnalysis init, VarSet initialized) {
        return null; // TODO: replace this with correct code!
    }
}
