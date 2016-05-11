package ast;
import compiler.Position;

/** Abstract syntax for statements that can be used as for loop
 *  step or tests (i.e., either an expression to be evaluate or
 *  else nothing at all).
 */
public abstract class StepStmt extends InitStmt {

	abstract void require(TypeAnalysis typing, Type type); 
}
