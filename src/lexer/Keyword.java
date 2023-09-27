package lexer;

public class Keyword extends Token{
    public final String lexeme;

    public Keyword(int tag, String lexeme) {
        super(tag);
        this.lexeme = lexeme;
    }

    public static final Keyword L_PAREN = new Keyword(Tag.L_PAREN, "("),
        R_PAREN = new Keyword(Tag.R_PAREN, ")"),
        L_CURLY_BRACE = new Keyword(Tag.L_CURLY_BRACE, "{"),
        R_CURLY_BRACE = new Keyword(Tag.R_CURLY_BRACE, "}"),
        SEMICOLON = new Keyword(Tag.SEMICOLON, ";"),
        COLON = new Keyword(Tag.COLON, ":"),
        COMMA = new Keyword(Tag.COMMA, ","),
        EQUALS = new Keyword(Tag.EQUALS, "=="),
        G_EQUALS = new Keyword(Tag.G_EQUALS, ">="),
        L_EQUALS = new Keyword(Tag.L_EQUALS, "<="),
        NOT_EQUALS = new Keyword(Tag.NOT_EQUALS, "!="),
        GREATER_THAN = new Keyword(Tag.GREATER_THAN, ">"),
        LESS_THAN = new Keyword(Tag.LESS_THAN, "<"),
        PLUS = new Keyword(Tag.PLUS, "+"),
        MINUS = new Keyword(Tag.MINUS, "-");
}
