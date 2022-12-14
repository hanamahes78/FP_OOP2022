/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hana Maheswari
 */
public class GUI extends JFrame implements NewInterface {
    private int size;                            
    private int playerOrder=0;                    
    private int numberOfPlayer;                   
    private static int livingCellNumber=0;        
    
    // GUI Requirements
    private final JFrame frame;                   
    private final JPanel panel;                   
    private final JButton[][] buttons;            
    private Cell gameBoard[][];                   
    private final GridLayout grid;                
  
    ImageIcon empty   = new ImageIcon(getClass().getResource("/images/empty.png"));
    ImageIcon player1 = new ImageIcon(getClass().getResource("/images/red.png"));
    ImageIcon player2 = new ImageIcon(getClass().getResource("/images/yellow.png"));
    
    public GUI() { // Constructor       
        frame = new JFrame("Connect Four Game");
        panel = new JPanel();
        
        playerNumberAndBoardSize();  // Get the game parameters
        dynamicAllocation();         // DArray

        buttons = new JButton[getBoardSize()][getBoardSize()];    
        grid = new GridLayout(getBoardSize(),getBoardSize());     
        panel.setLayout(grid);   
        
        initialBoard();
        
        frame.setContentPane(panel);
        frame.pack();                       // Automatic sizing of the window based on the added swing components
        frame.setLocationRelativeTo(null);  // Game board will be center of the screen 
        frame.setVisible(true);             // Show frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
    }
     
    /**
     * Setter to board size
     * @param newSize integer to board size
     */
    public void setBoardSize(int newSize) { // Encapsulation
        size = newSize;
    }
    
    /**
     * Getter to board size
     * @return Board size
     */
    public int getBoardSize() {
        return size;
    }
    
    /**
     * static function that returns the number of living cells in all the games.
     * @return Number of living cell in game
     */
    public static int numberOfLivingCells() {
        return livingCellNumber;
    }
    
    public void playerNumberAndBoardSize() {
        String playerNumber = JOptionPane.showInputDialog( "Player Number (1 or 2)" );
        String boardSize = JOptionPane.showInputDialog( "Game Board Size" );

        numberOfPlayer  = Integer.parseInt(playerNumber); // Casting
        int sizeOfBoard = Integer.parseInt(boardSize);

        // User input check to game board
        if(sizeOfBoard < 4) {
           JFrame frameInputError = new JFrame();
           JOptionPane.showMessageDialog(frameInputError,
           "Board size must be greater than 4  !!",
           "Board Size Error",
           JOptionPane.ERROR_MESSAGE);
           System.exit(0);
        }
        setBoardSize(sizeOfBoard);  // Set to dynamic board size 
    }

    // ArrayList
    public void dynamicAllocation() {
      // Create dynamic Cell array to game board
        gameBoard = new Cell[getBoardSize()][getBoardSize()];
        for (int i = 0; i <getBoardSize(); i++) {
            for (int j = 0; j <getBoardSize(); j++)
                gameBoard [i][j]=new Cell(); 
        }
    } 
   
    public void addButtonsToBoard() {
        for(int i=0; i<getBoardSize(); ++i) {
            for(int j=0; j<getBoardSize(); ++j) {
                buttons[i][j] = new JButton(empty); // Empty button
                
                if(numberOfPlayer==1)
                    buttons[i][j].addActionListener(new listenButtonOnePlayer());
                if(numberOfPlayer==2)
                    buttons[i][j].addActionListener(new listenButtonTwoPlayers());
                panel.add(buttons[i][j]);   // Add buttons to panel
            }
        }            
    }      
    
    public void initialBoard() {
        for (int i=getBoardSize()-2; i>= 0; --i) {
            for (int j = getBoardSize()-1; j>=0; --j)
                gameBoard[i][j].setCellState(-99);
        }    
        addButtonsToBoard(); // Add buttons and listener
    }
     
