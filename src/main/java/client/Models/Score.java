package Client.Models;

import Client.Models.Person.Person;

import java.io.Serializable;
import java.util.Objects;

public class Score implements Serializable {
    private final Person person;
    private final int score;
    private final Product product;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                Objects.equals(person, score1.person) &&
                Objects.equals(product, score1.product);
    }

}
