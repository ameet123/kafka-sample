package org.ameet.kafkasample.model;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * sample model representation for welcome
 */
public class Greet {
    private final long key;
    private final String content;

    public Greet(long key, String content) {
        this.key = key;
        this.content = content;
    }

    public long getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }
}
