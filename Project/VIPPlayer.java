package Project;

public class VIPPlayer extends Player{

    private int bonusRounds;

    public VIPPlayer(String name, PlayerType type) {
        super(name, type);
        this.bonusRounds = 2;
    }


    public void claimOneBonusRound() {
        this.bonusRounds--;
    }

    public int availableBonusRounds(){
        return bonusRounds;
    }
}
