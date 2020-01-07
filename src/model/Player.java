package model;

public abstract class Player {
    protected Block choice;
    protected int score;

    public Player() {
        this.score = 0;
    }

    public void setChoice(Block block) {
        this.choice = block;
    }

    public Block getChoice() {
        return this.choice;
    }

    public void incrementScore() {
        this.score++;
    }

    public void decrementScore() {
        this.score--;
    }
}
