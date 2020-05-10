package Client.Models;

import Client.Models.Person.Person;

public class Score {
    private Person person;
    private int score;
    private Product product;

    public int getScore() {
        return score;
    }

    public Score(Person person, int score, Product product) {
        this.person = person;
        this.score = score;
        this.product = product;
    }
}
