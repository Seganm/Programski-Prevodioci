import java.util.Stack;

public class LL1Parser {
    private MPLexer lexer;
    private Yytoken next;
    private Stack<Integer> stack = new Stack<>();

    public LL1Parser(MPLexer lexer) {
        this.lexer = lexer;
    }

    public boolean sa_ll1() throws Exception {
    	// Ispisi matricu sintaksne analize
    	
		for (int i = 0; i < SyntaxTable.M.length; i++) {
			for (int j = 0; j < SyntaxTable.M[i].length; j++) {
				System.out.printf("%4d", SyntaxTable.M[i][j]);
			}
			System.out.println();
		}
    	
    	
        // Inicijalizacija
        stack.push(sym.EOF);       // push(#)
        stack.push(sym.READ_EXPR); // push(Startni simbol)
        
        int prepoznat = 0;
        int greska = 0;
        next = lexer.next_token(); // next = nextlex()

        do {
            int top = stack.peek();
            int currentInput = next.m_index;
            
            int action = SyntaxTable.M[top][currentInput];

            if (action == SyntaxTable.POP) {
                stack.pop();
                next = lexer.next_token();
            } 
            else if (action == SyntaxTable.ACC) {
                prepoznat = 1;
            } 
            else if (action == SyntaxTable.ERR) {
                greska = 1;
                System.err.println("Sintaksna greska kod tokena: " + next.m_text + 
                                   " na liniji " + (next.m_line + 1));
            } 
            else { // rule k
                stack.pop();
                pushRule(action); // push(alpha, rule[k].right)
            }
        } while (prepoznat == 0 && greska == 0);

        return prepoznat == 1;
    }

    private void pushRule(int k) {
        // Desna strana pravila se stavlja na magacin U OBRNUTOM redosledu
        switch(k) {
            case 1: // ReadExpr -> read ( ID in ID ) do StmtList
                stack.push(sym.STMT_LIST);
                stack.push(sym.DO);
                stack.push(sym.RIGHTPAR);
                stack.push(sym.ID);
                stack.push(sym.IN);
                stack.push(sym.ID);
                stack.push(sym.LEFTPAR);
                stack.push(sym.READ);
                break;
            case 2: // StmtList -> Stmt StmtList'
                stack.push(sym.STMT_LIST_P);
                stack.push(sym.STMT);
                break;
            case 3: // StmtList' -> ; Stmt StmtList'
                stack.push(sym.STMT_LIST_P);
                stack.push(sym.STMT);
                stack.push(sym.SEMICOLON);
                break;
            case 4: // StmtList' -> epsilon
                // Ne push-ujemo nista
                break;
            case 5: // Stmt -> ID = StmtSuffix
                stack.push(sym.STMT_SUFFIX);
                stack.push(sym.ASSIGN);
                stack.push(sym.ID);
                break;
            case 6: // StmtSuffix -> CONST
                stack.push(sym.CONST);
                break;
            case 7: // StmtSuffix -> ID
                stack.push(sym.ID);
                break;
        }
    }
}