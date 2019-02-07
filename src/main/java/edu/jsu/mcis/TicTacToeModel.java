package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        // DONE
        
        for (int i = 0; i < width; ++i) {

            for (int q = 0; q < width; ++q) {

                board[i][q] = Mark.EMPTY;
            }
        }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // DONE
        boolean moveOkayYes = false;

        if (isValidSquare(row, col)) {

            if (!isSquareMarked(row, col)) {
                moveOkayYes = true;

                if (isXTurn()){

                    board[row][col] = Mark.X;
                    xTurn = ! xTurn;
                    

                }
                else {

                    board[row][col] = Mark.O;
                    xTurn = ! xTurn;
                   

                }

            }

        }
        
        return moveOkayYes; 
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        // DONE

        if (row < board.length  && row >= 0) {
            
            if (col < board.length && col >= 0) {

                return true;
            }
        }
        
        return false;
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // DONE 

        if (board[row][col].equals(Mark.O)) {
            
            return true;

        }
        if (board[row][col].equals(Mark.X)) {

             return true;
        }
        return false;
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // DONE

        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // Done
        Result gameResult = Result.NONE;

        

        if (isMarkWin(Mark.X)) {

            gameResult =  Result.X;
        }
        

        else if (isMarkWin(Mark.O)) {

            gameResult =  Result.O;

        }
        
        else if (isTie()) {

            gameResult =  Result.TIE;

        }

        return gameResult; 
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        int markCounter;
        boolean win = false;
        Mark markBeingTested;


        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // DONE

        // Determine Which Mark to Test For
        markBeingTested = mark;

        // Win Condition For a Row

        for ( int row = 0; row < board.length; ++row) {

            markCounter = 0;

            for (int col = 0; col < board.length; ++col) {

                if (board[row][col].equals(markBeingTested)) {
                    
                    ++markCounter;

                    if (markCounter == board.length) {

                        win = true;
                    }
                }
                
            }
            markCounter = 0;
        }

        // Win Condition For Column 

        for (int col = 0; col < board.length; ++col) {
            
            markCounter = 0;
            
            for (int row = 0; row < board.length; ++row) {
                
                if (board[row][col].equals(markBeingTested)) {
                    
                    ++markCounter;
                    
                    if (markCounter == board.length) {
                        
                        win = true;
                
                    }
                }
                
            }
        }

        // Win Condition for Diagnal from [0][0] to [board.length][board.length]
        
        markCounter = 0;
        
        for (int i = 0; i < board.length; ++ i) {
            
            Mark squareBeingTestedMark = board[i][i];
            
            if (squareBeingTestedMark.equals(markBeingTested)) {
                
                ++markCounter;
                
                if (markCounter == board.length) {
                    
                    win = true;
                    //System.out.println("WIN Condition 3");
                }
            }
            else {

                markCounter = 0;

            }
        }

        // Win Condition for Diagnal from [0][board.length] to [board.length][0]
        
        markCounter = 0;
        
        for (int i = 0; i < board.length; ++ i) {
            
            Mark squareBeingTestedMark = board[i][ board.length - i - 1];
            
            if (squareBeingTestedMark.equals(markBeingTested)) {
                
                ++markCounter;
                
                if (markCounter == board.length) {
                    
                    win = true;
                    //System.out.println("WIN Condition 4");
                }
            }
            else {
                markCounter = 0;
            }
        }

        return win; 

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // DONE
        int spotOccupied = 0;
        boolean tie = false;

        for (int row = 0; row < board.length; ++row) {
            for(int col = 0; col < board.length; ++col) {

                if (board[row][col].equals(Mark.O) || board[row][col].equals((Mark.X))){

                    ++spotOccupied;

                    if (spotOccupied == board.length * board.length) {

                        tie = true;
                    }
                }
            }
        }
        return tie;   
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // DONE

        output.append("");

        for  (int boardLabel = 0; boardLabel < board.length; ++boardLabel) {

            output.append(boardLabel);
        }
        output.append("\n\n");

        for (int row = 0; row < board.length; ++row) {

            output.append(row + " ");

            for (int col = 0; col < board.length; ++col) {

                output.append(board[row][col]);

            }
            output.append("\n");

        }
        return output.toString();
        
    }
    
}
