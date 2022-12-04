/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect4;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Hana Maheswari
 */
public class Connect4Game {
    private Board board;
    private final String color1;
    private final String color2;
    
    // true if player1's turn
    // false if player2's turn
    private boolean is1playing;
    
    public Connect4Game(String color1, String color2){
        this.board = new Board();
        this.color1 = color1;
        this.color2 = color2;
        
        is1playing = (new Random()).nextBoolean();
    }
    
    public boolean checkForWinner(int column){
        String winningColor;
        
        if(is1playing){
            winningColor = color1;
        }
        else{
            winningColor = color2;
        }
        return board.checkForWinner(column, winningColor);
    }
    
    public void reset(){
        this.board = new Board();
        is1playing = (new Random()).nextBoolean();
    }
    
    public void startGame(){
        boolean running = true;
        
        // turns of play
        while(running){
            board.printBoard();
            String color;
            if(is1playing){
                color = color1;
                System.out.println("Player " + color1 + " turn");
            }
            else{
                color = color2;
                System.out.println("Player " + color2 + " turn");
            }
        
            System.out.println("Please select which column you want to put your piece in.");
            System.out.print("Choose between 1 and " + Board.getColumns() + ": ");
        
            Scanner input = new Scanner(System.in);
            int column = input.nextInt() - 1;
        
            boolean success = board.addPiece(column, color);
        
            if(success){
                if(checkForWinner(column)){
                    board.printBoard();
                    running = false;
                    if(is1playing){
                        System.out.println("Player " + color1 + " won!");
                    }
                    else{
                        System.out.println("Player " + color2 + " won!");
                    }
                    
                    System.out.println("Would you like to play again?");
                    System.out.print("Y for Yes, anything else for no: ");
                    Scanner input2 = new Scanner(System.in);
                    String playAgain = input2.nextLine();
                    if(playAgain.toLowerCase().equals("y")){
                        reset();
                    }
                    else{
                        running = false;
                    }
                }
                is1playing = !is1playing;
            }
        }
    }
}
