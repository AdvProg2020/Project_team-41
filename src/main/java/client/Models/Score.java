package Client.Models;

import Client.Models.Person.Person;

import java.io.Serializable;

public class Score implements Serializable {
    private Person person;
    private int score;
    private Product product;

    public int getScore() {
        return score;
    }

    public Score(Person person, int score, Product product) throws Exception {
        this.person = person;
        if((score > 5) || (score < 0))
            throw new Exception("invalid score");
        this.score = score;
        this.product = product;

    }

}