    /**
     * Game winning state
     * If the four cell is same, user 1 will win the game
     * @param winner integer If the player 1 is equal to 1, otherwise 2
     */
    public void winnerPlayer(int winner) {
        for(int i=0; i<getBoardSize(); ++i) {         
            for(int j=0; j<getBoardSize(); ++j) {     
                if(gameBoard[i][j].getCellState() == winner) {    
                    // CHECK UP TO DOWN POSITIONS
                    if(i+3<getBoardSize()) {
                        if(gameBoard[i+1][j].getCellState() == winner && gameBoard[i+2][j].getCellState() == winner && gameBoard[i+3][j].getCellState() == winner) {
                            if(winner==1)
                                showResult(1);
                            else
                                showResult(2);
                        }
                    }
                    
                    // CHECK LEFT TO RIGHT POSITION
                    if(j + 3 <getBoardSize()) { 
                        if(gameBoard[i][j+1].getCellState() == winner && gameBoard[i][j+2].getCellState() == winner && gameBoard[i][j+3].getCellState() == winner) { 
                            if(winner==1)
                                showResult(1);
                            else
                                showResult(2);
                        }
                    }

                    // CHECK DIAGONAL LEFT TO RIGHT POSITION
                    if(i  < getBoardSize()- 3 && j<getBoardSize()-3) {
                        if(gameBoard[i+1][j+1].getCellState() == winner && gameBoard[i+2][j+2].getCellState() == winner && gameBoard[i+3][j+3].getCellState() == winner) {  
                            if(winner==1)
                                showResult(1);
                            else
                                showResult(2);
                        }   
                    }

                    // CHECK DIAGONAL RIGHT TO LEFT POSITION
                    if(i  < getBoardSize()- 3 && j - 3 >= 0 ) {
                        if(gameBoard[i+1][j-1].getCellState() == winner && gameBoard[i+2][j-2].getCellState() == winner && gameBoard[i+3][j-3].getCellState() == winner) {
                            if(winner==1)
                                showResult(1);
                            else
                                showResult(2);
                        }
                    }                           
                }         
            }             
        } 
    }     
   
    /**
     * Show winner player on the new frame
     * @param winnerPlayer integer if the parameter is equal to 1,player 1 is winner.Otherwise, player 2
     */
    public void showResult(int winnerPlayer) {
        JFrame frameShowResult = new JFrame();       
        if(winnerPlayer==1) {
            JOptionPane.showMessageDialog(frameShowResult,
            "\nWinner : Player 1\n\nThe new game will start.\n\n",
            "End Game",
            JOptionPane.INFORMATION_MESSAGE);
            startAgain(); 
        }
        else {
            JOptionPane.showMessageDialog(frameShowResult,
            "\nWinner : Player 2\n\nThe new game will start.\n\n",
            "End Game",
            JOptionPane.INFORMATION_MESSAGE); 
            startAgain();    
        }
    }
   
    public void startAgain() {
        for(int i=0; i<getBoardSize(); ++i) {         
            for(int j=0; j<getBoardSize(); ++j) {
                gameBoard[i][j].setCellState(-99);  // Initial Value
                buttons[i][j].setIcon(empty);       // Put the empty cell icon
            }
        }
        
        frame.setVisible(false);             // Unvisible previous game board
        Main newGame = new Main();           // New Game Object
    }
     
    /**
     *
     * Action listener to game button
     * Computer vs Player 1
     */
    private class listenButtonOnePlayer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try { // Exception Handling
                for(int i=getBoardSize()-1; i>=0; --i) {
                    for(int j=0; j<=getBoardSize()-1; ++j) {
                        if(buttons[i][j]== e.getSource()) {  
                            if(0 == playerOrder%2) { // Player 1 
                                for(int k=0; k<=getBoardSize(); ++i) {
                                    if(gameBoard[i-k][j].getCellState() == 0) {
                                       buttons[i-k][j].setIcon(player1);           // Change button icon
                                       gameBoard[i-k][j].setAllPosition('X', i);   // Set cell parameters
                                       gameBoard[i-k][j].setCellState(1);          // Set cell state
                                       ++livingCellNumber;                         // Increase living cell number
                                       winnerPlayer(1);                            // Check game winning state
                                       break; 
                                    } 
                                }
                                setUpperCellToEmpty(i,j); // Set the upper cells to empty cell to listen button
                                System.out.println("... Player 1 played ... ");
                                ++playerOrder;  // Change player order from player 1 to computer
                                break;    
                            }

                            if(1 == playerOrder%2) { // Computer Operations
                                moveComputer(i);
                                System.out.println("... Computer played ... ");
                                ++playerOrder; // Change player order from computer to player 1
                                break;
                            }
                            
                            else 
                                warningMessage();
                        }
                    }    
                }
            } 
            
