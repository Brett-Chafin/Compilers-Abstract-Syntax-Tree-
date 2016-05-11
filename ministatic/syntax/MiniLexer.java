package syntax;

import java.util.Hashtable;
import compiler.Source;
import compiler.SourceLexer;
import compiler.Handler;
import compiler.Warning;
import compiler.Failure;
import ast.Id;
import ast.IntLit;

/** A lexical analyzer.
 */
public class MiniLexer extends SourceLexer implements MiniTokens {
    /** Construct a lexical analyzer.
     */
    public MiniLexer(Handler handler, Source source) {
        super(handler, source);
    }

    /** Store the semantic value for the current token.
     */
    private Object semantic;

    /** Return the semantic value associated with the current token.
     */
    public Object getSemantic() {
      return semantic;
    }

    //- Main lexical analysis: ------------------------------------------------

    /** Read the next token and return the corresponding integer code.
     */
    public int nextToken() {
        for (;;) {
            skipWhitespace();
            markPosition();
            semantic = getPos();
            switch (c) {
                case EOF  : return token=ENDINPUT;

                // Separators:
                case '('  : nextChar();
                            return token='(';
                case ')'  : nextChar();
                            return token=')';
                case '{'  : nextChar();
                            return token='{';
                case '}'  : nextChar();
                            return token='}';
                case ';'  : nextChar();
                            return token=';';
                case ','  : nextChar();
                            return token=',';
		case '['  : nextChar();
			    return token='[';
		case ']'  : nextChar();
			    return token=']';

                // Operators:
                case '='  : nextChar();
                            if (c=='=') {
                                nextChar();
                                return token=EQL;
                            } else {
                                return token='=';
                            }

                case '!'  : nextChar();
                            if (c=='=') {
                                nextChar();
                                return token=NEQ;
                            } else {
                                return token='!';
                            }

                case '<'  : nextChar();
                            if (c=='=') {
                                nextChar();
                                return token=LTE;
                            } else {
                                return token='<';
                            }

                case '>'  : nextChar();
                            if (c=='=') {
                                nextChar();
                                return token=GTE;
                            } else {
                                return token='>';
                            }

                case '&'  : nextChar();
                            if (c=='&') {
                                nextChar();
                                return token=LAND;
                            } else {
                                return token='&';
                            }

                case '|'  : nextChar();
                            if (c=='|') {
                                nextChar();
                                return token=LOR;
                            } else {
                                return token='|';
                            }

                case '^'  : nextChar();
                            return token='^';

                case '~'  : nextChar();
                            return token='~';

                case '+'  : nextChar();
                            return token='+';

                case '-'  : nextChar();
                            return token='-';

                case '*'  : nextChar();
                            return token='*';

                case '/'  : nextChar();
                            if (c=='/') {
                                skipOneLineComment();
                            } else if (c=='*') {
                                skipBracketComment();
                            } else {
                                return token = '/';
                            }
                            continue;

                default   : if (Character.isJavaIdentifierStart((char)c)) {
                                return identifier();
                            } else if (Character.digit((char)c, 10)>=0) {
                                return number();
                            }
            }
            illegalCharacter();
            nextChar();
        }
    }

    //- Whitespace and comments -----------------------------------------------

    private boolean isWhitespace(int c) {
        return (c==' ') || (c=='\t') || (c=='\f');
    }

    private void skipWhitespace() {
        while (isWhitespace(c)) {
            nextChar();
        }
        while (c==EOL) {
            nextLine();
            while (isWhitespace(c)) {
                nextChar();
            }
        }
    }

    private void skipOneLineComment() { // Assumes c=='/'
        nextLine();
    }

    private void skipBracketComment() { // Assumes c=='*'
        nextChar();
        for (;;) {
            if (c=='*') {
                do {
                    nextChar();
                } while (c=='*');
                if (c=='/') {
                    nextChar();
                    return;
                }
            }
            if (c==EOF) {
                report(new Failure(getPos(), "Unterminated comment"));
                return;
            }
            if (c==EOL) {
                nextLine();
            } else {
                nextChar();
            }
        }
    }

    //- Identifiers, keywords, boolean and null literals ----------------------

    private int identifier() {          // Assumes isJavaIdentifierStart(c)
        int start = col;
        do {
            nextChar();
        } while (c!=EOF && Character.isJavaIdentifierPart((char)c));
        lexemeText = line.substring(start, col);

        Integer kw = reserved.get(lexemeText);
        if (kw!=null) {
            semantic = getPos();
            return token=kw.intValue();
        }
        semantic = new Id(getPos(), lexemeText);
        return token=IDENT;
    }

    private static Hashtable<String, Integer> reserved;
    static {
        reserved = new Hashtable<String, Integer>();
        reserved.put("int",     new Integer(INT));
        reserved.put("boolean", new Integer(BOOLEAN));
        reserved.put("if",      new Integer(IF));
        reserved.put("else",    new Integer(ELSE));
        reserved.put("while",   new Integer(WHILE));
        reserved.put("print",   new Integer(PRINT));
        reserved.put("true",    new Integer(TRUE));
        reserved.put("false",   new Integer(FALSE));

	//new code
	reserved.put("for", 	new Integer(FOR));
	reserved.put("new", 	new Integer(NEW));
    }

    //- Numeric integer literals ----------------------------------------------

    /** Read an integer literal.
     */
    private int number() {              // Assumes c is a digit
        int num = 0;
        int d = Character.digit((char)c, 10);
        do {
            num = 10*num + d;
            nextChar();
            d = Character.digit((char)c, 10);
        } while (d>=0);
        semantic = new IntLit(getPos(), num);
        return token=INTLIT;
    }

    //- Error reporting: ------------------------------------------------------

    private void illegalCharacter() {
        report(new Warning(getPos(), "Ignoring illegal character"));
    }
}
