/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect4;

/**
 *
 * @author Hana Maheswari
 */
public class GameLoop {
    private final Connect4Game game;
    private final GUI ourGUI;

    public GameLoop() {
        game = new Connect4Game("R", "Y", 6, 7);
        ourGUI = new GUI(game.isIs1playing(), game, 6, 7);
    }

}