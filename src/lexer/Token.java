/**
 * A token class - creates tokens from the parsed information read through the lexer.
 */

package lexer;

public class Token {

//    public static enum Type {
//        L_PAREN, R_PAREN, L_CURLY_BRACE, R_CURLY_BRACE, SEMICOLON, COLON, COMMA, EQUALS, G_EQUALS, L_EQUALS, NOT_EQUALS,
//        GREATER_THAN, LESS_THAN,
//        PLUS, MINUS, TIMES, DIVIDE, INTEGER, FLOAT, STRING
//    }
    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }
}
