package org.ameet.kafkasample.model;

/**
 * Created by ameet.chaubal on 6/12/2017.
 * class to pack results of submitSync in
 */
public class SubmitStatus {
    private int elapsedMilli;
    private int id;
    private int totalSent;
    private String kafkaTopic;
    private String message;

    public int getTotalSent() {
        return totalSent;
    }

    public void setTotalSent(int totalSent) {
        this.totalSent = totalSent;
    }

    public int getElapsedMilli() {
        return elapsedMilli;
    }

    public void setElapsedMilli(int elapsedMilli) {
        this.elapsedMilli = elapsedMilli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
