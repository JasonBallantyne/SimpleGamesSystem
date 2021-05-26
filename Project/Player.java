package Project;

public class Player {

    private String name;
    private int points;
    private PlayerType type;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
        this.points = 0;
    }


    public void addPoints(int points) {
        this.points = this.points + points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public PlayerType getType() {
        return type;
    }

    public String toString() {
        if (this.getType() == PlayerType.NORMAL) {
            return String.format("%-20s %-10s", this.getName(), this.getPoints());
        } else {
            return String.format("%-20s %-10s", this.getName() + " (" + this.getType() + ")", this.getPoints());
        }
    }
}
