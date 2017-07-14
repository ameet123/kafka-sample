package org.ameet.kafkasample.model.ihg;

import java.util.Arrays;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
public class EZMessage {
    private String action;
    private String generationTime;
    private RoomType[] roomTypes;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(String generationTime) {
        this.generationTime = generationTime;
    }

    public RoomType[] getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomType[] roomTypes) {
        this.roomTypes = roomTypes;
    }

    @Override
    public String toString() {
        return "EZMessage{" +
                "action='" + action + '\'' +
                ", generationTime='" + generationTime + '\'' +
                ", roomTypes=" + Arrays.toString(roomTypes) +
                '}';
    }
}
