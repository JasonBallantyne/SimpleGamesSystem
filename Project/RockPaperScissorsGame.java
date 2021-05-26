package Project;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame implements Game{

    // singleton pattern RockPaperScissorsGame object
    private static final RockPaperScissorsGame instance = new RockPaperScissorsGame();
    private Player player;
    private Scanner scanner;

    public RockPaperScissorsGame(){
        this.scanner = new Scanner(System.in);
    }

    // static method to get object
    public static RockPaperScissorsGame getInstance() {
        return instance;
    }

    // set player to the game
    @Override
    public void setPlayer(Player player){
        this.player = player;

    }

    // game loop
    @Override
    public void start(){
        Random random = new Random(System.currentTimeMillis());
        System.out.println("\nHello " + this.player.getName()+ ". Lets start the Rock Paper Scissors game!");
        String items[] = {"Rock", "Paper", "Scissor"};
        System.out.println("Choose Rock or Paper or Scissor");
        System.out.println("1 - Rock");
        System.out.println("2 - Paper");
        System.out.println("3 - Scissor");
        int userSelectedItem;
        while(true){
            try{
                userSelectedItem = Integer.parseInt(this.scanner.nextLine());
                if(userSelectedItem >= 1 && userSelectedItem <= 3){
                    System.out.println("You selected " + items[userSelectedItem-1]);
                    break;
                }else {
                    System.out.println("Please enter an Integer between 1 and 3");
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please enter an Integer between 1 and 3");
            }
        }

        System.out.println("Computer is choosing one.....");
        int ComputerSelectedItem = random.nextInt(3) + 1;
        System.out.println("Computer selected one is: " + items[ComputerSelectedItem-1]);

        if(userSelectedItem == 1 && ComputerSelectedItem == 3){
            System.out.println(this.player.getName()+" won the game and earned 10 points");
            this.player.addPoints(10);
        } else if(userSelectedItem == 2 && ComputerSelectedItem == 1){
            System.out.println(this.player.getName()+" won the game and earned 10 points");
            this.player.addPoints(10);
        } else if(userSelectedItem == 3 && ComputerSelectedItem == 2){
            System.out.println(this.player.getName()+" won the game and earned 10 points");
            this.player.addPoints(10);
        } else if(userSelectedItem == ComputerSelectedItem){
            System.out.println("Game is a draw, please try again");
            start();
        } else {
            if(this.player.getType() == PlayerType.VIP){
                VIPPlayer vipPlayer = (VIPPlayer)this.player;
                if(vipPlayer.availableBonusRounds() > 0){
                    System.out.println(vipPlayer.getName()+" lost the game, but you are a VIP player you have "+ vipPlayer.availableBonusRounds()+ " rounds left to try again");
                    vipPlayer.claimOneBonusRound();
                    start();
                } else {
                    System.out.println(vipPlayer.getName()+" lost the game, you don't have any extra rounds please try again another time");
                }

            } else if(this.player.getType() == PlayerType.NORMAL){
                System.out.println(this.player.getName()+" lost the game, don't worry please try again another time");
            }

        }

        System.out.println(this.player.getName()+", Thank you for playing Rock Paper Scissors game\n");
    }

}
