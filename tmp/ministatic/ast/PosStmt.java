package ast;
import compiler.Position;

/** Statements that are annotated with positions.
 */
public abstract class PosStmt extends Stmt {

    protected Position pos;

    /** Default constructor.
     */
    public PosStmt(Position pos) {
        this.pos = pos;
    }

    /** Return a string describing the position/coordinates
     *  of this abstract syntax tree node.
     */
    String coordString() { return pos.coordString(); }

    protected int node(DotOutput dot, String lab, int n) {
        return dot.node(lab + "\\n" + pos.coordString(), n);
    }
}
