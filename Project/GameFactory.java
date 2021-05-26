package Project;

public class GameFactory {

    public Game getGame(GameTypes gameType){
        if(gameType == GameTypes.LOTTERY){
            return LotteryGame.getInstance();
        } else if(gameType == GameTypes.COIN_FLIP){
            return CoinFlipGame.getInstance();
        } else if (gameType == GameTypes.ROCK_PAPER_SCISSORS){
            return RockPaperScissorsGame.getInstance();
        }

        return null;
    }
}
