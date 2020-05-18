package Client.Models;

import Client.Models.Person.Person;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;

public class Comment implements Serializable {
    private Person person;
    private Product product;
    private String title;
    private String content;
    private CommentSituation commentSituation;
    private boolean hasHeBought;

    public CommentSituation getCommentSituation() {
        return commentSituation;
    }

    public Comment(Person person, Product product, String title, String content, CommentSituation commentSituation,boolean hasHeBought) throws Exception {
        this.person = person;
        this.product = product;
        this.setTitle(title);
        this.setContent(content);
        this.commentSituation = commentSituation;
        this.hasHeBought = hasHeBought;

    }

    public Person getPerson() {
        return person;
    }

    public Product getProduct() {
        return product;
    }

    public void setTitle(String title) throws Exception {
        if(title.isBlank())
            throw new Exception("title can't be blank!");
        this.title = title;
    }

    public void setContent(String content) throws Exception {
        if(content.isBlank())
            throw new Exception("content can't be blank!");
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isHasHeBought() {
        return hasHeBought;
    }

    public void setHasHeBought(boolean hasHeBought) {
        this.hasHeBought = hasHeBought;
        ServerSaver.write(AllCommands.allData);
    }

    @Override
    public String toString() {
        return "person : " + person.getUserName() +
                "\ntitle :'" + title + '\'' +
                "\nContent :'" + content + '\'' +
                "\ncommentSituation :" + commentSituation +
                "\nhasHeBought :" + hasHeBought+"\n";
    }

    public void setCommentSituation(CommentSituation commentSituation) {
        this.commentSituation = commentSituation;
        ServerSaver.write(AllCommands.allData);
    }
}
