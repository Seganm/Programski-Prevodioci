
public class SyntaxTable {
    public static final int ERR = -1;
    public static final int POP = -2;
    public static final int ACC = -3;

    // Matrica: vrste (0-104), kolone (0-29)
    public static int[][] M = new int[105][30];

    static {
        // Inicijalizacija na ERR
        for(int i = 0; i < 35; i++)
            for(int j = 0; j < 30; j++) 
            	M[i][j] = ERR;

        // --- POP AKCIJE (M[a, a] = pop) ---
        int[] terminals = {sym.READ, sym.LEFTPAR, sym.ID, sym.IN, sym.RIGHTPAR, 
                           sym.DO, sym.SEMICOLON, sym.ASSIGN, sym.CONST, sym.EOF};
        
        for (int t : terminals)
        	M[t][t] = POP;

        // --- ACC AKCIJA ---
        M[sym.EOF][sym.EOF] = ACC;

        // --- RULE AKCIJE (M[Neterminal, Terminal]) ---
        // Rule 1: ReadExpr na 'read'
        M[sym.READ_EXPR][sym.READ] = 1;
        
        // Rule 2: StmtList na 'ID'
        M[sym.STMT_LIST][sym.ID] = 2;
        
        // Rule 3: StmtList' na ';'
        M[sym.STMT_LIST_P][sym.SEMICOLON] = 3;
        
        // Rule 4: StmtList' na EOF (Epsilon smena - koristi FOLLOW skup)
        M[sym.STMT_LIST_P][sym.EOF] = 4;
        
        // Rule 5: Stmt na 'ID'
        M[sym.STMT][sym.ID] = 5;
        
        // Rule 6 i 7: StmtSuffix na 'CONST' ili 'ID'
        M[sym.STMT_SUFFIX][sym.CONST] = 6;
        M[sym.STMT_SUFFIX][sym.ID] = 7;
    }
}