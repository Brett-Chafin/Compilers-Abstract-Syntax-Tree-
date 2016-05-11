package ast;
import compiler.Position;

/** Abstract syntax for  basic types.
 */
public abstract class Type {

    /** Represents the type of integers.
     */
    public static final Type INT = new PrimType("int");

    /** Represents the type of booleans.
     */
    public static final Type BOOLEAN = new PrimType("boolean");

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
        return dot.node(toString(),n);
    }

    /** Test two types to see if they are equal.
     */
    public abstract boolean equal(Type that);

    /** Test this type to see if it is equal to the given primitive type.
     */
    public abstract boolean equalPrimType(PrimType that);

    /** Test this type to see if it is equal to the given array type.
     */
    public abstract boolean equalArrayType(ArrayType that);

    /** Return the element type of this array type, or null if this
     *  is not an array type.
     */
    public Type elemType() {
        return null;
    }

    /** Return a string that assigns a dot color for the type of this node.
     */
    public static String color(Type type) {
        return (type==Type.INT)     ? "lemonchiffon"
             : (type==Type.BOOLEAN) ? "plum"
             :                        "lightsalmon";
    }
}
