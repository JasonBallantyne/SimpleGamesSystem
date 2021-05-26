package Project;

import java.util.Random;
import java.util.Scanner;

public class LotteryGame implements Game {

    // singleton pattern LotteryGame object
    private static final LotteryGame instance = new LotteryGame();
    private Player player;
    private Scanner scanner;

    public LotteryGame(){
        this.scanner = new Scanner(System.in);
    }

    // static method to get object
    public static LotteryGame getInstance() {
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
        System.out.println("\nHello " + this.player.getName()+ ". Lets start the Lottery game!");
        Random random = new Random(System.currentTimeMillis());
        int[] userSelectedNumbers = new int[4];
        int[] drawnNumbers = new int[4];
        System.out.println("You have to choose four lottery numbers between 1 - 50");
        System.out.println("Select number one: ");
        userSelectedNumbers[0] = getNumberFromUser();
        System.out.println("Select number two: ");
        userSelectedNumbers[1] = getNumberFromUser();
        System.out.println("Select number three: ");
        userSelectedNumbers[2] = getNumberFromUser();
        System.out.println("Select number four: ");
        userSelectedNumbers[3] = getNumberFromUser();

        System.out.println("Your lottery numbers");
        for(int number : userSelectedNumbers){
            System.out.print(number+ " ");
        }
        System.out.println("\nnumbers are drawing....");
        drawNumbers(random, drawnNumbers);
        System.out.println("Drawn numbers");
        for(int number : drawnNumbers){
            System.out.print(number+ " ");
        }
        System.out.println();
        int numberOfMatches = 0;
        for(int number : userSelectedNumbers){
            for(int i=0; i<drawnNumbers.length; i++){
                if(number == drawnNumbers[i]){
                    numberOfMatches++;
                    drawnNumbers[i] = -1;
                }
            }
        }

        if(numberOfMatches > 0){
            System.out.println(this.player.getName()+", You have "+ numberOfMatches + " correct matches, you have earned " +
                    numberOfMatches * 10+" points");
            this.player.addPoints(numberOfMatches * 10);
        } else {
            if(this.player.getType() == PlayerType.VIP){
                VIPPlayer vipPlayer = (VIPPlayer)this.player;
                if(vipPlayer.availableBonusRounds() > 0){
                    System.out.println(vipPlayer.getName()+" sorry you don't have any correct matches, but you are a VIP player you have "+ vipPlayer.availableBonusRounds()+ " rounds left to try again");
                    vipPlayer.claimOneBonusRound();
                    start();
                } else {
                    System.out.println(vipPlayer.getName()+" sorry you don't have any correct matches, you don't have extra rounds please try again another time");
                }

            } else if(this.player.getType() == PlayerType.NORMAL){
                System.out.println(this.player.getName()+" sorry you don't have any correct matches, don't worry please try again another time");
            }

        }

        System.out.println(this.player.getName()+", Thank you for playing Lottery game\n");

    }

    // get random four numbers
    private void drawNumbers(Random random, int[] drawnNumbers){
        for(int i=0; i<drawnNumbers.length; i++){
            drawnNumbers[i] = random.nextInt(50) + 1;
        }
    }

    private int getNumberFromUser(){
        int number;
        while(true){
            try{
                number = Integer.parseInt(this.scanner.nextLine());
                if(number >= 1 && number <= 50){
                    return number;
                }else {
                    System.out.println("Please enter an Integer between 1 and 50");
                }
            }catch (NumberFormatException ex) {
                System.out.println("Please enter an Integer between 1 and 50");
            }
        }
    }
}
