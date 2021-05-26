package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // all players list
    private ArrayList<Player> players;
    private ArrayList<Player> sortedPlayers;
    Scanner scanner;

    public App(){
        this.players = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App app = new App();
        while (true){
            app.mainMenu();
            int userChoiceOne;
            try{
                userChoiceOne = Integer.parseInt(app.scanner.nextLine());
                if(userChoiceOne == 1){
                    app.gameSelection(PlayerType.NORMAL);
                } else if(userChoiceOne == 2) {
                    app.gameSelection(PlayerType.VIP);
                } else if (userChoiceOne == 3) {
                    app.sortResults();
                    app.showFinalResults();
                    app.writeToFile();
                    System.out.println("Exiting......");
                    return;
                } else {
                    System.out.println("Please enter an Integer between 1 and 2");
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please enter an Integer between 1 and 2");
            }
        }
    }

    // sorting the players array list
    private void sortResults(){
        this.sortedPlayers = new ArrayList<>();
        int size = players.size();
        while (size > 0) {
            Player lowestPointsPlayer = this.players.get(0);
            for(int i = 0; i<this.players.size(); i++){
                if(lowestPointsPlayer.getPoints() <= this.players.get(i).getPoints()){
                    lowestPointsPlayer = this.players.get(i);
                }
            }
            this.sortedPlayers.add(lowestPointsPlayer);
            this.players.remove(lowestPointsPlayer);
            size--;
        }


    }

    // display final results in console
    private void showFinalResults(){

        System.out.println("Player\t\t:\t\tPoints");
        for(Player player : this.sortedPlayers){
            System.out.println(player.toString());
        }
        System.out.println();
    }

    // write final results to file
    private void writeToFile(){
        File file = new File("leaderboard.txt");

        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            for(Player player : this.sortedPlayers){
                br.write(player.toString());
                br.write("\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // print main menu to the user
    private void mainMenu() {
        System.out.println("Please choose an option: ");
        System.out.println("1. New Player");
        System.out.println("2. New VIP Player");
        System.out.println("3. Quit");
    }

    // print game choosing menu to the user
    private void gameMenu(String name) {
        System.out.println("Hello " + name + ". Please choose a game, or -1 to quit: ");
        System.out.println("1: Lottery");
        System.out.println("2: Coin flip");
        System.out.println("3: Rock Paper Scissors");
    }


    private void gameSelection(PlayerType type){
        System.out.println("Please enter your name: ");
        String name = this.scanner.nextLine();

        GameFactory gameFactory = new GameFactory();

        //create new player and add to list
        Player newPlayer = null;
        if(type == PlayerType.NORMAL){
            newPlayer = new NormalPlayer(name, type);
        } else if(type == PlayerType.VIP) {
            newPlayer = new VIPPlayer(name, type);
        }

        players.add(newPlayer);
        while (true) {
            this.gameMenu(name);
            int userChoiceTwo;
            Game game;
            try{
                userChoiceTwo = Integer.parseInt(this.scanner.nextLine());
                switch (userChoiceTwo){
                    case 1:
                        game = gameFactory.getGame(GameTypes.LOTTERY);
                        game.setPlayer(newPlayer);
                        game.start(); // start lottery game
                        break;
                    case 2:
                        game = gameFactory.getGame(GameTypes.COIN_FLIP);
                        game.setPlayer(newPlayer);
                        game.start(); // start coin flip game
                        break;
                    case 3:
                        game = gameFactory.getGame(GameTypes.ROCK_PAPER_SCISSORS);
                        game.setPlayer(newPlayer);
                        game.start(); // start rock,paper,scissor game
                        break;
                    case -1:
                        return;

                    default:
                        System.out.println("Please enter an Integer between 1 and 3");
                        break;
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please enter an Integer between 1 and 3");
            }
        }

    }
}
