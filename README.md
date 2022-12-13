# FP_OOP2022
Hana Maheswari - 5025211182

Game Connect 4

Game ini dimainkan oleh 2 orang pemain. Terdapat papan dengan beberapa lingkaran. Pemain pertama mengisi papan dengan lingkaran merah. Pemain kedua mengisi papan dengan lingkaran kuning. Pemain yang pertama kali membuat 4 lingkaran warna sama berjejer secara vertikal, diagonal, atau horizontal menang.

Aspek OOP :
- Casting/Conversion

         public void playerNumberAndBoardSize() {
                String playerNumber = JOptionPane.showInputDialog( "Player Number (1 or 2)" );
                String boardSize = JOptionPane.showInputDialog( "Game Board Size" );

                numberOfPlayer  = Integer.parseInt(playerNumber); // Casting
                int sizeOfBoard = Integer.parseInt(boardSize);
        
- Constructor

        public GUI() { // Constructor       
                frame = new JFrame("Connect Four Game");
                panel = new JPanel();

                playerNumberAndBoardSize();  // Get the game parameters
                dynamicAllocation();         // DArray

                buttons = new JButton[getBoardSize()][getBoardSize()];    
                grid = new GridLayout(getBoardSize(),getBoardSize());     
                panel.setLayout(grid);   

                initialBoard();
        
- Overriding

        @Override
        public void actionPerformed(ActionEvent e) {
        
- Encapsulation 

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
    
- Polymorphism

        public class GUI extends JFrame implements NewInterface {
            private int size;                            
            private int playerOrder=0;                    
            private int numberOfPlayer;                   
            private static int livingCellNumber=0;    
    
- ArrayList

        public void dynamicAllocation() {
                // Create dynamic Cell array to game board
                gameBoard = new Cell[getBoardSize()][getBoardSize()];
                for (int i = 0; i <getBoardSize(); i++) {
                        for (int j = 0; j <getBoardSize(); j++)
                                gameBoard [i][j]=new Cell(); 
                }
        } 
    
- Exception Handling

        public void setUpperCellToEmpty(int rowPos, int columnPos) {
                try {
                        gameBoard[rowPos-1][columnPos].setCellState(0);    
                }   
                catch (Exception ex) { 
                }      
        }
    
- GUI

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
    
- Interface

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
    
- Input Output

        public void showResult(int winnerPlayer) {
                JFrame frameShowResult = new JFrame();       
                if(winnerPlayer==1) {
                        JOptionPane.showMessageDialog(frameShowResult,
                        "\nWinner : Player 1\n\nThe new game will start.\n\n",
                        "End Game",
                        JOptionPane.INFORMATION_MESSAGE);
                        startAgain(); 
                }

Source code : https://github.com/CelalTEMIZ/Connect-Four.git
