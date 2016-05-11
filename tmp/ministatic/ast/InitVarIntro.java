package ast;
import compiler.Position;

/** Represents a variable introduction with an initializer
 *  expression.
 */
public class InitVarIntro extends VarIntro {

    /** The expression whose value will be used as the
     *  initial value for this variable.
     */
    private Expr expr;

    /** Default constructor.
     */
    public InitVarIntro(Id id, Expr expr) {
        super(id);
        this.expr = expr;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "InitVarIntro");
        id.indent(out, n+1);
        expr.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this variable
     *  introduction using the concrete syntax of the mini
     *  programming language.
     */
    public void print(TextOutput out) {
        super.print(out);
        out.print(" = ");
        expr.print(out);
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return expr.toDot(dot, n, "expr",
               dot.node("InitVarIntro: " + id, n));
    }

    /** Run scope analysis on this variable introduction.
     */
    public Env extend(ScopeAnalysis scoping, Env env, Type type) {
        expr.analyze(scoping, env);
        return id.extend(type, env);
     }

    /** Run type checker on this variable introduction
     *  using the given declared type.
     */
    public void analyze(TypeAnalysis typing, Type type) {
        expr.require(typing, type);
        id.setType(type);
    }

    /** Run initialization analysis on this statement.  The init
     *  parameter provides access to an initialization analysis phase
     *  object (specifically, to an associated error handler).  The
     *  initialized parameter is the set of variables (each represented
     *  by pointers to environment entries) that have definitely been
     *  initialized before this statement is executed.
     */
    public VarSet analyze(InitAnalysis init, VarSet initialized) {
        return id.addTo(expr.analyze(init, initialized));
    }
}