            catch(Exception ex) {
                warningMessage();
            }
        } 
    } 
    
    public void warningMessage() {
        JFrame frameWarning = new JFrame();           
        JOptionPane.showMessageDialog(frameWarning,
        "Invalid Movement !!\nThe cell is not empty.", "Warning",
        JOptionPane.WARNING_MESSAGE);
    }
                        
    /**
     * Set the upper cells to empty cell to listen button
     * @param rowPos integer row position to board
     * @param columnPos integer column position to board
     */ 
    public void setUpperCellToEmpty(int rowPos, int columnPos) {
        try {
            gameBoard[rowPos-1][columnPos].setCellState(0);    
        }   
        catch (Exception ex) { 
        }      
    }
     
    /**
     * Computer's logic fills cells from left to right
     * @param rowPosition Cell row position
     */
    public void moveComputer(int rowPosition){
        int l,m;
        boolean flag=false;

        for(l=getBoardSize()-1; (l>=0)&& !flag; --l) { 
            for(m=0; (m<getBoardSize()) && !flag; ++m) {
                if(gameBoard[l][m].getCellState() == 0) {
                    buttons[l][m].setIcon(player2);                     // Set new button icon
                    gameBoard[l][m].setAllPosition('O', rowPosition);   // Set cell parameters
                    gameBoard[l][m].setCellState(2);                    // Set cell state
                    ++livingCellNumber;
                    winnerPlayer(2); // Check the computer winning state
                    flag = true;  
                    setUpperCellToEmpty(l,m);
                }
            }
        } 
    }

    /**
     * Action listener to game button
     * Player 1 vs Player 2
     */
    private class listenButtonTwoPlayers implements ActionListener {           
        @Override
        public void actionPerformed(ActionEvent e) {            
            try {
                int eventFlag = 0;
                int flagPlayerOrder=0;

                for(int i=getBoardSize()-1; i>=0; --i) {
                    for(int j=0; j<=getBoardSize()-1; ++j) {
                        if(eventFlag==0 && buttons[i][j]== e.getSource()) {  
                            if(flagPlayerOrder==0 && playerOrder%2==0) { 
                                for(int k=0; k<=getBoardSize(); ++i) { // Player 1  
                                    if(gameBoard[i-k][j].getCellState()==0 && playerOrder%2==0) {
                                       buttons[i-k][j].setIcon(player1);          // Set new icon to player 1 
                                       gameBoard[i-k][j].setAllPosition('X', i);  // Set cell parameters
                                       gameBoard[i-k][j].setCellState(1);
                                       ++livingCellNumber;  // Increase living cell number
                                       winnerPlayer(1);     // Check player 1 winning state
                                       flagPlayerOrder=1;   
                                       eventFlag=1;
                                       break; 
                                    } 
                                }
                                setUpperCellToEmpty(i,j);   // Set upper cell to empty 
                                System.out.println("... Player 1 played ... ");
                                ++playerOrder; // Change order from player 1 to player 2
                                break;
                            }

                            if(flagPlayerOrder==0 && playerOrder%2==1) { // Player 2
                                for(int k=0; k<=getBoardSize(); ++i) {
                                    if(gameBoard[i-k][j].getCellState()==0 && playerOrder%2==1) {
                                       buttons[i-k][j].setIcon(player2);            // Set new icon to player 2
                                       gameBoard[i-k][j].setAllPosition('O', i);    // Set cell parameters    
                                       gameBoard[i-k][j].setCellState(2);           // Set cell state
                                       ++livingCellNumber;
                                       winnerPlayer(2);
                                       flagPlayerOrder=1;
                                       eventFlag=1;
                                       break;
                                    } 
                                }
                                setUpperCellToEmpty(i,j);
                                System.out.println("... Player 2 played ... ");
                                ++playerOrder;
                                break;
                            }
                        } 
                    } 
                }    
            }
            
            catch(Exception ex) { 
                warningMessage(); 
            }     
        } 
    } 
}