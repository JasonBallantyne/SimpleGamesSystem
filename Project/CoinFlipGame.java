package Project;

import java.util.Random;
import java.util.Scanner;

public class CoinFlipGame implements Game{

    // singleton pattern CoinFlipGame object
    private static final CoinFlipGame instance = new CoinFlipGame();
    private Player player;
    private Scanner scanner;

    public CoinFlipGame(){
        this.scanner = new Scanner(System.in);
    }

    // static method to get object
    public static CoinFlipGame getInstance() {
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
        String sides[] = {"Head", "tail"};
        System.out.println("\nHello " + this.player.getName()+ ". Lets start the Coin Flip game!");
        System.out.println("Guess the side");
        System.out.println("1 - Head");
        System.out.println("2 - Tail");
        int userSelectedSide;
        while(true){
            try{
                userSelectedSide = Integer.parseInt(this.scanner.nextLine());
                if(userSelectedSide >= 1 && userSelectedSide <= 2){
                    System.out.println("You selected " + sides[userSelectedSide-1]);
                    break;
                }else {
                    System.out.println("Please enter an Integer between 1 and 2");
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please enter an Integer between 1 and 2");
            }
        }

        System.out.println("Coin is flipping.....");
        int flippedSide = random.nextInt(2) + 1;
        System.out.println("Flipped side is: " + sides[flippedSide-1]);

        if(userSelectedSide == flippedSide){
            System.out.println(this.player.getName()+", Your guess is correct and you earned 10 points");
            this.player.addPoints(10);
        } else {
            if(this.player.getType() == PlayerType.VIP){
                VIPPlayer vipPlayer = (VIPPlayer)this.player;
                if(vipPlayer.availableBonusRounds() > 0){
                    System.out.println(vipPlayer.getName()+" lost the game, but you are a VIP player you have "+ vipPlayer.availableBonusRounds()+ " rounds left to give try again");
                    vipPlayer.claimOneBonusRound();
                    start();
                } else {
                    System.out.println(vipPlayer.getName()+" lost the game, you don't have extra rounds left, please try again another time");
                }

            } else if(this.player.getType() == PlayerType.NORMAL){
                System.out.println(this.player.getName()+" lost the game, please try again another time");
            }
        }

        System.out.println(this.player.getName()+", thank you for playing Coin Flip game\n");



    }
}
