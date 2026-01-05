public class sym {

    public static final int EOF        = 0;

    // KLJUČNE REČI
    public static final int PROGRAM    = 1;
    public static final int VAR        = 2;
    public static final int INTEGER    = 3;
    public static final int CHAR       = 4;
    public static final int BEGIN      = 5;
    public static final int END        = 6;
    public static final int READ       = 7;
    public static final int WRITE      = 8;
    public static final int IF         = 9;
    public static final int THEN       = 10;
    public static final int ELSE       = 11;

    // OPŠTI TOKENI
    public static final int ID         = 12;
    public static final int CONST      = 13;

    // OPERATORI
    public static final int PLUS       = 14;
    public static final int MUL        = 15;
    public static final int MINUS      = 16;
    public static final int ASSIGN     = 17;

    // ZAGRADE
    public static final int LEFTPAR    = 18;
    public static final int RIGHTPAR   = 19;

    // SEPARATORI
    public static final int SEMICOLON  = 20;
    public static final int COMMA      = 21;
    public static final int DOT        = 22;
    public static final int COLON      = 23;
    public static final int STRING = 24;
    public static final int FILE   = 25;
    public static final int DO     = 26;
    public static final int IN     = 27;
    public static final int RETURN = 28;
}
