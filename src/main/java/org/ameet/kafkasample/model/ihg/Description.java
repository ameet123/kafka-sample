package org.ameet.kafkasample.model.ihg;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
public class Description {

    private String text;
    private String language;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Description{" +
                "text='" + text + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
