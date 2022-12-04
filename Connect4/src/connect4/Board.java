/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect4;

/**
 *
 * @author Hana Maheswari
 */
public class Board {
    private static final int rows = 6;
    private static final int columns = 7;
    
    Piece [][]ourBoard = new Piece[rows][columns];
    
    public static int getColumns(){
        return columns;
    }
    /*
    public static int getRows(){
        return rows;
    }*/
    
    private boolean checkDiagonal(int row, int col, String winningColor, boolean rightDiagonal){
        int winningStreak = 4;
        int reverser = 1;
        
        if(rightDiagonal) reverser = -1;
        
        for(int winRow = row - 3, winCol = col - (3 * reverser); winRow <= row +3; winRow++, winCol += reverser){
            if(!rightDiagonal){
                if(winRow < 0 || winCol < 0) continue;
                if(winRow >= rows || winCol >= columns) break;
            }
            else{
                if(winRow < 0 || winCol >= columns) continue;
                if(winRow >= rows || winCol < 0) break;
            }
            
            if(ourBoard[winRow][winCol] != null && ourBoard[winRow][winCol].getColor().equals(winningColor)){
                if(--winningStreak == 0){
                    return true;
                }
            }
            else{
                winningStreak = 4;
            }
        }
        return false;
    }
    
    public boolean checkForWinner(int col, String winningColor){
        for(int row = 0; row < rows; row++){
            if(ourBoard[row][col] != null){
                // if this reaches 0, we have won
                int winningStreak = 3;
                
                // check downwards
                for(int winRow = row + 1; winRow < rows; winRow++){
                    if(ourBoard[winRow][col].getColor().equals(winningColor)){
                        winningStreak--;
                        if(winningStreak == 0){
                            return true;
                        }
                    }
                    else{
                        winningStreak = 3;
                    }
                }

                // for any other type of check, it needs to be 4
                winningStreak = 4;
                
                // look at the horizontal
                for(int winCol = col - 3; winCol <= col + 3; winCol++){
                    if(winCol < 0) continue;
                    if(winCol >= columns) break;
                
                    if(ourBoard[row][winCol] != null && ourBoard[row][winCol].getColor().equals(winningColor)){
                        winningStreak--;
                        if(winningStreak == 0){
                            return true;
                        }
                    }
                    else{
                        winningStreak = 4;
                    }
                }
                
                // check left diagonal
                if(checkDiagonal(row, col, winningColor, false)) return true;
                // check right diagonal
                if(checkDiagonal(row, col, winningColor, true)) return true;
                
                return false;
            }
        }
        return false;
    }
    
    public boolean addPiece(int colToAdd, String color){
        // within normal range
        if(colToAdd >= 0 && colToAdd < columns){
            // we can add
            if(ourBoard[0][colToAdd] == null){
                boolean addedThePiece = false;
                for(int row = rows - 1; row >= 0; row--){
                    if(ourBoard[row][colToAdd] == null){
                        ourBoard[row][colToAdd] = new Piece();
                        ourBoard[row][colToAdd].setColor(color);
                        addedThePiece = true;
                        break;
                    }
                }
                return addedThePiece;
            }
            // that row is full
            else{
                System.err.println("This column is full, pllease choose another.");
                return false;
            }
        }
        // outside normal range
        else{
            System.err.println("Add not supported.");
            return false;
        }
    }
    
    public void printBoard(){
        for(int col = 0; col < columns + 2; col++)
            System.out.print("-");
        System.out.println();
        for(int row = 0; row < rows; row++){
            System.out.print("|");
            for(int col = 0; col < columns; col++){
                if(ourBoard[row][col] == null){
                    System.out.print("_");
                }
                else{
                    System.out.print(ourBoard[row][col].getColor());
                }
                System.out.print("|");
            }
            System.out.println();
        }
        for(int col = 0; col < columns + 2; col++)
            System.out.print("-");
        System.out.println();
    }
    
    public Board(){
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                ourBoard[row][col] = null;
            }
        }
    }
}
