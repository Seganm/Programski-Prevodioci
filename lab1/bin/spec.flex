
%%

%class MPLexer
%function next_token
%line
%column
%debug
%type Yytoken

%eofval{
    return new Yytoken(sym.EOF, null, yyline, yycolumn);
%eofval}

%{
    // Dodatni članovi generisane klase
    KWTable kwTable = new KWTable();

    Yytoken getKW() {
        return new Yytoken(
            kwTable.find(yytext()),
            yytext(),
            yyline,
            yycolumn
        );
    }
%}


%xstate KOMENTAR


slovo     = [a-zA-Z_]
cifra     = [0-9]
hexcifra  = [0-9a-fA-F]

%%

"\(\*"           { yybegin(KOMENTAR); }
<KOMENTAR>"\*\)" { yybegin(YYINITIAL); }
<KOMENTAR>.|\n   { ; }


[\t\r\n ] { ; }


"(" { return new Yytoken(sym.LEFTPAR, yytext(), yyline, yycolumn); }
")" { return new Yytoken(sym.RIGHTPAR, yytext(), yyline, yycolumn); }


"+" { return new Yytoken(sym.PLUS, yytext(), yyline, yycolumn); }
"-" { return new Yytoken(sym.MINUS, yytext(), yyline, yycolumn); }
"*" { return new Yytoken(sym.MUL, yytext(), yyline, yycolumn); }
"=" { return new Yytoken(sym.ASSIGN, yytext(), yyline, yycolumn); }


";" { return new Yytoken(sym.SEMICOLON, yytext(), yyline, yycolumn); }
":" { return new Yytoken(sym.COLON, yytext(), yyline, yycolumn); }
"," { return new Yytoken(sym.COMMA, yytext(), yyline, yycolumn); }
"." { return new Yytoken(sym.DOT, yytext(), yyline, yycolumn); }

//STRING KONSTANTE
\"([^\"\n])*\" {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}


'[^']' {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}

//INTEGER KONSTANTE 


0[xX]{hexcifra}+ {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}


0[0-7]+ {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}


[1-9]{cifra}* | 0 {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}


{slovo}({slovo}|{cifra})* {
    return getKW();
}


. {
    System.out.println(
        "LEX ERROR: '" + yytext() +
        "' at line " + yyline +
        ", column " + yycolumn
    );
}
