package edu.jsu.mcis;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class TicTacToeController implements ActionListener{

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView();
        
    }

    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */

        // DONE

        TicTacToeMove playerMove;      

        while (!model.isGameover()) {


            view.showBoard(model.toString());

            playerMove = view.getNextMove(model.isXTurn());
            

            if ( model.makeMark(playerMove.getRow(), playerMove.getCol()));
            
            else {
                view.showInputError();
            }
            model.getResult();
        }
            
    
        /* After the game is over, show the final board and the winner */

        view.showBoard(model.toString());

        view.showResult(model.getResult().toString());
        
        
    }

    public String getMarkAsString(int row, int col) {  

        return (model.getMark(row, col).toString());        
    }
    
    public TicTacToeView getView() {  

        return view;        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // DONE

        Object source = event.getSource();
        
        if (source instanceof JButton) {
            
            JButton button = (JButton) source;
            
            String buttonName = button.getName();
            
            int buttonRow = 0;
            int buttonCol = 0;

            int row = 0;
            int col = 0;

            Character charRow;
            Character charCol;

            charRow = buttonName.charAt(6);
            charCol = buttonName.charAt(7);

            buttonRow = Character.getNumericValue(charRow);
            buttonCol = Character.getNumericValue(charCol);

            view.updateSquares( button, model.isXTurn(), model.makeMark(buttonRow, buttonCol));
            view.showResult(model.getResult().toString());


            
            
        }
    }
}


