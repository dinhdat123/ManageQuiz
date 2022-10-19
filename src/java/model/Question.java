/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author DINHDAT
 */
public class Question {

    private int id;
    private String content;
    private Date created;
    private ArrayList<Answer> answers;

    public Question() {

    }

    public Question(String content, Date created, ArrayList<Answer> answers) {
        this.content = content;
        this.created = created;
        this.answers = answers;
    }

    public Question(int id, String content, Date created, ArrayList<Answer> answers) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        return format.format(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

}
