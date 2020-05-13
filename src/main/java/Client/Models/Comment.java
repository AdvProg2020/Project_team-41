package Client.Models;

import Client.Models.Person.Person;

import java.io.Serializable;

public class Comment implements Serializable {
    private Person person;
    private Product product;
    private String title;
    private String Content;
    private CommentSituation commentSituation;
    private boolean hasHeBought;

    public CommentSituation getCommentSituation() {
        return commentSituation;
    }

    public Comment(Person person, Product product, String title, String content, CommentSituation commentSituation) {
        this.person = person;
        this.product = product;
        this.title = title;
        Content = content;
        this.commentSituation = commentSituation;
    }

    public void setHasHeBought(boolean hasHeBought) {
        this.hasHeBought = hasHeBought;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "person=" + person +
                ", title='" + title + '\'' +
                ", Content='" + Content + '\'' +
                ", commentSituation=" + commentSituation +
                ", hasHeBought=" + hasHeBought +
                '}';
    }
}
