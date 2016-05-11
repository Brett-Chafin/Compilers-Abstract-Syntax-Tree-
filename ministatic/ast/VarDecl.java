package ast;
import compiler.Position;

/** Abstract syntax for variable declarations.
 */
public class VarDecl extends InitStmt {

    /** The type of the declared variables.
     */
    private Type type;

    /** The names of the declared variables.
     */
    private VarIntro[] intros;

    /** The number of slots in intros that are used.
     */
    private int used;

    /** Construct a variable declaration with a given type and
     *  a specific variable introduction.
     */
    public VarDecl(Type type, VarIntro intro) {
        this.type   = type;
        this.intros = new VarIntro[] { intro };
        this.used   = 1;
    }

    /** Extend this variable declaration with an additional variable
     *  introduction.
     */
    public void addIntro(VarIntro intro) {
        if (used>=intros.length) {
            VarIntro[] newIntros = new VarIntro[2*intros.length];
            for (int i=0; i<intros.length; i++) {
                newIntros[i] = intros[i];
            }
            intros = newIntros;
        }
        intros[used++] = intro;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "VarDecl");
        out.indent(n+1, type.toString());
        for (int i=0; i<intros.length; i++) {
           intros[i].indent(out, n+1);
        }
    }

    /** Print this InitStmt at the current indentation, no line breaks.
     */
    public void print(TextOutput out) {
        out.print(type.toString());
        out.print(" ");
        for (int i=0; i<used; i++) {
           if (i>0) {
               out.print(", ");
           }
           intros[i].print(out);
        }
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        int c = type.toDot(dot, n, "type", node(dot, "VarDecl", n));
        for (int i=0; i<used; i++) {
            c = intros[i].toDot(dot, n, "" + i, c);
        }
        return c;
    }

    /** Run scope analysis on this statement.  The scoping parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment at the start of the statement.  The
     *  return result is the environment at the end of the statement.
     */
    public Env analyze(ScopeAnalysis scoping, Env env) {
        for (int i=0; i<used; i++) {
            env = intros[i].extend(scoping, env, type);
        }
        return env;
    }

    /** Generate a dot description for the environment structure of this
     *  program.
     */
    public void dotEnv(DotEnvOutput dot) {
        for (int i=0; i<used; i++) {
            intros[i].dotEnv(dot);
        }
    }

    /** Run type checker on this statement.  The typing parameter
     *  provides access to the scope analysis phase (specifically,
     *  to the associated error handler).
     */
    public void analyze(TypeAnalysis typing) {
        for (int i=0; i<used; i++) {
            intros[i].analyze(typing, type);
        }
    }

    /** Run initialization analysis on this statement.  The init
     *  parameter provides access to an initialization analysis phase
     *  object (specifically, to an associated error handler).  The
     *  initialized parameter is the set of variables (each represented
     *  by pointers to environment entries) that have definitely been
     *  initialized before this statement is executed.
     */
    public VarSet analyze(InitAnalysis init, VarSet initialized) {
        for (int i=0; i<used; i++) {
            initialized = intros[i].analyze(init, initialized);
        }
        return initialized;
    }
}
