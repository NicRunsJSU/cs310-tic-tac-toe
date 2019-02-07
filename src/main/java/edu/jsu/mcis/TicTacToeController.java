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
        view = new TicTacToeView(this, width);
        
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


