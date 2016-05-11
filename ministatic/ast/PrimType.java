package ast;
import compiler.Position;

public class PrimType extends Type {

    /** A string that identifies this primitive
     *  type.
     */
    private String typename;

    /** Default constructor.
     */
    public PrimType(String typename) {
        this.typename = typename;
    }

    /** Generate a printable name for this type.
     */
    public String toString() {
        return typename;
    }

    /** Test two types to see if they are equal.
     */
    public boolean equal(Type that) {
        return that.equalPrimType(this);
    }

    /** Test this type to see if it is equal to the given primitive type.
     */
    public boolean equalPrimType(PrimType that) {
        return this==that || this.typename.equals(that.typename);
    }

    /** Test this type to see if it is equal to the given array type.
     */
    public boolean equalArrayType(ArrayType that) {
        return false;
    }
}
