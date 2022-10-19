/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DINHDAT
 */
public class Answer {

    private int id;
    private String content;
    private boolean isTrue;

    public Answer(String content, boolean isTrue) {
        this.content = content;
        this.isTrue = isTrue;
    }

    public Answer(int id, String content, boolean isTrue) {
        this.id = id;
        this.content = content;
        this.isTrue = isTrue;
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

    public boolean isIsTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

}
