package ast;
import compiler.Position;

public class ArrayType extends Type {

    /** The type of the elements that are stored
     *  in an array of this type.
     */
    private Type elem;

    /** Default constructor.
     */
    public ArrayType(Type elem) {
        this.elem = elem;
    }

    /** Generate a printable name for this type.
     */
    public String toString() {
        return elem + "[]";
    }

    /** Test two types to see if they are equal.
     */
    public boolean equal(Type that) {
        return that.equalArrayType(this);
    }

    /** Test this type to see if it is equal to the given primitive type.
     */
    public boolean equalPrimType(PrimType that) {
        return false;
    }

    /** Test this type to see if it is equal to the given array type.
     */
    public boolean equalArrayType(ArrayType that) {
        return this==that || this.elem.equal(that.elem);
    }

    /** Return the element type of this array type, or null if this
     *  is not an array type.
     */
    public Type elemType() {
        return elem;
    }
}
