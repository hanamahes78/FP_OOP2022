/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package connect4;

/**
 *
 * @author Hana Maheswari
 */
public interface NewInterface {

    void addButtonsToBoard();

    // ArrayList
    void dynamicAllocation();

    /**
     * Getter to board size
     * @return Board size
     */
    int getBoardSize();

    void initialBoard();

    /**
     * Computer's logic fills cells from left to right
     * @param rowPosition Cell row position
     */
    void moveComputer(int rowPosition);

    void playerNumberAndBoardSize();

    /**
     * Setter to board size
     * @param newSize integer to board size
     */
    void setBoardSize(int newSize);

    /**
     * Set the upper cells to empty cell to listen button
     * @param rowPos integer row position to board
     * @param columnPos integer column position to board
     */
    void setUpperCellToEmpty(int rowPos, int columnPos);

    /**
     * Show winner player on the new frame
     * @param winnerPlayer integer if the parameter is equal to 1,player 1 is winner.Otherwise, player 2
     */
    void showResult(int winnerPlayer);

    void startAgain();

    void warningMessage();

    /**
     * Game winning state
     * If the four cell is same, user 1 will win the game
     * @param winner integer If the player 1 is equal to 1, otherwise 2
     */
    void winnerPlayer(int winner);
    
}
