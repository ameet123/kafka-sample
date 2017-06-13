package org.ameet.kafkasample.model;

/**
 * Created by ameet.chaubal on 6/13/2017.
 */
public class KMessage {
    private int id;
    private String source;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
