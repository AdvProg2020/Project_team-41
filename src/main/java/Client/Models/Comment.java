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

    public Comment(Person person, Product product, String title, String content, CommentSituation commentSituation,boolean hasHeBought) {
        this.person = person;
        this.product = product;
        this.title = title;
        Content = content;
        this.commentSituation = commentSituation;
        this.hasHeBought = hasHeBought;

    }

    public Person getPerson() {
        return person;
    }

    public Product getProduct() {
        return product;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }

    public boolean isHasHeBought() {
        return hasHeBought;
    }

    public void setHasHeBought(boolean hasHeBought) {
        this.hasHeBought = hasHeBought;
    }

    @Override
    public String toString() {
        return "person : " + person.getUserName() +
                "\ntitle :'" + title + '\'' +
                "\nContent :'" + Content + '\'' +
                "\ncommentSituation :" + commentSituation +
                "\nhasHeBought :" + hasHeBought+"\n";
    }

    public void setCommentSituation(CommentSituation commentSituation) {
        this.commentSituation = commentSituation;
    }
}
