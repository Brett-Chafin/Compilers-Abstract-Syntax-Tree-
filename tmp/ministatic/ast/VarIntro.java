package ast;
import compiler.Position;

/** Represents a variable introduction with a single variable
 *  name.
 */
public class VarIntro {

    /** The name of the variable that is being introduced.
     */
    protected Id id;

    /** Default constructor.
     */
    public VarIntro(Id id) {
        this.id = id;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "VarIntro");
        id.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this variable
     *  introduction using the concrete syntax of the mini
     *  programming language.
     */
    public void print(TextOutput out) { out.printDef(id); }

    /** Output a description of this node (with id n), including a
     *  link to its parent node (with id p) and returning the next
     *  available node id.
     */
    public int toDot(DotOutput dot, int p, String attr, int n) {
        dot.join(p, n, attr);
        return toDot(dot, n);
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return dot.node("VarIntro: " + id, n);
    }

    /** Run scope analysis on this variable introduction.
     */
    public Env extend(ScopeAnalysis scoping, Env env, Type type) {
        return id.extend(type, env);
     }

    /** Generate a dot description for the environment structure of this
     *  program.
     */
    public void dotEnv(DotEnvOutput dot) {
        id.dotEnv(dot);
    }

    /** Run type checker on this variable introduction
     *  using the given declared type.
     */
    public void analyze(TypeAnalysis typing, Type type) {
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
        return initialized; // No evaluation or initialization here
    }
}
