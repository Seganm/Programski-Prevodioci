// import sekcija

%%

// sekcija opcija i deklaracija
%class MPLexer

%cup

%line
%column

%eofval{
	return new Symbol( sym.EOF );
%eofval}

%{
   public int getLine()
   {
      return yyline;
   }
%}

//stanja
%xstate KOMENTAR
//makroi
slovo = [a-zA-Z]
cifra = [0-9A-Fa-f]

%%

// pravila
\(\* { yybegin( KOMENTAR ); }
<KOMENTAR>~"*)" { yybegin( YYINITIAL ); }

[\t\n\r ] { ; }

//operatori
\+ 				{ return new Symbol( sym.PLUS ); }
- 				{ return new Symbol( sym.MINUS ); }
//separatori
; 				{ return new Symbol( sym.SEMICOLON );	}
: 				{ return new Symbol( sym.COLON ); }
= 				{ return new Symbol( sym.ASSIGN ); }
\(				{ return new Symbol( sym.LEFTPAR ); }
\)				{ return new Symbol( sym.RIGHTPAR ); }

//kljucne reci
"program"		{ return new Symbol( sym.PROGRAM );	}	
"return"		{ return new Symbol( sym.RETURN );	}
"integer"		{ return new Symbol( sym.INTEGER );	}
"char"			{ return new Symbol( sym.CHAR );	}
"string"		{ return new Symbol( sym.STRING );	}
"file"			{ return new Symbol( sym.FILE );	}
"read"			{ return new Symbol( sym.READ );	}
"open"			{ return new Symbol( sym.OPEN );	}
"in"			{ return new Symbol( sym.IN );	}
"begin"			{ return new Symbol( sym.BEGIN );	}
"end"			{ return new Symbol( sym.END );	}
"do"			{ return new Symbol( sym.DO ); }
//identifikatori
{slovo}({slovo}|{cifra})* { return new Symbol( sym.ID ); }
//konstante
((0x)[0-9A-Fa-f]+)|0[0-7]+|(0|[1-9]+0*) { return new Symbol( sym.CONST ); }
'[^]' { return new Symbol( sym.CONST ); }
\".*\" { return new Symbol( sym.CONST ); }
//obrada gresaka
. { if (yytext() != null && yytext().length() > 0) System.out.println( "ERROR: " + yytext() ); }
